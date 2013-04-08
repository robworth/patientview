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
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordChangeAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        /**
         *  This allows to change their email address, and forces them to change their password.
         *
         *  Note: this is used upon first login for users to complete their account and when patients change
         *  their password.
         *
         *  Note: there is also struts validation, see validation.xml
         */

        // receive data from submitted form
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();
        String suppliedOldPassword = BeanUtils.getProperty(form, "oldpassword");
        String actualOldPassword = user.getPassword();
        String hashedSuppliedOldPassword = LogonUtils.hashPassword(suppliedOldPassword);
        String emailAddress = BeanUtils.getProperty(form, "emailAddress");
        String emailAddressAgain = BeanUtils.getProperty(form, "emailAddressAgain");

        boolean errorFound = false;
        boolean sendVerificationEmail = true;

        // check the supplied current password matches what we have in the db
        if (!hashedSuppliedOldPassword.equals(actualOldPassword)) {
            request.setAttribute("passwordError", "Incorrect current password");
            errorFound = true;
        }

        // if both email boxes empty -> fine, and no validation email sent (this
        if (!StringUtils.hasLength(emailAddress) && !StringUtils.hasLength(emailAddressAgain)) {
            sendVerificationEmail = false;

        } else if (!emailAddress.equals(emailAddressAgain)) {
            // emails supplied, they must match
            request.setAttribute("emailError", "Email addresses don't match");
            errorFound = true;

        } else {
            // update the user's email with that supplied
            user.setEmail(emailAddress);
        }

        if (errorFound) {
            return mapping.findForward("input");
        } else {

            // ok so it worked, update the password, set the user not see this screen again, and save the email
            // change if it was made.
            user.setPassword(LogonUtils.hashPassword(BeanUtils.getProperty(form, "passwordPwd")));
            user.setFirstlogon(false);
            LegacySpringUtils.getUserManager().save(user);

            // db logging
            AddLog.addLog(user.getUsername(), AddLog.PASSWORD_CHANGE, user.getUsername(), "",
                    UserUtils.retrieveUsersRealUnitcodeBestGuess(user.getUsername()), "");

            // email verification - only required if the user has supplied an email address
            // (regardless of if it is the same as the one used to create by the admin)
            if (sendVerificationEmail) {
                EmailVerificationUtils.createEmailVerification(user.getUsername(), user.getEmail(), request);
                request.setAttribute("verificationMailSent", true);
            }

            return mapping.findForward("success");
        }
    }
}
