package com.worthsoln.patientview.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.user.UserUtils;

import java.util.List;

public class PasswordResetAction{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String username = BeanUtils.getProperty(form, "username");
        Patient patient =  LegacySpringUtils.getPatientManager().get(BeanUtils.getProperty(form, "nhsno"),
                BeanUtils.getProperty(form, "unitcode"));

        String mappingToFind = "";
        User user = null;

        if (patient != null) {
            String password = LogonUtils.generateNewPassword();
            
            user = LegacySpringUtils.getUserManager().get(username);
            user.setPassword(LogonUtils.hashPassword(password));
            user.setFirstlogon(true);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PASSWORD_RESET,
                    user.getUsername(), "", UserUtils.retrieveUsersRealUnitcodeBestGuess(user.getUsername()), "");

            mappingToFind = "success";
        } else {
            request.setAttribute(LogonUtils.USER_ALREADY_EXISTS, username);

            mappingToFind = "input";
        }

        List<Unit> units = LegacySpringUtils.getUnitManager().getAll(false);

        request.setAttribute("units", units);
        request.setAttribute("user", user);
        request.setAttribute("patient", patient);

        return mapping.findForward(mappingToFind);
    }

}
