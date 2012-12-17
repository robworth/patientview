package com.worthsoln.patientview.logon;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.user.EmailVerificationUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordChangeAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        // receive data from submitted form
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();
        String suppliedOldPassword = BeanUtils.getProperty(form, "oldpassword");
        String actualOldPassword = user.getPassword();
        String hashedSuppliedOldPassword = LogonUtils.hashPassword(suppliedOldPassword);
        String emailAddress = BeanUtils.getProperty(form, "emailAddress");
        String emailAddressAgain = BeanUtils.getProperty(form, "emailAddressAgain");

        if (!emailAddress.equals(emailAddressAgain)) {
            request.setAttribute("emailError", "email addresses don't match");

            return mapping.findForward("input");
        }

        if (!hashedSuppliedOldPassword.equals(actualOldPassword)) {
            request.setAttribute("passwordError", "incorrect current password");

            return mapping.findForward("input");
        }

        user.setPassword(LogonUtils.hashPassword(BeanUtils.getProperty(form, "passwordPwd")));
        user.setFirstlogon(false);
        LegacySpringUtils.getUserManager().save(user);
        AddLog.addLog(user.getUsername(), AddLog.PASSWORD_CHANGE, user.getUsername(), "",
                UserUtils.retrieveUsersRealUnitcodeBestGuess(user.getUsername()), "");

        EmailVerificationUtils.createEmailVerification(user.getUsername(), user.getEmail(), request);
        request.setAttribute("verificationMailSent", true);

        return mapping.findForward("success");
    }
}
