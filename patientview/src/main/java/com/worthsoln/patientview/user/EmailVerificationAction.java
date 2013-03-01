package com.worthsoln.patientview.user;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmailVerificationAction extends DatabaseAction {

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

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}