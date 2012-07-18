package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class ResultHeadingDisplayAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setAttribute("resultHeadings", LegacySpringUtils.getResultHeadingManager().getAll());

        return LogonUtils.logonChecks(mapping, request);
    }
}
