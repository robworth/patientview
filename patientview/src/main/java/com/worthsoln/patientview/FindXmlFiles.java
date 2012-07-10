package com.worthsoln.patientview;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class FindXmlFiles extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        putXmlFilesInRequest(request);
        return LogonUtils.logonChecks(mapping, request);
    }

    public static void putXmlFilesInRequest(HttpServletRequest request) {
        ServletContext context = request.getSession().getServletContext();
        String xmlDirPath = context.getInitParameter("xml.directory");
        File[] xmlFiles = findXmlFiles(xmlDirPath, new String[]{".xml"});
        request.setAttribute("xmlFiles", Arrays.asList(xmlFiles));
    }

    public static File[] findXmlFiles(String xmlDirPath, String[] fileEndings) {
        File xmlDir = new File(xmlDirPath);
        File[] xmlFiles = xmlDir.listFiles(new XmlFileFilter(fileEndings));
        Arrays.sort(xmlFiles, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
            }
        });
        return xmlFiles;
    }
}

class XmlFileFilter implements FilenameFilter {

    String[] fileEndings;

    public XmlFileFilter(String[] fileEndings) {
        this.fileEndings = fileEndings;
    }

    public boolean accept(File dir, String name) {
        boolean accepted = false;
        for (int i = 0; i < fileEndings.length; i++) {
            if (name.endsWith(fileEndings[i])) {
                accepted = true;
                break;
            }

        }
        return accepted;
    }
}
