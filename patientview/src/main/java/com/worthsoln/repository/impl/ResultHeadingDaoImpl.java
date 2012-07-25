package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.model.ResultHeading_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ResultHeadingDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ResultHeadingDaoImpl extends AbstractHibernateDAO<ResultHeading> implements ResultHeadingDao {

    @Override
    public ResultHeading get(String headingcode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> from = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(ResultHeading_.headingcode), headingcode));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    @Override
    public void delete(String headingCode) {

        delete(get(headingCode));
    }
}
