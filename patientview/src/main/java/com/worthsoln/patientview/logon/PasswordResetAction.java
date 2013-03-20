package com.worthsoln.patientview.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.user.UserUtils;

import java.util.List;

public class PasswordResetAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String username = BeanUtils.getProperty(form, "username");
        User user = LegacySpringUtils.getUserManager().get(username);

        String mappingToFind = "";

        if (user != null) {
            String password = LogonUtils.generateNewPassword();
            user.setPassword(LogonUtils.hashPassword(password));
            user.setFirstlogon(true);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PASSWORD_RESET,
                    user.getUsername(), "", UserUtils.retrieveUsersRealUnitcodeBestGuess(user.getUsername()), "");

            LegacySpringUtils.getUserManager().save(user);

            request.setAttribute("plaintextPassword", password);
            request.setAttribute("passwordUpdated", true);
            mappingToFind = "success";
        } else {

            request.setAttribute("passwordUpdateError", true);
            mappingToFind = "error";
        }

        request.setAttribute("user", user);

        return mapping.findForward(mappingToFind);
    }

}
