package com.worthsoln.patientview.aboutme;

import com.worthsoln.patientview.model.Aboutme;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;

public class AboutmeImageUploadAction extends Action {


    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AboutmeImageUploadForm myForm = (AboutmeImageUploadForm) form;

        FormFile myFile = myForm.getAboutmeImageFile();
        String nhsno = myForm.getNhsno();
        String fileName = myFile.getFileName();

        ServletContext context = request.getSession().getServletContext();
        String aboutmeImageDirPath = context.getInitParameter("aboutme.image.directory");

        if (!fileName.equals("")) {
            File fileToCreate = new File(aboutmeImageDirPath, nhsno);

            if (fileToCreate.exists()) {
                fileToCreate.delete();
            }

            if (!fileToCreate.exists()) {
                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                fileOutStream.write(myFile.getFileData());
                fileOutStream.flush();
                fileOutStream.close();
            }
        }

        User user = UserUtils.retrieveUser(request);
        Aboutme aboutme = AboutmeUtils.fetchAboutmeForPatient(user);

        request.setAttribute("aboutme", aboutme);

        request.setAttribute("fileName", fileName);
        request.setAttribute("nhsno", nhsno);

        return mapping.findForward("success");
    }
}
