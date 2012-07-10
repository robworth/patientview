package com.worthsoln.patientview.uktransplant;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.ParserThread;

public class UktParserThread implements Runnable, ParserThread {

    private String prebit;
    private String directory;
    private String archiveDirectory;
    private int minutesBetweenWait;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ServletContext servletContext;

    public UktParserThread() {
    }

    public UktParserThread(ServletContext context, String uktDirectory,
                           String uktArchiveDirectory, int minutesBetweenWait) {
        this.directory = uktDirectory;
        this.archiveDirectory = uktArchiveDirectory;
        this.minutesBetweenWait = minutesBetweenWait;
        this.servletContext = context;
    }

    public void run() {
        try {
            while (true) {
                File uktDir = new File(directory);
                File[] uktFiles = uktDir.listFiles(new UktFileFilter());
                updateUktFiles(uktFiles);
                Thread.sleep(1000 * 60 * minutesBetweenWait);
                Date now = new Date(System.currentTimeMillis());
                System.out.println("UktParserThread " + dateFormat.format(now));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateUktFiles(File[] uktFiles) {
        DatabaseDAO dao = new DatabaseDAO("patientview");
        for (int i = 0; i < uktFiles.length; i++) {
            UktParserUtils.updateData(servletContext, uktFiles[i], dao);
            uktFiles[i].delete();
        }
    }

    public String getPrebit() {
        return prebit;
    }

    public void setPrebit(String prebit) {
        this.prebit = prebit;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getArchiveDirectory() {
        return archiveDirectory;
    }

    public void setArchiveDirectory(String archiveDirectory) {
        this.archiveDirectory = archiveDirectory;
    }

    public int getMinutesBetweenWait() {
        return minutesBetweenWait;
    }

    public void setMinutesBetweenWait(int minutesBetweenWait) {
        this.minutesBetweenWait = minutesBetweenWait;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

class UktFileFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        return name.endsWith("uktstatus.gpg.txt");
    }
}

