package com.worthsoln.patientview.aboutme;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AboutmeDisplay extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = UserUtils.retrieveUser(request);

        Aboutme aboutme = AboutmeUtils.fetchAboutmeForPatient(user);

        request.setAttribute("user", user);
        
        if (null != aboutme) {
            request.setAttribute("aboutme", aboutme);
            return LogonUtils.logonChecks(mapping, request);
        } else {
            return LogonUtils.logonChecks(mapping, request, "editaboutme");
        }
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "aboutme";
    }
}