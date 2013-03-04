package com.worthsoln.patientview;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.edtacode.EdtaCodeUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PatientDetailsAdminViewAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = ActionUtils.retrieveStringPropertyValue("username", form, request);

        request.getSession().setAttribute("userBeingViewedUsername", username);

        List<PatientDetails> patientDetails = LegacySpringUtils.getPatientManager().getPatientDetails(
                LegacySpringUtils.getUserManager().getLoggedInUser().getUsername());

        request.setAttribute("patientDetails", patientDetails);

        EdtaCodeUtils.addEdtaCodeToRequest("static", "staticLinks", request);

        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        return LogonUtils.logonChecks(mapping, request);
    }
}
