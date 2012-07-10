package com.worthsoln.patientview.splashpage;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SplashPageEditAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List splashpages = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            splashpages = session.find("from " + SplashPage.class.getName());
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        if (!splashpages.isEmpty()) {

            SplashPage splashPage = (SplashPage) splashpages.get(0);

            request.setAttribute("splashPage", splashPage);

        }

        return LogonUtils.logonChecks(mapping, request);
    }
}