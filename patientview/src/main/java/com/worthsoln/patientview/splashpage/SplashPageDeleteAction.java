package com.worthsoln.patientview.splashpage;

import com.worthsoln.HibernateUtil;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SplashPageDeleteAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = BeanUtils.getProperty(form, "id");
        Integer idInt = Integer.decode(id);
        int splashPageId = idInt.intValue();

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.delete(new SplashPage(splashPageId));
        tx.commit();
        HibernateUtil.closeSession();

        SplashPageUtils.removeSplashPagesSeen(splashPageId);

        List<SplashPage> splashpages = SplashPageUtils.retrieveSplashPages(request);
        request.setAttribute("splashpages", splashpages);

        return mapping.findForward("success");
    }
}