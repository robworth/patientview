package com.worthsoln.patientview.resultheading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

public class ResultHeadingAddAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultHeading resultHeading = new ResultHeading();

        BeanUtils.setProperty(resultHeading, "headingcode", BeanUtils.getProperty(form, "headingcode"));

        String[] colNames = HibernateUtil.getPropertyNames(resultHeading.getClass());

        for (int i = 0; i < colNames.length; i++) {
            BeanUtils.setProperty(resultHeading, colNames[i], BeanUtils.getProperty(form, colNames[i]));
        }

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.save(resultHeading);
        tx.commit();
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
