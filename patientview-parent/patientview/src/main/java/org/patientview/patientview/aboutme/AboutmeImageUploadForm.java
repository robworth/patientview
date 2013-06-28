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

package org.patientview.patientview.aboutme;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;

public class AboutmeImageUploadForm extends ActionForm {

    private FormFile aboutmeImage;
    private String nhsno;

    private static final int FIVE_HUNDRED_KB = 500480;

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
        } else if (aboutmeImage.getFileSize() > FIVE_HUNDRED_KB) {            // If the file size is greater than 500kb
            errors.add("file", new ActionMessage("aboutme.image.file.size"));
        }
        return errors;
    }
}
