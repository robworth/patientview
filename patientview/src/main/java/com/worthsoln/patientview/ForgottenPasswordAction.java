package com.worthsoln.patientview;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgottenPasswordAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String forwardMapping = "success";

        if (username != null && username.trim().length() > 0 && email != null && email.trim().length() > 0) {
            User user = LegacySpringUtils.getUserManager().get(username);

            request.setAttribute("foundUser", user != null);

            if (user != null) {
                String storedEmail = user.getEmail();
                if (storedEmail != null && user.getEmail().trim().length() > 0) {
                    if (email.equalsIgnoreCase(storedEmail)) {
                        String password = LogonUtils.generateNewPassword();
                        user.setPassword(LogonUtils.hashPassword(password));
                        user.setFirstlogon(true);

                        // Email password
                        String message = "Hello User,\n" +
                                "\n" +
                                "Your password has been reset, your new password is\n" +
                                password + "\n" +
                                "\n" +
                                "Renal Patient View";
                        String fromAddress = request.getSession().getServletContext().getInitParameter("noreply.email");
                        EmailUtils.sendEmail(request.getSession().getServletContext(), fromAddress, user.getEmail(),
                                "Renal Patient View - Your password has been reset", message);
                        LegacySpringUtils.getUserManager().save(user);

                        AddLog.addLog("system", AddLog.PASSWORD_RESET_FORGOTTEN, username, "",
                                UserUtils.retrieveUsersRealUnitcodeBestGuess(username), user.getEmail());
                    } else {
                        request.setAttribute("noMatch", true);
                        forwardMapping = "input";
                    }
                } else {
                    request.setAttribute("nullEmail", true);
                    forwardMapping = "input";
                }
            } else {
                request.setAttribute("nullUser", true);
                forwardMapping = "input";
            }
        } else {
            request.setAttribute("nullUser", true);
            forwardMapping = "input";
        }
        return mapping.findForward(forwardMapping);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "user";
    }
}