package com.worthsoln;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;

public class ShaAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        List<User> users = LegacySpringUtils.getUserManager().getAllUsers();
        for (User user : users) {
            user.setPassword(LogonUtils.hashPassword(user.getPassword()));
            LegacySpringUtils.getUserManager().save(user);
        }
        return mapping.findForward("success");
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
