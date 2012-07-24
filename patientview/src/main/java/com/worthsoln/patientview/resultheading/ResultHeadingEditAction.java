package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class ResultHeadingEditAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ResultHeading resultHeading
                = LegacySpringUtils.getResultHeadingManager().get(request.getParameter("headingcode"));

        request.getSession().setAttribute("resultHeading", resultHeading);

        return LogonUtils.logonChecks(mapping, request);
    }
}
