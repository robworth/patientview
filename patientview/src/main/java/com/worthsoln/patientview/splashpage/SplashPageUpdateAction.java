package com.worthsoln.patientview.splashpage;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logon.LogonUtils;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SplashPageUpdateAction extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SplashPage splashPage = new SplashPage();

//        BeanUtils.setProperty(splashPage, "splashPage", BeanUtils.getProperty(form, "splashPage"));

        BeanUtils.setProperty(splashPage, "id", BeanUtils.getProperty(form, "id"));
        BeanUtils.setProperty(splashPage, "name", BeanUtils.getProperty(form, "name"));
        BeanUtils.setProperty(splashPage, "headline", BeanUtils.getProperty(form, "headline"));
        BeanUtils.setProperty(splashPage, "bodytext", BeanUtils.getProperty(form, "bodytext"));
        String stringLive  = BeanUtils.getProperty(form, "live");
        boolean isLive = "true".equals(stringLive);
        splashPage.setLive(isLive);

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(splashPage);
        tx.commit();
        HibernateUtil.closeSession();
        request.setAttribute("splashPage", splashPage);
        HibernateUtil.retrievePersistentObjectAndAddToRequest(request, SplashPage.class, SplashPage.getIdentifier());

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "splashPage";
    }
}