package com.worthsoln.patientview.aboutme;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AboutmeUpdate extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = UserUtils.retrieveUser(request);

        String id = BeanUtils.getProperty(form, "id");
        String aboutme = BeanUtils.getProperty(form, "aboutme");
        String talkabout = BeanUtils.getProperty(form, "talkabout");
        String nhsno = BeanUtils.getProperty(form, "nhsno");

        Aboutme aboutMe = null;

        if (id == null || "".equals(id)) {
            UserMapping userMapping = UserUtils.retrieveUserMappingsPatientEntered(user);
            nhsno = userMapping.getNhsno();

            aboutMe = new Aboutme(nhsno, aboutme, talkabout);
        } else {
            Long idLong = Long.decode(id);

            aboutMe = new Aboutme(idLong, nhsno, aboutme, talkabout);
        }

        LegacySpringUtils.getAboutmeManager().save(aboutMe);

        request.setAttribute("user", user);
        request.setAttribute("aboutme", aboutMe);

        return LogonUtils.logonChecks(mapping, request, "success");
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "aboutme";
    }
}
