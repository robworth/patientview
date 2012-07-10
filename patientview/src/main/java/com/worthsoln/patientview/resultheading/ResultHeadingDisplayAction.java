package com.worthsoln.patientview.resultheading;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;

public class ResultHeadingDisplayAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List resultHeadings = session.find("from " + ResultHeading.class.getName()
                                           + " as heading order by heading.panel asc, heading.panelorder asc");

        tx.commit();
        HibernateUtil.closeSession();
        request.setAttribute("resultHeadings", resultHeadings);

        return LogonUtils.logonChecks(mapping, request);
    }
}
