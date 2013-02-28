package com.worthsoln.patientview.user;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmailVerificationAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        String mappingToFind = "failure";

        String verificationCode = request.getParameter("v");

        if (LegacySpringUtils.getEmailVerificationManager().verify(verificationCode)) {
            mappingToFind = "success";
        }

        return mapping.findForward(mappingToFind);
    }

}