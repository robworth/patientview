package com.worthsoln.patientview;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logon.LogonUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;


public class XmlFileViewAction extends DatabaseAction {
    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String xmlFilename = request.getParameter("xmlfile");

        String directory = request.getSession().getServletContext().getInitParameter("xml.patient.data.load.directory");

        File xmlFile = new File(directory, xmlFilename);

        if (xmlFile.exists()) {
            String xmlContent = readFile(xmlFile.getCanonicalPath());  
            request.setAttribute("xmlContent", xmlContent);
        }

        request.setAttribute("xmlFilename", xmlFilename);
        return LogonUtils.logonChecks(mapping, request);
    }

    private static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally {
            stream.close();
        }
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "patient";
    }
}
