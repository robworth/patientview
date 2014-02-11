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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.patientview.ibd.action.BaseAction;
import org.patientview.model.Unit;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.JoinRequest;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactMyUnitSubmitAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        /**
         * validate form
         */
        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            /**
             * set bean values for success screen
             */
            List<Unit> units = LegacySpringUtils.getUnitManager().getAllDisregardingSpeciality(false);
            request.setAttribute("units", units);

            return mapping.findForward(INPUT);
        }

        // get data from form
        String firstName = BeanUtils.getProperty(form, "firstName");
        String lastName = BeanUtils.getProperty(form, "lastName");
        Date dateOfBirth = convertFormDateString("dateOfBirth", dynaForm);
        String nhsNo = BeanUtils.getProperty(form, "nhsNo");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String email = BeanUtils.getProperty(form, "email");

        JoinRequest joinRequest = new JoinRequest(firstName, lastName, dateOfBirth, nhsNo, unitcode, email,
                new GregorianCalendar().getTime());

        // send a mail to rpv admin
        sendJoinRequestEmailToRPVAdmin(request, unitcode, joinRequest);

        return LogonUtils.logonChecks(mapping, request);
    }

    private void sendJoinRequestEmailToRPVAdmin(HttpServletRequest request, String unitcode,
                                                       JoinRequest joinRequest) {
        // from
        String fromAddress = LegacySpringUtils.getContextProperties().getProperty("noreply.email");

        // to
        Unit unit = UnitUtils.retrieveUnit(unitcode);
        String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(request.getSession().getServletContext(), unit);

        // subject
        String subject = "[PatientView] Reset password request from patient";

        // body
        StringBuffer message = new StringBuffer();
        message.append("Hello,\n");
        message.append("\n");
        message.append("A patient at your unit has asked for help with their logon details. \n");
        message.append("\n");
        message.append("First name: ").append(joinRequest.getFirstName()).append("\n");
        message.append("Last name: ").append(joinRequest.getLastName()).append("\n");
        message.append("NHS No: ").append(joinRequest.getNhsNo()).append("\n");
        message.append("Unit code: ").append(joinRequest.getUnitcode()).append("\n");
        message.append("Date of birth: ").append(joinRequest.getDateOfBirthFormatted()).append("\n");
        message.append("Email address: ").append(joinRequest.getEmail()).append("\n");
        message.append("\n");
        message.append("Please verify these details and follow up this request with the patient ");
        message.append("using your usual process to send their logon details or reset their password.");
        message.append("\n");

        // send the mail
        EmailUtils.sendEmail(request.getSession().getServletContext(), fromAddress,
                toAddress, subject, message.toString());
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // Comment out the ones that do not require validation
        if (form.get("firstName") == null || ((String) form.get("firstName")).length() == 0) {
            actionErrors.add("firstName", new ActionMessage("firstName.required"));
        }

        if (form.get("lastName") == null || ((String) form.get("lastName")).length() == 0) {
            actionErrors.add("lastName", new ActionMessage("lastName.required"));
        }

        if (form.get("dateOfBirth") == null || ((String) form.get("dateOfBirth")).length() == 0) {
            actionErrors.add("dateOfBirth", new ActionMessage("dateOfBirth.required"));
        }

        if (StringUtils.isNotEmpty((String) form.get("nhsNo"))) {
            try {
                if (!UserUtils.isNhsNumberValid((String) form.get("nhsNo"))) {
                    actionErrors.add("nhsNo", new ActionMessage("nhsno.checksum"));
                }
            } catch (NumberFormatException e) {
                actionErrors.add("nhsNo", new ActionMessage("nhsno.checksum"));
            }
        } else {
            actionErrors.add("nhsNo", new ActionMessage("nhsno.checksum"));
        }

        if (form.get("unitcode") == null || ((String) form.get("unitcode")).length() == 0
                || (form.get("unitcode")).equals("-1")) {
            actionErrors.add("unitcode", new ActionMessage("unitcode.required"));
        }

        if (form.get("email") == null || ((String) form.get("email")).length() == 0) {
            actionErrors.add("email", new ActionMessage("email.required"));
        } else {
            Pattern p = Pattern.compile("^[A-Za-z0-9._%'-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
            Matcher m = p.matcher(form.get("email").toString());
            if (!m.matches()) {
                actionErrors.add("email", new ActionMessage("email.valid"));
            }
        }

        if (form.get("antiSpamAnswer") == null || !(form.get("antiSpamAnswer")).equals(
                request.getSession().getAttribute("ANTI_SPAM_ANSWER"))) {
            actionErrors.add("antiSpamAnswer", new ActionMessage("antispam.wrong.answer"));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
