package com.worthsoln.patientview.edtacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.LinkType;
import com.worthsoln.patientview.logon.LogonUtils;

public class EdtaCodeAddInputAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().setAttribute("codeType", new LinkType(BeanUtils.getProperty(form, "linkType")));

        return LogonUtils.logonChecks(mapping, request);
    }
}
