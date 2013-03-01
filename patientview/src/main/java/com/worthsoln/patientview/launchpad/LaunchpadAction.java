package com.worthsoln.patientview.launchpad;

import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public class LaunchpadAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        // get available tenancies for the logged in user and add them to the struts context
        // the spring security should guarantee that we have a user here
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();

        if (user != null) {
            List<TenancyUserRole> tenancyUserRoles = LegacySpringUtils.getUserManager().getTenancyUserRoles(user);
            request.setAttribute("tenancyUserRoles", tenancyUserRoles);
        }

        return mapping.findForward("success");
    }
}
