package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class ResultHeadingUpdateAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultHeading resultHeading
                = LegacySpringUtils.getResultHeadingManager().get(BeanUtils.getProperty(form, "headingcode"));

        BeanUtils.setProperty(resultHeading, "heading", BeanUtils.getProperty(form, "heading"));
        BeanUtils.setProperty(resultHeading, "rollover", BeanUtils.getProperty(form, "rollover"));
        BeanUtils.setProperty(resultHeading, "link", BeanUtils.getProperty(form, "link"));
        BeanUtils.setProperty(resultHeading, "panel", BeanUtils.getProperty(form, "panel"));
        BeanUtils.setProperty(resultHeading, "panelorder", BeanUtils.getProperty(form, "panelorder"));

        LegacySpringUtils.getResultHeadingManager().save(resultHeading);

        request.setAttribute("resultHeading", resultHeading);

        return LogonUtils.logonChecks(mapping, request);
    }

}
