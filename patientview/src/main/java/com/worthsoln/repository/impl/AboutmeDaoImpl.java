package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.repository.AboutmeDao;
import com.worthsoln.repository.AbstractHibernateDAO;

/**
 *
 */
public class AboutmeDaoImpl extends AbstractHibernateDAO<Aboutme> implements AboutmeDao {


    @Override
    public Aboutme get(String nhsno) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        aboutme = (Aboutme) session.createQuery("from " + Aboutme.class.getName() + " as aboutme where aboutme.nhsno = :nhsno ")
//                .setString("nhsno", nhsno)
//                .uniqueResult();
//
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
