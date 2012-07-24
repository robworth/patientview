package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.patientview.model.Diagnosis_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.DiagnosisDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DiagnosisDaoImpl extends AbstractHibernateDAO<Diagnosis> implements DiagnosisDao {

    @Override
    public List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode) {
        // when unit codes are stored they are put in uppercase
        unitcode = unitcode.toUpperCase();

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnosis> criteria = builder.createQuery(Diagnosis.class);
        Root<Diagnosis> diagnosisRoot = criteria.from(Diagnosis.class);

        criteria.where(builder.equal(diagnosisRoot.get(Diagnosis_.nhsno), nhsno),
                builder.equal(diagnosisRoot.get(Diagnosis_.unitcode), unitcode));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
