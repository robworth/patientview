package com.worthsoln.patientview.parser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.FindXmlFiles;
import com.worthsoln.patientview.ParserThread;

public class XmlParserThread implements Runnable, ParserThread {

    private String prebit;
    private String directory;
    private String archiveDirectory;
    private int minutesBetweenWait;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ServletContext servletContext;
    private String[] fileEndings;

    public XmlParserThread(String[] fileEndings) {
        this.fileEndings = fileEndings;
    }

    public XmlParserThread(ServletContext context, String xmlDirectory,
                           String xmlArchiveDirectory, int minutesBetweenWait) {
        this.directory = xmlDirectory;
        this.archiveDirectory = xmlArchiveDirectory;
        this.minutesBetweenWait = minutesBetweenWait;
        this.servletContext = context;
    }

    public void run() {
        try {
            while (true) {
                File[] xmlFiles = FindXmlFiles.findXmlFiles(directory, fileEndings);
                updateXmlFiles(xmlFiles);
                Thread.sleep(1000 * 60 * minutesBetweenWait);
                Date now = new Date(System.currentTimeMillis());
                System.out.println("XmlParserThread " + dateFormat.format(now));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateXmlFiles(File[] xmlFiles) {
        DatabaseDAO dao = new DatabaseDAO("patientview");
        for (int i = 0; i < xmlFiles.length; i++) {
            XmlParserUtils.updateXmlData(servletContext, xmlFiles[i], dao);
            //xmlFiles[i].delete();
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
