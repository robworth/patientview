package com.worthsoln.patientview.edtacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class EdtaCodeDeleteAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String edtaCodeId = request.getParameter("edtaCode");

        LegacySpringUtils.getEdtaCodeManager().delete(edtaCodeId);

        return LogonUtils.logonChecks(mapping, request);
    }

}
