package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.ResultHeading;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;

public class ResultHeadingEditAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HibernateUtil.retrievePersistentObjectAndAddToSession(request, ResultHeading.class, "headingcode",
            "resultHeading");

        return LogonUtils.logonChecks(mapping, request);
    }
}
