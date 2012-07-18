package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.PatientCount;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.PatientCountDao;

import java.util.List;

/**
 *
 */
public class PatientCountDaoImpl extends AbstractHibernateDAO<PatientCount> implements PatientCountDao {

    @Override
    public List<PatientCount> get(String unitCode, String role) {

//        Object[] parameters = new String[]{unitcode, "patient"};
//        Type[] types = new Type[]{Hibernate.STRING, Hibernate.STRING};
//
//        List<PatientCount> patientCounts =
//                session.find("from " + PatientCount.class.getName() + " patientcount " +
//                        "where patientcount.unitcode = ? and patientcount.role = ?", parameters, types);
//        tx.commit();
//        HibernateUtil.closeSession();


        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
