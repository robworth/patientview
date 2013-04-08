package com.worthsoln.patientview.launchpad;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class LaunchpadSelectAction extends Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchpadSelectAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        // Determine the Specialty selected and call manager to set Specialty in the session
        try {
            Long specialtyId = Long.decode(request.getParameter("specialtyId"));
            LegacySpringUtils.getSecurityUserManager().setLoggedInSpecialty(specialtyId);

        } catch (Exception e) {

            // for now just catch everything - there is no error page setup
            LOGGER.error("Failed to set Specialty from launchpad: {}", e.getMessage());
            // Note: the error redirect will log out the user
            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }
}
