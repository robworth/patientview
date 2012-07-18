package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.EmailVerification;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.EmailVerificationDao;

import java.util.List;

/**
 *
 */
public class EmailVerificationDaoImpl extends AbstractHibernateDAO<EmailVerification> implements EmailVerificationDao {

    @Override
    public List<EmailVerification> get(String verificationCode) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List emailVerifications = session.find("from " + EmailVerification.class.getName() + " as emailverification " +
//                " where emailverification.verificationcode = ? " +
//                " and emailverification.expirydatestamp > current_date", verificationCode, Hibernate.STRING);
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(String username) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        session.delete("from " + EmailVerification.class.getName() + " as emailverification " +
//                " where emailverification.username = ?", username, Hibernate.STRING);
//        tx.commit();
    }
}
