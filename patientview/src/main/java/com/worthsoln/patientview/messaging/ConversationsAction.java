package com.worthsoln.patientview.messaging;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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

        List<Unit> units = getUnitManager().getLoggedInUsersUnits();

        List<User> recipients = new ArrayList<User>();

        // need to add in a list of recipients available to the user
        // if its a patient then they get unit admins in their unit
        // if an admin they get all the patients in their unit
        if (getSecurityUserManager().isRolePresent("unitadmin")) {
            for (Unit unit : units) {
                List<Patient> patients = getPatientManager().get(unit.getUnitcode());

                for (Patient patient : patients) {
                    //recipients.add(getUserManager().get(patient.get()));
                }
            }
        } else if (getSecurityUserManager().isRolePresent("patient")) {
            for (Unit unit : units) {
                List<UnitAdmin> unitAdmins = getUnitManager().getUnitUsers(unit.getUnitcode());

                for (UnitAdmin unitAdmin : unitAdmins) {
                    recipients.add(getUserManager().get(unitAdmin.getUsername()));
                }
            }
        }

        // order the recipients by name
        Collections.sort(recipients, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        request.setAttribute(Messaging.CONVERSATIONS_PARAM, getMessageManager().getConversations(user.getId()));
        request.setAttribute(Messaging.RECIPIENTS_PARAM, recipients);

        return mapping.findForward(SUCCESS);
    }
}
