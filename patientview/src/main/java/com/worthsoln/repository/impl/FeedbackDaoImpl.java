package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Feedback;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.FeedbackDao;

import java.util.List;

/**
 *
 */
public class FeedbackDaoImpl extends AbstractHibernateDAO<Feedback> implements FeedbackDao {

    @Override
    public List<Feedback> get(String unitcode) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List edtaCodes = session.find("from " + Feedback.class.getName()
//                + " as feedback where feedback.unitcode = ? order by feedback.datestamp desc",
//                unitcode, Hibernate.STRING);
//
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
