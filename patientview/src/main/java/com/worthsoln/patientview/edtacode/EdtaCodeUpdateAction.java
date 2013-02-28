package com.worthsoln.patientview.edtacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class EdtaCodeUpdateAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EdtaCode edtaCode = LegacySpringUtils.getEdtaCodeManager().getEdtaCode(BeanUtils.getProperty(form, "edtaCode"));

        EdtaCodeUtils.build(form, edtaCode);

        LegacySpringUtils.getEdtaCodeManager().save(edtaCode);

        request.setAttribute("edtaCode", edtaCode);

        return LogonUtils.logonChecks(mapping, request);
    }

}
