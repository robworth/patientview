package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.PatientCount;
import com.worthsoln.patientview.model.PatientCount_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.PatientCountDao;
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
@Repository(value = "patientCountDao")
public class PatientCountDaoImpl extends AbstractHibernateDAO<PatientCount> implements PatientCountDao {

    @Override
    public List<PatientCount> get(String unitCode, String role) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PatientCount> criteria = builder.createQuery(PatientCount.class);
        Root<PatientCount> from = criteria.from(PatientCount.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(PatientCount_.unitcode), unitCode));
        wherePredicates.add(builder.equal(from.get(PatientCount_.role), role));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
