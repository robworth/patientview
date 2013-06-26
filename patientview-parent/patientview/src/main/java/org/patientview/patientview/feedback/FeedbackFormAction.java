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

package org.patientview.patientview.feedback;

import org.patientview.patientview.EmailUtils;
import org.patientview.patientview.PatientUtils;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.Feedback;
import org.patientview.patientview.model.Patient;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackFormAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        if (username != null) {
            User user = LegacySpringUtils.getUserManager().get(username);

            String unitcode = BeanUtils.getProperty(form, "unitcode");
            String nhsno = BeanUtils.getProperty(form, "nhsno");
            String comment = BeanUtils.getProperty(form, "comment");
            String anonymous = BeanUtils.getProperty(form, "anonymous");
            //String nhsno = (null != user.getNhsno()) ? user.getNhsno() : "";
            boolean isAnonymous = "on".equals(anonymous);

            Feedback feedback = new Feedback(user.getUsername(), user.getName(), nhsno, unitcode, comment, isAnonymous);

            LegacySpringUtils.getFeedbackManager().save(feedback);

            emailUnitAdminFeedbackNotification(request, feedback);

            Patient patient = PatientUtils.retrievePatient(request);

            if (patient != null) {
                request.setAttribute("patient", patient);

                Unit unit = LegacySpringUtils.getUnitManager().get(patient.getCentreCode());
                request.setAttribute("unit", unit);
            } else if (!LegacySpringUtils.getSecurityUserManager().isRolePresent("patient")) {
                return LogonUtils.logonChecks(mapping, request, "control");
            }

            DynaActionForm feedbackForm = (DynaActionForm) form;
            feedbackForm.set("anonymous", "true");
            feedbackForm.set("comment", "");

            request.setAttribute("commentSent", true);
        }
        return mapping.findForward("success");
    }

    private void emailUnitAdminFeedbackNotification(HttpServletRequest request, Feedback feedback) {
        ServletContext context = request.getSession().getServletContext();
        String fromAddress = context.getInitParameter("noreply.email");
        Unit unit = UnitUtils.retrieveUnit(feedback.getUnitcode());
        String toAddress = unit.getRenaladminemail();
        String subject = "[Renal PatientView] New feedback for your unit - " + unit.getShortname();

        String newLine = System.getProperty("line.separator");
        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]" + newLine;
        emailBody += newLine;
        emailBody += "A patient has posted some feedback about " + unit.getShortname()
                + ". Please login to Renal PatientView to see the feedback in full "
                + "and approve it for viewing by other patients." + newLine;
        emailBody += newLine;
        emailBody += "The comment is as follows:" + newLine;
        emailBody += newLine;
        emailBody += feedback.getComment() + newLine;
        emailBody += newLine;
        if (feedback.isAnonymous()) {
            emailBody += "The feedback is anonymous." + newLine;
            emailBody += newLine;
        } else {
            emailBody += "The feedback is not anonymous and it was submitted by: " + newLine;
            emailBody += "Name: " + feedback.getName() + newLine;
            emailBody += "NHS No: " + feedback.getNhsno() + newLine;
            emailBody += newLine;
        }
        emailBody += newLine;
        emailBody += newLine;
        emailBody += "------------------------" + newLine;
        emailBody += newLine;
        emailBody += "Please note that Renal PatientView will never send you an email with link to click "
                + "to ask you to log in to do anything. If you ever get an email like that, please let us know, "
                + "because it it probably some kind of scam or phishing attempt." + newLine;

        EmailUtils.sendEmail(context, fromAddress, toAddress, subject, emailBody);
    }
}
