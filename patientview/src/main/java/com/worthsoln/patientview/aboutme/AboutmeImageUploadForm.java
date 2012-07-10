package com.worthsoln.patientview.aboutme;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;

public class AboutmeImageUploadForm extends ActionForm {
    private FormFile aboutmeImage;
    private String nhsno;

    public FormFile getAboutmeImageFile() {
        return aboutmeImage;
    }

    public void setAboutmeImageFile(FormFile theFile) {
        this.aboutmeImage = theFile;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (aboutmeImage.getFileSize() == 0) {
            errors.add("file", new ActionMessage("aboutme.image.file.required"));
        } else if (!aboutmeImage.getContentType().equals("image/jpeg")
                && !aboutmeImage.getContentType().equals("image/gif")
                && !aboutmeImage.getContentType().equals("image/png")) {
            errors.add("file", new ActionMessage("aboutme.image.file.type"));
        }
        /**
         * If the file size is greater than 500kb.
         */
        else if (aboutmeImage.getFileSize() > 500480) {
            errors.add("file", new ActionMessage("aboutme.image.file.size"));
        }
        return errors;
    }

}