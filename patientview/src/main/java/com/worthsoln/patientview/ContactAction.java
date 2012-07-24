package com.worthsoln.patientview;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.contact.Contact;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ContactAction extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        User user = UserUtils.retrieveUser(request);
        List<UserMapping> userMappings = UserUtils.retrieveUserMappings(user);

        List<Contact> contacts = new ArrayList();

        for (UserMapping userMapping : userMappings) {
            if (!UnitUtils.PATIENT_ENTERS_UNITCODE.equalsIgnoreCase(userMapping.getUnitcode())) {
                Patient patient = PatientUtils.retrievePatient(userMapping.getNhsno(), userMapping.getUnitcode(), getDao(request));

                Unit unit = UnitUtils.retrieveUnit(userMapping.getUnitcode());
                Contact contact = new Contact(patient, unit, userMapping);

                contacts.add(contact);
            }
        }

        request.setAttribute("contacts", contacts);

        DynaActionForm feedbackForm = (DynaActionForm) form;
        feedbackForm.set("anonymous", "true");

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "patient";
    }
}
