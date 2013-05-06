package com.worthsoln.patientview.letter;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LetterDisplayAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        setUpLettersDisplay(mapping, request);

        return LogonUtils.logonChecks(mapping, request);
    }

    static void setUpLettersDisplay(ActionMapping mapping, HttpServletRequest request) {
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);
        List letters = LegacySpringUtils.getLetterManager().get(user.getUsername());
        request.setAttribute("letters", letters);
        request.setAttribute("user", user);
    }
}
