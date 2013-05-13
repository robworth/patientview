package com.worthsoln.monitoring;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Monitors XML Import process to see if it is stalled or not working properly
 * <p/>
 * Assumes records are created every minute in the file that contains file counts
 *
 * @author Deniz Ozger
 */
public class ImportMonitor {

    private static final int NUMBER_OF_LINES_TO_READ = 10;
    private static final int PENDING_FILE_LIMIT = 10;

    private static final String RECORD_DATA_DELIMITER = ",";
    private static final String RECORD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final int DATE_POSITION_IN_RECORD = 0;
    private static final int NUMBER_OF_FILES_IN_PROTON_DIRECTORY_INFORMATION_POSITION_IN_RECORD = 1;
    private static final int NUMBER_OF_FILES_IN_RPV_XML_DIRECTORY_INFORMATION_POSITION_IN_RECORD = 2;
    private static final String PROJECT_PROPERTIES_FILE = "patientview.properties";

    private static enum ImporterError {
        NUMBER_OF_FILES_IS_STATIC,
        NUMBER_OF_FILES_EXCEEDS_LIMIT
    }

    private static final int LINE_FEED = 0xA;
    private static final int CARRIAGE_RETURN = 0xD;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportMonitor.class);
    private static final Logger COUNT_LOGGER = LoggerFactory.getLogger("count");

    public static void main(String[] args) {
        LOGGER.info("ImportMonitor starts");

        /**
         * Count the number of pending files in both importer directories
         */
        int protonDirectoryFileCount = getNumberOfFilesInDirectory(getProperty("importer.proton_files.directory.path"));
        int rpvXmlDirectoryFileCount = getNumberOfFilesInDirectory(getProperty("importer.rpvxml_files.directory.path"));

        /**
         * Write the counts to the file
         */
        // todo create a new logging level
        logNumberOfFiles(protonDirectoryFileCount, rpvXmlDirectoryFileCount);

        /**
         * Read some lines from the file
         */

        List<String> lines = getLastNLinesOfFile("importer.data.file.location", NUMBER_OF_LINES_TO_READ);

        /**
         * Convert them to Record objects
         */
        List<CountRecord> countRecords = getCountRecordsFromLines(lines);

        /**
         * Check if files are static, send an email if they are
         */
        if (isNumberOfFilesStatic(countRecords)) {
            sendWarningEmail(ImporterError.NUMBER_OF_FILES_IS_STATIC, countRecords);
        }

        /**
         * Check if files exceed limit, send an email if they do
         */
        if (doesNumberOfPendingFilesExceedLimit(countRecords, PENDING_FILE_LIMIT)) {
            sendWarningEmail(ImporterError.NUMBER_OF_FILES_EXCEEDS_LIMIT, countRecords);
        }

        LOGGER.info("ImportMonitor ends");
    }

    private static void logNumberOfFiles(int protonDirectoryFileCount, int rpvXmlDirectoryFileCount) {


        COUNT_LOGGER.info("protonDirectoryFileCount {}", protonDirectoryFileCount);
        COUNT_LOGGER.info("rpvXmlDirectoryFileCount {}", rpvXmlDirectoryFileCount);


    }

    private static int getNumberOfFilesInDirectory(String directoryPath) {
        File[] files = new File(directoryPath).listFiles();

        int count = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && !file.getName().startsWith(".")) { // don't want hidden files
                    count++;
                }
            }
        }

        return count;
    }

    private static void sendWarningEmail(ImporterError importerError, List<CountRecord> countRecords) {
        String subject = "Problems detected in Patient View XML Importer";

        String body = getWarningEmailBody(importerError, countRecords);

        try {
            Resource resource = new ClassPathResource("/patientview.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);

            String fromAddress = props.getProperty("noreply.email");

            String[] toAddresses = {props.getProperty("support.email")};

            sendEmail(fromAddress, toAddresses, null, subject, body);
        } catch (IOException e) {
            LOGGER.error("Could not find properties file: {}", e);
        }
    }

    public static void sendEmail(String from, String[] to, String[] bcc, String subject, String body) {
        if (!StringUtils.hasLength(from)) {
            throw new IllegalArgumentException("Cannot send mail missing 'from'");
        }

        if (!StringUtils.hasLength(subject)) {
            throw new IllegalArgumentException("Cannot send mail missing 'subject'");
        }

        if (!StringUtils.hasLength(body)) {
            throw new IllegalArgumentException("Cannot send mail missing 'body'");
        }

        if ((to == null || to.length == 0) && (bcc == null || bcc.length == 0)) {
            throw new IllegalArgumentException("Cannot send mail missing recipients");
        }

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"classpath*:context-standalone.xml"});

        JavaMailSender javaMailSender = (JavaMailSender) context.getBean("javaMailSender");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;

        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(to);
            if (bcc != null && bcc.length > 0) {
                Address[] bccAddresses = new Address[bcc.length];
                for (int i = 0; i < bcc.length; i++) {
                    bccAddresses[i] = new InternetAddress(bcc[i]);
                }
                message.addRecipients(Message.RecipientType.BCC, bccAddresses);
            }
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, false); // Note: the second param indicates to send plaintext

            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (Exception e) {
            LOGGER.error("Could not send email: {}", e);
        }
    }

    private static String getWarningEmailBody(ImporterError importerError, List<CountRecord> countRecords) {
        String emailBody = "";
        String newLine = System.getProperty("line.separator");

        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]";
        emailBody += newLine;
        emailBody += newLine + "There are some problems in XML Importer.";
        emailBody += newLine;

        if (importerError == ImporterError.NUMBER_OF_FILES_IS_STATIC) {
            emailBody += newLine + "Importer has not imported any files recently.";
        } else if (importerError == ImporterError.NUMBER_OF_FILES_EXCEEDS_LIMIT) {
            emailBody += newLine + "Number of files waiting to be imported is above threshold.";
        }

        emailBody += newLine;
        emailBody += newLine + "Please see the following most recent file count records:";
        emailBody += newLine;

        List<CountRecord> countRecordsToSend = new ArrayList<CountRecord>(countRecords);
        Collections.sort(countRecordsToSend, CountRecord.Order.ByRecordTime.ascending());

        for (CountRecord countRecord : countRecordsToSend) {
            emailBody += newLine + countRecord;
            emailBody += newLine;
        }

        return emailBody;
    }

    private static boolean isNumberOfFilesStatic(List<CountRecord> countRecords) {
        boolean protonFilesAreStatic = true;
        boolean rpvXmlFilesAreStatic = true;

        if (countRecords.size() > 0) {
            int firstRecordsProtonFileCount = countRecords.get(0).getNumberOfFilesInProtonDirectory();
            int firstRecordsRpvXmlFileCount = countRecords.get(0).getNumberOfFilesInRpvXmlDirectory();

            for (CountRecord countRecord : countRecords) {
                if (countRecord.getNumberOfFilesInProtonDirectory() != firstRecordsProtonFileCount) {
                    protonFilesAreStatic = false;
                }

                if (countRecord.getNumberOfFilesInRpvXmlDirectory() != firstRecordsRpvXmlFileCount) {
                    rpvXmlFilesAreStatic = false;
                }
            }

            if (firstRecordsProtonFileCount == 0) { // ignore if the file count is 0
                protonFilesAreStatic = false;
            }

            if (firstRecordsRpvXmlFileCount == 0) { // ignore if the file count is 0
                rpvXmlFilesAreStatic = false;
            }

        } else {
            LOGGER.warn("There are no records to check");

            return false;
        }

        LOGGER.debug("Proton files are static: " + protonFilesAreStatic);
        LOGGER.debug("RpvXml files are static: " + rpvXmlFilesAreStatic);

        return protonFilesAreStatic || rpvXmlFilesAreStatic;
    }

    private static boolean doesNumberOfPendingFilesExceedLimit(List<CountRecord> countRecords, int pendingFileLimit) {
        if (countRecords.size() > 0) {
            List<CountRecord> countRecordsToTest = new ArrayList<CountRecord>(countRecords);

            Collections.sort(countRecordsToTest, CountRecord.Order.ByRecordTime.descending());

            CountRecord latestRecord = countRecordsToTest.get(0);

            LOGGER.debug("Proton files exceed limit: {}",
                    (latestRecord.getNumberOfFilesInProtonDirectory() > pendingFileLimit));
            LOGGER.debug("RpvXml files exceed limit: " +
                    (latestRecord.getNumberOfFilesInRpvXmlDirectory() > pendingFileLimit));

            if (latestRecord.getNumberOfFilesInProtonDirectory() > pendingFileLimit ||
                    latestRecord.getNumberOfFilesInRpvXmlDirectory() > pendingFileLimit) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the last N lines of a file. Assumes lines are terminated by |n ascii character
     */
    private static List<String> getLastNLinesOfFile(String fileLocationProperty, int numberOfLinesToReturn) {
        List<String> lastNLines = new ArrayList<String>();
        java.io.RandomAccessFile fileHandler = null;

        try {
            File file = new File(getProperty(fileLocationProperty));

            fileHandler = new java.io.RandomAccessFile(file, "r");

            long totalNumberOfCharactersInFile = file.length() - 1;

            StringBuilder sb = new StringBuilder();
            int numberOfLinesRead = 0;

            /**
             * loop through characters in file, construct lines out of characters, add lines to a list
             */
            for (long currentCharacter = totalNumberOfCharactersInFile; currentCharacter != -1; currentCharacter--) {
                fileHandler.seek(currentCharacter);

                int readByte = fileHandler.readByte();

                if (readByte == LINE_FEED || readByte == CARRIAGE_RETURN) {
                    numberOfLinesRead++;

                    if (numberOfLinesRead == numberOfLinesToReturn) {
                        break;
                    }

                    /**
                     * add line to line list
                     */
                    lastNLines.add(sb.reverse().toString());
                    sb = new StringBuilder();
                } else {
                    sb.append((char) readByte);
                }
            }

            /**
             * add the last line
             */
            lastNLines.add(sb.reverse().toString());

        } catch (FileNotFoundException e) {
            LOGGER.error("Can not read file {}", fileLocationProperty, e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Can not read file {} or properties file {}",
                    new Object[]{fileLocationProperty, PROJECT_PROPERTIES_FILE}, e.getMessage());
        } finally {
            if (fileHandler != null) {
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    fileHandler = null;
                }
            }
        }

        return lastNLines;
    }

    private static String getProperty(String propertyName) {
        Resource resource = new ClassPathResource("/" + PROJECT_PROPERTIES_FILE);
        Properties props = null;
        String propertyValue = "";

        try {
            props = PropertiesLoaderUtils.loadProperties(resource);

            propertyValue = props.getProperty(propertyName);
        } catch (IOException e) {
            LOGGER.error("Could not find properties file: {}", e);
        }

        return propertyValue;
    }

    /**
     * Convert lines on the file into sensible objects
     */
    private static List<CountRecord> getCountRecordsFromLines(List<String> lines) {
        List<CountRecord> countRecords = new ArrayList<CountRecord>();

        for (String line : lines) {
            String date = extractDateAsString(line);
            String numberOfFilesInProtonDirectory = extractNumberOfFilesInProtonDirectoryAsString(line);
            String numberOfFilesInRpvXmlDirectory = extractNumberOfFilesInRpvXmlDirectoryAsString(line);

            if (isValidCountRecord(date, RECORD_DATE_FORMAT, numberOfFilesInProtonDirectory,
                    numberOfFilesInRpvXmlDirectory)) {

                countRecords.add(CountRecord.fromDateProtonAndRpvXmlCounts(date, RECORD_DATE_FORMAT,
                        Integer.parseInt(numberOfFilesInProtonDirectory),
                        Integer.parseInt(numberOfFilesInRpvXmlDirectory)));
            } else {
                LOGGER.error("Invalid record {}", line);
            }
        }

        return countRecords;
    }

    /**
     * Check if the parameters are in correct formats
     */
    private static boolean isValidCountRecord(String dateString, String dateFormant,
                                              String numberOfFilesInProtonDirectory,
                                              String numberOfFilesInRpvXmlDirectory) {
        return isInteger(numberOfFilesInProtonDirectory) && isInteger(numberOfFilesInRpvXmlDirectory) &&
                parseDate(dateString, dateFormant) != null;
    }

    private static Date parseDate(String maybeDate, String format) {
        Date date = null;

        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
            DateTime dateTime = fmt.parseDateTime(maybeDate);
            date = dateTime.toDate();
        } catch (Exception e) {
            LOGGER.error("Invalid date: {}", maybeDate, e.getMessage());
        }

        return date;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


    private static String extractDateAsString(String line) {
        return extractRecordDataOnThisPosition(line, DATE_POSITION_IN_RECORD);
    }

    private static String extractNumberOfFilesInProtonDirectoryAsString(String line) {
        return extractRecordDataOnThisPosition(line,
                NUMBER_OF_FILES_IN_PROTON_DIRECTORY_INFORMATION_POSITION_IN_RECORD);
    }

    private static String extractNumberOfFilesInRpvXmlDirectoryAsString(String line) {
        return extractRecordDataOnThisPosition(line,
                NUMBER_OF_FILES_IN_RPV_XML_DIRECTORY_INFORMATION_POSITION_IN_RECORD);
    }

    private static String extractRecordDataOnThisPosition(String line, int position) {
        String[] recordDataSections = line.split(RECORD_DATA_DELIMITER);

        if (recordDataSections != null && recordDataSections.length > position) {
            return recordDataSections[position];
        } else {
            return null;
        }
    }

    private static class CountRecord {
        private Date recordTime;
        private int numberOfFilesInProtonDirectory;
        private int numberOfFilesInRpvXmlDirectory;

        private CountRecord() {

        }

        public static CountRecord fromDateProtonAndRpvXmlCounts(String dateString, String dateFormat,
                                                                int numberOfFilesInProtonDirectory,
                                                                int numberOfFilesInRpvXmlDirectory) {
            CountRecord countRecord = new CountRecord();
            countRecord.setRecordTime(parseDate(dateString, dateFormat));
            countRecord.setNumberOfFilesInProtonDirectory(numberOfFilesInProtonDirectory);
            countRecord.setNumberOfFilesInRpvXmlDirectory(numberOfFilesInRpvXmlDirectory);

            return countRecord;
        }

        public static enum Order implements Comparator<CountRecord> {
            ByRecordTime() {
                public int compare(CountRecord leftRecord, CountRecord rightRecord) {
                    return leftRecord.getRecordTime().compareTo(rightRecord.getRecordTime());
                }
            };

            public abstract int compare(CountRecord leftRecord, CountRecord rightRecord);

            public Comparator ascending() {
                return this;
            }

            public Comparator descending() {
                return Collections.reverseOrder(this);
            }
        }

        public Date getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(Date recordTime) {
            this.recordTime = recordTime;
        }

        public int getNumberOfFilesInProtonDirectory() {
            return numberOfFilesInProtonDirectory;
        }

        public void setNumberOfFilesInProtonDirectory(int numberOfFilesInProtonDirectory) {
            this.numberOfFilesInProtonDirectory = numberOfFilesInProtonDirectory;
        }

        public int getNumberOfFilesInRpvXmlDirectory() {
            return numberOfFilesInRpvXmlDirectory;
        }

        public void setNumberOfFilesInRpvXmlDirectory(int numberOfFilesInRpvXmlDirectory) {
            this.numberOfFilesInRpvXmlDirectory = numberOfFilesInRpvXmlDirectory;
        }

        public String toString() {
            return "Date: " + recordTime + ", " +
                    "Proton directory file count: " + numberOfFilesInProtonDirectory + ", " +
                    "Rpv Xml directory file count: " + numberOfFilesInRpvXmlDirectory;
        }
    }
}
