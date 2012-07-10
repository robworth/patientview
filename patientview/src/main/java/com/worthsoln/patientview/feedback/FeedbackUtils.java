package com.worthsoln.patientview.feedback;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.Hibernate;

import java.util.List;

import com.worthsoln.HibernateUtil;


public class FeedbackUtils {

    static List getCommentsForUnit(String unitcode) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List edtaCodes = session.find("from " + Feedback.class.getName()
                + " as feedback where feedback.unitcode = ? order by feedback.datestamp desc",
                unitcode, Hibernate.STRING);

        tx.commit();
        HibernateUtil.closeSession();

        return edtaCodes;
    }

}
