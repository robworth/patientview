package com.worthsoln.patientview.logon;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.NhsnoUnitcode;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientEditInputAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = ActionUtils.retrieveStringPropertyValue("username", form, request);
        String unitcode = ActionUtils.retrieveStringPropertyValue("unitcode", form, request);
        String nhsno = ActionUtils.retrieveStringPropertyValue("nhsno", form, request);
        User user = LegacySpringUtils.getUserManager().get(username);
        // String nhsno = UserUtils.retrieveUsersRealNhsnoBestGuess(username);
        NhsnoUnitcode nhsnoThing = new NhsnoUnitcode(nhsno, unitcode);
        request.getSession().setAttribute("patient", user);
        request.setAttribute("nhsnot", nhsnoThing);
        return LogonUtils.logonChecks(mapping, request);
    }
}

