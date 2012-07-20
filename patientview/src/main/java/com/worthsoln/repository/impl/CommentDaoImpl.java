package com.worthsoln.repository.impl;

import com.worthsoln.patientview.Panel;
import com.worthsoln.patientview.model.Comment;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.CommentDao;

import java.util.List;

/**
 *
 */
public class CommentDaoImpl extends AbstractHibernateDAO<Comment> implements CommentDao {

    @Override
    public List<Comment> get(String nhsno) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        comments = session.find("from " + Comment.class.getName() + " where nhsno = ? " ,
//                patient.getNhsno(), Hibernate.STRING);
//
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Comment> get(String nhsno, Panel currentPanel) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        String thisPanel = (currentPanel == null) ? "1" : Integer.toString(currentPanel.getPanel());
//
//
//        comments = session.find("from " + Comment.class.getName() + " as comment," + ResultHeading.class.getName() +
//                " as result_heading where comment.nhsno = ? " +
//                " and result_heading.headingcode = 'resultcomment' and result_heading.panel = ?",
//                new Object[]{nhsno, thisPanel}, new Type[]{Hibernate.STRING, Hibernate.STRING});
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
