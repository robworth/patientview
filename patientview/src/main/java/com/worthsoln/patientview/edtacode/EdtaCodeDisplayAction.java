package com.worthsoln.patientview.edtacode;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.LinkType;
import com.worthsoln.patientview.logon.LogonUtils;

public class EdtaCodeDisplayAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String linkTypeMappingParameter = mapping.getParameter();
        List edtaCodes = EdtaCodeUtils.getCodeLinksForLinkType(linkTypeMappingParameter);

        request.setAttribute("edtaCodes", edtaCodes);
        request.setAttribute("codeType", new LinkType(mapping.getParameter()));

        return LogonUtils.logonChecks(mapping, request);
    }
}
