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
            if (getSecurityUserManager().isRolePresent("superadmin") || getSecurityUserManager().isRolePresent("unitadmin")) {
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

                if (getSecurityUserManager().isRolePresent("unitadmin")) {
                    request.setAttribute(Messaging.IS_UNIT_ADMIN_PARAM, true);
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
