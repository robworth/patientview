package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Patient_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.PatientDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "patientDao")
public class PatientDaoImpl extends AbstractHibernateDAO<Patient> implements PatientDao {

    @Override
    public List<Patient> get(String centreCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
        Root<Patient> from = criteria.from(Patient.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Patient_.centreCode), centreCode));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
