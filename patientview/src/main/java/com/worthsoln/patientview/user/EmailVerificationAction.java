package com.worthsoln.patientview.user;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logging.AddLog;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class EmailVerificationAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        String mappingToFind = "failure";

        String verificationCode = request.getParameter("v");

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List emailVerifications = session.find("from " + EmailVerification.class.getName() + " as emailverification " +
                " where emailverification.verificationcode = ? " +
                " and emailverification.expirydatestamp > current_date", verificationCode, Hibernate.STRING);
        tx.commit();
        HibernateUtil.closeSession();


        if (!emailVerifications.isEmpty()) {
            EmailVerification emailVerification = (EmailVerification) emailVerifications.get(0);

            if (null != emailVerification) {
                User user = (User) HibernateUtil.getPersistentObject(User.class, emailVerification.getUsername());
                if (null != user) {
                    if (emailVerification.getEmail().equals(user.getEmail())) {
                        user.setEmailverified(true);
                        HibernateUtil.saveOrUpdateWithTransaction(user);

                        session = HibernateUtil.currentSession();
                        Transaction tx1 = session.beginTransaction();
                        session.delete(emailVerification);
                        tx1.commit();

                        AddLog.addLog(emailVerification.getUsername(), AddLog.EMAIL_VERIFY, emailVerification.getUsername(),
                                UserUtils.retrieveUsersRealNhsnoBestGuess(emailVerification.getUsername()), UserUtils.retrieveUsersRealUnitcodeBestGuess(emailVerification.getUsername()), emailVerification.getEmail());

                        mappingToFind = "success";
                    }
                }
            }
        }

        return mapping.findForward(mappingToFind);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}