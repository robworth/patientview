package com.worthsoln.forum;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.jforum.dao.DataAccessDriver;
import net.jforum.dao.UserDAO;
import net.jforum.sso.SSOUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScreenNameAction extends DatabaseAction {


    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String screenName = request.getParameter("screenname");
        if (screenName == null || screenName.trim().length() == 0) {
            return mapping.findForward("input");
        }
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();

        if (user != null) {
            user.setScreenname(screenName);
            LegacySpringUtils.getUserManager().save(user);
        }

        setJforumScreenname(user);

        return LogonUtils.logonChecks(mapping, request);
    }

    private void setJforumScreenname(User user) {
        SSOUtils ssoutils = new SSOUtils();

        if (!ssoutils.userExists(user.getUsername())) {
            net.jforum.entities.User jforumuser = new net.jforum.entities.User();

            jforumuser.setUsername(user.getUsername());
            jforumuser.setPassword("sso");
            String email = null == user.getEmail() ? "" : user.getEmail();
            jforumuser.setEmail(email);
            jforumuser.setFrom(user.getScreenname());
            jforumuser.setActive(1);

            UserDAO dao = DataAccessDriver.getInstance().newUserDAO();

            dao.addNew(jforumuser);

            net.jforum.entities.User addedjforumuser = dao.selectByName(user.getUsername());

            addedjforumuser.setFrom(user.getScreenname());

            dao.update(addedjforumuser);
        }
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "user";
    }
}
