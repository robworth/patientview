package com.worthsoln;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A class that implements PGP interface for Java.
 * <p/>
 * It calls gpg (GnuPG) program to do all the PGP commands.
 * $Id:$
 *
 * @author Yaniv Yemini, January 2004.
 * @author Based on a class GnuPG by John Anderson, which can be found
 * @author at: http://lists.gnupg.org/pipermail/gnupg-devel/2002-February/018098.html
 * @version 0.5.1
 * @see GnuPG - http://www.gnupg.org/
 */
public class GnuPG {

    // Konstants:
    private final String kGnuPGCommand = "/usr/local/bin/gpg --batch --armor --output -";
    // Class vars:
    private File tmpFile;
    private int gpgExitCode = -1;
    private String gpgResult;
    private String gpgErr;

    /**
     * Reads an output stream from an external process.
     * Imeplemented as a thred.
     */
    class ProcessStreamReader
            extends Thread {

        private StringBuffer stream;
        private InputStreamReader in;
        static final int BUFFER_SIZE = 1024;

        /**
         * Creates new ProcessStreamReader object.
         *
         * @param in
         */
        ProcessStreamReader(InputStream in) {
            super();
            this.in = new InputStreamReader(in);
            this.stream = new StringBuffer();
        }

        public void run() {
            try {
                int read;
                char[] c = new char[BUFFER_SIZE];
                while ((read = in.read(c, 0, BUFFER_SIZE - 1)) > 0) {
                    stream.append(c, 0, read);
                    if (read < BUFFER_SIZE - 1) {
                        break;
                    }
                }
            } catch (IOException io) {
                ;
            }
        }

        String getString() {
            return stream.toString();
        }
    }

    /**
     * Sign
     *
     * @param inStr      input string to sign
     * @param passPhrase passphrase for the personal private key to sign with
     * @return true upon success
     */
    public boolean sign(String inStr, String passPhrase) {
        boolean success;
        success = createTempFile(inStr);
        if (success) {
            success = runGnuPG("--passphrase-fd 0 --sign " + this.tmpFile.getAbsolutePath(), passPhrase);
            this.tmpFile.delete();
            if (success && this.gpgExitCode != 0) {
                success = false;
            }
        }
        return success;
    }

    /**
     * ClearSign
     *
     * @param inStr      input string to sign
     * @param passPhrase passphrase for the personal private key to sign with
     * @return true upon success
     */
    public boolean clearSign(String inStr, String passPhrase) {
        boolean success;
        success = createTempFile(inStr);
        if (success) {
            success = runGnuPG("--passphrase-fd 0 --clearsign " + this.tmpFile.getAbsolutePath(), passPhrase);
            this.tmpFile.delete();
            if (success && this.gpgExitCode != 0) {
                success = false;
            }
        }
        return success;
    }

    /**
     * Signs and encrypts a string
     *
     * @param inStr      input string to encrypt
     * @param keyID      key ID of the key in GnuPG's key database to encrypt with
     * @param passPhrase passphrase for the personal private key to sign with
     * @return true upon success
     */
    public boolean signAndEncrypt(String inStr, String keyID, String passPhrase) {
        boolean success;
        success = createTempFile(inStr);
        if (success) {
            success = runGnuPG("-r " + keyID + " --passphrase-fd 0 -se " + this.tmpFile.getAbsolutePath(), passPhrase);
            this.tmpFile.delete();
            if (success && this.gpgExitCode != 0) {
                success = false;
            }
        }
        return success;
    }

    /**
     * Encrypt
     *
     * @param inStr input string to encrypt
     * @param keyID key ID of the key in GnuPG's key database to encrypt with
     * @return true upon success
     */
    public boolean encrypt(String inStr, String keyID) {
        boolean success;
        success = runGnuPG("-r " + keyID + " --encrypt", inStr);
        if (success && this.gpgExitCode != 0) {
            success = false;
        }
        return success;
    }

