package com.worthsoln.patientview.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.security.PatientViewAuthenticationFailureHandler;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogonErrorAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        // display the locked out message if that's the case
        String lockedOut = (String) request.getSession()
                .getAttribute(PatientViewAuthenticationFailureHandler.ACCOUNT_LOCKED_SESSION_TOKEN);

        if (lockedOut != null) {
            request.setAttribute("lockedOut", true);
        }
        return mapping.findForward("success");
    }

}

