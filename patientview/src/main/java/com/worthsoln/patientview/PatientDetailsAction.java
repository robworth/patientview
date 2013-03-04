package com.worthsoln.patientview;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.edtacode.EdtaCodeUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.news.NewsUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PatientDetailsAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        NewsUtils.putAppropriateNewsForViewingInRequest(request);

        List<PatientDetails> patientDetails = LegacySpringUtils.getPatientManager().getPatientDetails(
                LegacySpringUtils.getUserManager().getLoggedInUser().getUsername());

        request.setAttribute("patientDetails", patientDetails);

        // this form is only used for ibd for now, so just check it exist before trying to use it
        if (form != null && form instanceof DynaActionForm && patientDetails != null && patientDetails.size() > 0) {
            // add the editable ibd only patient details
            DynaActionForm dynaForm = (DynaActionForm) form;
            // let's just store this info against the first patient object if there are many for this nhsno
            dynaForm.set("patientId", patientDetails.get(0).getPatient().getId());
            dynaForm.set("otherConditions", patientDetails.get(0).getPatient().getOtherConditions());
            dynaForm.set("email", LegacySpringUtils.getUserManager().getLoggedInUser().getEmail());
        }

        EdtaCodeUtils.addEdtaCodeToRequest("static", "staticLinks", request);

        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        return LogonUtils.logonChecks(mapping, request);
    }
}
