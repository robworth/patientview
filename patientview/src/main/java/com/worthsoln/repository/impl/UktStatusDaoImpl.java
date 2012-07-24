package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.UktStatus;
import com.worthsoln.patientview.model.UktStatus_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UktStatusDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UktStatusDaoImpl extends AbstractHibernateDAO<UktStatus> implements UktStatusDao {

    @Override
    public UktStatus get(String nhsno) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UktStatus> criteria = builder.createQuery(UktStatus.class);
        Root<UktStatus> from = criteria.from(UktStatus.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UktStatus_.nhsno), nhsno));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getSingleResult();
    }
}
