package com.worthsoln.patientview.edtacode;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class InfoLinkDisplayAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List edtaCodes = EdtaCodeUtils.getCodeLinksForLinkType("edtaCode");
        List treatmentCodes = EdtaCodeUtils.getCodeLinksForLinkType("treatment");
        List staticCodes = EdtaCodeUtils.getCodeLinksForLinkType("static");

        request.setAttribute("edtaCodes", edtaCodes);
        request.setAttribute("treatmentCodes", treatmentCodes);
        request.setAttribute("staticCodes", staticCodes);

        return LogonUtils.logonChecks(mapping, request);
    }
}
