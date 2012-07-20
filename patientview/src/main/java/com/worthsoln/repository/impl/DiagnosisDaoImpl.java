package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.DiagnosisDao;

import java.util.List;

/**
 *
 */
public class DiagnosisDaoImpl extends AbstractHibernateDAO<Diagnosis> implements DiagnosisDao {

    @Override
    public List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List diagnoses = session.find("from " + Diagnosis.class.getName() +
//                " as diagnosis where diagnosis.nhsno = ? AND diagnosis.unitcode = ? " +
//                " order by diagnosis.displayorder asc", new Object[]{nhsno, unitcode},
//                new Type[]{Hibernate.STRING, Hibernate.STRING});
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
