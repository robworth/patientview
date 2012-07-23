package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ResultHeadingDao;

import java.util.List;

/**
 *
 */
public class ResultHeadingDaoImpl extends AbstractHibernateDAO<ResultHeading> implements ResultHeadingDao {

    @Override
    public ResultHeading get(String headingcode) {

        // todo

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ResultHeading> getAll() {
        // customize ordering

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List resultHeadings = session.find("from " + ResultHeading.class.getName()
//                + " as heading order by heading.panel asc, heading.panelorder asc");
//
//        tx.commit();
//        HibernateUtil.closeSession();
//
        return null;
    }

    @Override
    public void delete(String headingCode) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        session.delete(new ResultHeading(resultHeadingId));
//        tx.commit();
//        HibernateUtil.closeSession();
    }
}
