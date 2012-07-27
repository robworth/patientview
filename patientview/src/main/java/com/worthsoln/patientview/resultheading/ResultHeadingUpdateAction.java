package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

public class ResultHeadingUpdateAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultHeading resultHeading = new ResultHeading();

        // todo work out how to get the object prior to save, probably use PK id
        BeanUtils.setProperty(resultHeading, "headingcode", BeanUtils.getProperty(form, "headingcode"));
        BeanUtils.setProperty(resultHeading, "heading", BeanUtils.getProperty(form, "heading"));
        BeanUtils.setProperty(resultHeading, "rollover", BeanUtils.getProperty(form, "rollover"));
        BeanUtils.setProperty(resultHeading, "link", BeanUtils.getProperty(form, "link"));
        BeanUtils.setProperty(resultHeading, "panel", BeanUtils.getProperty(form, "panel"));
        BeanUtils.setProperty(resultHeading, "panelorder", BeanUtils.getProperty(form, "panelorder"));

        LegacySpringUtils.getResultHeadingManager().save(resultHeading);

        request.setAttribute("resultHeading", resultHeading);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "resultHeading";
    }
}
