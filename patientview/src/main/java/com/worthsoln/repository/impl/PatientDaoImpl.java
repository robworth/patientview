package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.PatientDao;

import java.util.List;

/**
 *
 */
public class PatientDaoImpl extends AbstractHibernateDAO<Patient> implements PatientDao {

    @Override
    public List<Patient> get(String unitCode) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        patients = session.find("from " + Patient.class.getName() + " where centreCode = ?",
//                unitCode, Hibernate.STRING);
//
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
