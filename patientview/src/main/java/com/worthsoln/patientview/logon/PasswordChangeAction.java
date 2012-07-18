package com.worthsoln.patientview.logon;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logging.AddLog;
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
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        User user = (User) HibernateUtil.getPersistentObject(User.class, username);
        String suppliedOldPassword = BeanUtils.getProperty(form, "oldpassword");
        String actualOldPassword = user.getPassword();
        String hashedSuppliedOldPassword = LogonUtils.hashPassword(suppliedOldPassword);
        if (hashedSuppliedOldPassword.equals(actualOldPassword)) {
            user.setPassword(LogonUtils.hashPassword(BeanUtils.getProperty(form, "passwordPwd")));
            user.setFirstlogon(false);
            HibernateUtil.saveOrUpdateWithTransaction(user);
            AddLog.addLog(user.getUsername(), AddLog.PASSWORD_CHANGE, user.getUsername(), "",
                    UserUtils.retrieveUsersRealUnitcodeBestGuess(username), "");
            return mapping.findForward("success");
        } else {
            request.setAttribute("error", "incorrect current password");
            return mapping.findForward("input");
        }
    }
}
