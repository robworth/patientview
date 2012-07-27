package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.patientview.model.Diagnosis_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.DiagnosisDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "diagnosisDao")
public class DiagnosisDaoImpl extends AbstractHibernateDAO<Diagnosis> implements DiagnosisDao {

    @Override
    public List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode) {
        // when unit codes are stored they are put in uppercase
        unitcode = unitcode.toUpperCase();

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnosis> criteria = builder.createQuery(Diagnosis.class);
        Root<Diagnosis> diagnosisRoot = criteria.from(Diagnosis.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(diagnosisRoot.get(Diagnosis_.nhsno), nhsno));
        wherePredicates.add(builder.equal(diagnosisRoot.get(Diagnosis_.unitcode), unitcode));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
