package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

public class ResultHeadingUpdateAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultHeading resultHeading = new ResultHeading();

        BeanUtils.setProperty(resultHeading, "headingcode", BeanUtils.getProperty(form, "headingcode"));

        String[] colNames = HibernateUtil.getPropertyNames(resultHeading.getClass());

        for (int i = 0; i < colNames.length; i++) {
            BeanUtils.setProperty(resultHeading, colNames[i], BeanUtils.getProperty(form, colNames[i]));
        }

        LegacySpringUtils.getResultHeadingManager().save(resultHeading);

        HibernateUtil.closeSession();
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
