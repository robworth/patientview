package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class ResultHeadingDeleteAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String resultHeadingId = request.getParameter("headingcode");

        LegacySpringUtils.getResultHeadingManager().delete(resultHeadingId);

        return LogonUtils.logonChecks(mapping, request);
    }

}
