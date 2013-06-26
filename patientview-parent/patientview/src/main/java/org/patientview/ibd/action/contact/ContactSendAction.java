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

package org.patientview.ibd.action.contact;

import org.patientview.ibd.action.BaseAction;
import org.patientview.patientview.EmailUtils;
import org.patientview.patientview.PatientUtils;
import org.patientview.patientview.model.Patient;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactSendAction extends BaseAction {

    private static final int HEALTH_FORM = 1;
    private static final int OTHER_FORM = 2;

    private static final String HEALTH_FORM_EMAIL = "myibdhealth@srft.nhs.uk";
    private static final String OTHER_FORM_EMAIL = "myibdportal@srft.nhs.uk";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        DynaActionForm dynaForm = (DynaActionForm) form;

        Integer type = (Integer) dynaForm.get("type");

        if (validateMessage(dynaForm)) {
            Patient patient = PatientUtils.retrievePatient(request);

            if (type == HEALTH_FORM) {
                sendHealthEmail(request, dynaForm, patient);
            } else if (type == OTHER_FORM) {
                sendOtherEmail(request, dynaForm, patient);
            } else {
                return mapping.findForward(INPUT);
            }

            request.setAttribute("emailSent", true);

            return mapping.findForward(SUCCESS);
        } else {
            if (type == HEALTH_FORM) {
                request.setAttribute("healthFormError", true);
            } else if (type == OTHER_FORM) {
                request.setAttribute("otherFormError", true);
            }

            return mapping.findForward(INPUT);
        }
    }

    private void sendHealthEmail(HttpServletRequest request, DynaActionForm form, Patient patient) {
        sendEmail(request, HEALTH_FORM_EMAIL, "IBD Question about health", createMessage(patient, form));
    }

    private void sendOtherEmail(HttpServletRequest request, DynaActionForm form, Patient patient) {
        sendEmail(request, OTHER_FORM_EMAIL, "IBD Question about details, the site, feedback",
                createMessage(patient, form));
    }

    private void sendEmail(HttpServletRequest request, String email, String subject, String message) {
        EmailUtils.sendEmail(request.getSession().getServletContext(), email, email, subject, message);
    }

    private boolean validateMessage(DynaActionForm form) {
        return form.get("message") != null && ((String) form.get("message")).length() > 0;
    }

    private String createMessage(Patient patient, DynaActionForm form) {
        String completeMessage = "";
        String message = "";
        String contact = "";

        if (form.get("message") != null) {
            message = "Message: " + form.get("message") + "\n\n";
        }

        if (form.get("email") != null) {
            contact = "Contact: " + form.get("email") + "\n\n";
        }

        completeMessage += "Patient name - " + patient.getForename() + " " + patient.getSurname() + "\n";
        completeMessage += "NHS no - " + patient.getNhsno() + "\n";
        completeMessage += "Contact - " + contact + "\n";
        completeMessage += "\n";
        completeMessage += "Message:" + "\n";
        completeMessage += "------------" + "\n";
        completeMessage += message + "\n";
        completeMessage += "------------" + "\n";

        return completeMessage;
    }
}
