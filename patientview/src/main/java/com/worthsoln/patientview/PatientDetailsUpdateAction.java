package com.worthsoln.patientview;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class PatientDetailsUpdateAction extends BaseAction {


    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        // pull back the patient and the user and store if the data has been supplied
        Long patientId = (Long) dynaForm.get("patientId");
        String otherConditions = (String) dynaForm.get("otherConditions");
        String email = (String) dynaForm.get("email");

        if (email != null) {
            // update the user's email
            User user = LegacySpringUtils.getUserManager().getLoggedInUser();
            user.setEmail(email);
            LegacySpringUtils.getUserManager().save(user);
        }

        if (otherConditions != null && patientId != null) {
            Patient patient = LegacySpringUtils.getPatientManager().get(patientId);
            patient.setOtherConditions(otherConditions);
            LegacySpringUtils.getPatientManager().save(patient);
        }

        return mapping.findForward(SUCCESS);
    }

    // todo
    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();
        return true;
    }
}
