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

public class PasswordUnlockAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        User user = LegacySpringUtils.getUserManager().get(username);

        String mappingToFind = "";

        if (user != null) {
            user.setFailedlogons(0);
            user.setAccountlocked(false);
            LegacySpringUtils.getUserManager().save(user);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PASSWORD_UNLOCKED,
                    user.getUsername(), "",
                    UserUtils.retrieveUsersRealUnitcodeBestGuess(username), "");

            mappingToFind = "success";
        }

        List<Unit> units = LegacySpringUtils.getUnitManager().getAll(false);
        request.setAttribute("units", units);
        request.setAttribute("user", user);

        return mapping.findForward(mappingToFind);
    }
}