    /**
     * Decrypt
     *
     * @param inStr      input string to decrypt
     * @param passPhrase passphrase for the personal private key to sign with
     * @return true upon success
     */
    public boolean decrypt(String inStr, String passPhrase) {
        boolean success;
        success = createTempFile(inStr);
        if (success) {
            success = runGnuPG("--passphrase-fd 0 --decrypt " + this.tmpFile.getAbsolutePath(), passPhrase);
            this.tmpFile.delete();
            if (success && this.gpgExitCode != 0) {
                success = false;
            }
        }
        return success;
    }

    /**
     * Verify a signature
     *
     * @param    inStr    signature to verify
     * @param    keyID    key ID of the key in GnuPG's key database
     * @return true if verified.
     */
    /*
     public boolean verifySignature (String inStr, String keyID)
     {
         boolean        success;

         success = runGnuPG ("--sign " + keyID, inStr);
         if (success && this.gpgExitCode != 0)
             success = false;
         return success;
     }
     */

    /**
     * Get processing result
     *
     * @return result string.
     */
    public String getResult() {
        return gpgResult;
    }

    /**
     * Get error output from GnuPG process
     *
     * @return error string.
     */
    public String getErrorString() {
        return gpgErr;
    }

    /**
     * Get GnuPG exit code
     *
     * @return exit code.
     */
    public int getExitCode() {
        return gpgExitCode;
    }

    /**
     * Runs GnuPG external program
     *
     * @param commandArgs command line arguments
     * @param inputStr    key ID of the key in GnuPG's key database
     * @return true if success.
     */
    private boolean runGnuPG(String commandArgs, String inputStr) {
        Process p;
        String fullCommand = kGnuPGCommand + " " + commandArgs;
//      String      fullCommand = commandArgs;
        System.out.println(fullCommand);
        try {
            p = Runtime.getRuntime().exec(fullCommand);
        } catch (IOException io) {
            System.out.println("io Error" + io.getMessage());
            return false;
        }
        ProcessStreamReader psrStdout = new ProcessStreamReader(p.getInputStream());
        ProcessStreamReader psrStderr = new ProcessStreamReader(p.getErrorStream());
        psrStdout.start();
        psrStderr.start();
        if (inputStr != null) {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            try {
                out.write(inputStr);
                out.close();
            } catch (IOException io) {
                System.out.println("Exception at write! " + io.getMessage());
                return false;
            }
        }
        try {
            p.waitFor();
            psrStdout.join();
            psrStderr.join();
        } catch (InterruptedException i) {
            System.out.println("Exception at waitfor! " + i.getMessage());
            return false;
        }
        try {
            gpgExitCode = p.exitValue();
        } catch (IllegalThreadStateException itse) {
            return false;
        }
        gpgResult = psrStdout.getString();
        gpgErr = psrStderr.getString();
        return true;
    }

    /**
     * A utility method for creating a unique temporary file when needed by one of
     * the main methods.<BR>
     * The file handle is store in tmpFile object var.
     *
     * @param inStr data to write into the file.
     * @return true if success
     */
    private boolean createTempFile(String inStr) {
        this.tmpFile = null;
        FileWriter fw;
        try {
            this.tmpFile = File.createTempFile("YGnuPG", null);
        } catch (Exception e) {
            System.out.println("Cannot create temp file " + e.getMessage());
            return false;
        }
        try {
            fw = new FileWriter(this.tmpFile);
            fw.write(inStr);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            // delete our file:
            tmpFile.delete();
            System.out.println("Cannot write temp file " + e.getMessage());
            return false;
        }
        return true;
    }

    /*
     public static void main (String args[])
     {
         // use this to check:
         System.out.println("Hello World!");
         GnuPG pgp = new GnuPG ();
         if (args[0].equals ("sign"))
             pgp.sign (args[1], args[2]);
         else if (args[0].equals ("clearsign"))
             pgp.clearSign (args[1], args[2]);
         else if (args[0].equals ("se"))
             pgp.signAndEncrypt (args[1], args[2],args[3]);
         else if (args[0].equals ("encrypt"))
             pgp.encrypt (args[1], args[2]);
         else if (args[0].equals ("decrypt"))
             pgp.decrypt (args[1], args[2]);
         System.out.println("result: " + pgp.gpgResult + "\n\n");
         System.out.println("error: " + pgp.gpgErr + "\n\n");
         System.out.println("exit: " + pgp.gpgExitCode + "\n\n");
     }
     */
}

