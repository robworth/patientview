/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.patientview;

import org.patientview.patientview.logon.LogonUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

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

    private String[] fileEndings;

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
