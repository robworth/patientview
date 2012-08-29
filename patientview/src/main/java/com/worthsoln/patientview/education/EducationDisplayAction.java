package com.worthsoln.patientview.education;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class EducationDisplayAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        // pull back the static links for this tenancy and pass to view in the request
        EdtaCode edtaCode = LegacySpringUtils.getEdtaCodeManager().getEdtaCode("static");
        request.setAttribute(EdtaCode.getIdentifier(), edtaCode);
        return LogonUtils.logonChecks(mapping, request);
    }
}
