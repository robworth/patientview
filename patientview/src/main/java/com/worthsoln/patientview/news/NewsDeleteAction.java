package com.worthsoln.patientview.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;

public class NewsDeleteAction extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = BeanUtils.getProperty(form, "id");
        Integer idInt = Integer.decode(id);
        int newsid = idInt.intValue();
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.delete(new News(newsid));
        tx.commit();
        HibernateUtil.closeSession();
        NewsUtils.putAppropriateNewsForEditInRequest(request);
        return mapping.findForward("success");
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "news";
    }

}
