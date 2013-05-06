package com.worthsoln.patientview.letter;

import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LetterDeleteAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Long letterId = Long.parseLong(request.getParameter("id"));
        LegacySpringUtils.getLetterManager().delete(letterId);

        LetterDisplayAction.setUpLettersDisplay(mapping, request);

        return LogonUtils.logonChecks(mapping, request);
    }
}
