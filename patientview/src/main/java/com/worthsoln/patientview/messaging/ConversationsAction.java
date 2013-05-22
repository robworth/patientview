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

package com.worthsoln.patientview.messaging;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConversationsAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);

        // if the logged in user has not got an email set then show the no email message - only really for patients
        if (!StringUtils.hasText(user.getEmail())) {
            request.setAttribute(Messaging.NO_EMAIL_SET_PARAM, true);
        } else {
            List<Unit> units = getUnitManager().getLoggedInUsersUnits();

            // if its a super admin then they get the unit list to filter what users they need
            // other users just get the available ones for their units
            if (getSecurityUserManager().isRolePresent("superadmin")) {
                // sort units alpha
                Collections.sort(units, new Comparator<Unit>() {
                    @Override
                    public int compare(Unit o1, Unit o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });

                request.setAttribute(Messaging.UNITS_PARAM, units);
            } else {
                // patients and unit staff/admin get addresses for unit admin and staff
                List<User> unitAdminRecipients = getMessageManager().getUnitAdminRecipients(units, user);
                List<User> unitStaffRecipients = getMessageManager().getUnitStaffRecipients(units, user);
                List<User> unitPatientRecipients = new ArrayList<User>();

                // unit staff and admin also get patients
                if (getSecurityUserManager().isRolePresent("unitadmin")
                        || getSecurityUserManager().isRolePresent("unitstaff")) {
                    unitPatientRecipients = getMessageManager().getUnitPatientRecipients(units, user);
                }

                if (unitAdminRecipients.isEmpty() && unitStaffRecipients.isEmpty() && unitPatientRecipients.isEmpty()) {
                    request.setAttribute(Messaging.NO_RECIPIENTS_PARAM, true);
                } else {
                    request.setAttribute(Messaging.UNIT_ADMIN_RECIPIENTS_PARAM, unitAdminRecipients);
                    request.setAttribute(Messaging.UNIT_STAFF_RECIPIENTS_PARAM, unitStaffRecipients);
                    request.setAttribute(Messaging.UNIT_PATIENT_RECIPIENTS_PARAM, unitPatientRecipients);
                }
            }
        }

        boolean readerIsTheRecipient = user.getId().equals(getUserManager().getLoggedInUser().getId());
        if (!readerIsTheRecipient) {
            request.setAttribute(Messaging.IS_READER_THE_RECIPIENT, "false");
        }

        request.setAttribute(Messaging.CONVERSATIONS_PARAM, getMessageManager().getConversations(user.getId()));

        return mapping.findForward(SUCCESS);
    }
}
