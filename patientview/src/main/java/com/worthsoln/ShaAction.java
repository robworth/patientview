package com.worthsoln;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.LogonUtils;

public class ShaAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<User> users = session.find("from " + User.class.getName() + "");
        tx.commit();
        HibernateUtil.closeSession();
        for (User user : users) {
            user.setPassword(LogonUtils.hashPassword(user.getPassword()));
            HibernateUtil.saveOrUpdateWithTransaction(user);

        }
        return mapping.findForward("success");
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
