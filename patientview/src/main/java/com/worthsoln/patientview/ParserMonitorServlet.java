package com.worthsoln.patientview;

import com.worthsoln.patientview.parser.XmlParserThread;
import com.worthsoln.patientview.uktransplant.UktExportThread;
import com.worthsoln.patientview.uktransplant.UktParserThread;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ParserMonitorServlet extends HttpServlet {

    public void init() throws ServletException {
        super.init();
        String runThreads = getServletContext().getInitParameter("run.import.export.threads");
        if ((runThreads == null) || !"false".equals(runThreads)) {
            startParseThread("xml", new XmlParserThread(new String[]{".xml",}));
            //  startParseThread("dataout", new DataOutThread());
        }
        String runUktThreads = getServletContext().getInitParameter("run.ukt.threads");
        if ((runUktThreads == null) || !"false".equals(runUktThreads)) {
            startParseThread("ukt", new UktParserThread());
            startParseThread("uktexport", new UktExportThread());
        }
    }

    private void startParseThread(String prebit, ParserThread parserThread) {
        String directory = getServletContext().getInitParameter(prebit + ".directory");
        String archiveDirectory = getServletContext().getInitParameter(prebit + ".archive.directory");
        String minutesBetweenWaitString = getServletContext().getInitParameter(prebit + ".minutes.to.wait");
        int minutesBetweenWait = Integer.parseInt(minutesBetweenWaitString);
        parserThread.setPrebit(prebit);
        parserThread.setArchiveDirectory(archiveDirectory);
        parserThread.setDirectory(directory);
        parserThread.setMinutesBetweenWait(minutesBetweenWait);
        parserThread.setServletContext(getServletContext());
        Runnable runnable = (Runnable) parserThread;
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
