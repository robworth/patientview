package com.worthsoln.patientview;

import javax.servlet.ServletContext;

public interface ParserThread {

    String getPrebit();

    void setPrebit(String prebit);

    String getDirectory();

    void setDirectory(String directory);

    String getArchiveDirectory();

    void setArchiveDirectory(String archiveDirectory);

    int getMinutesBetweenWait();

    void setMinutesBetweenWait(int minutesBetweenWait);

    ServletContext getServletContext();

    void setServletContext(ServletContext servletContext);
}
