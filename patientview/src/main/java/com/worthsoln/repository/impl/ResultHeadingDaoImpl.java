package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.model.ResultHeading_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ResultHeadingDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "resultHeadingDao")
public class ResultHeadingDaoImpl extends AbstractHibernateDAO<ResultHeading> implements ResultHeadingDao {

    @Override
    public ResultHeading get(String headingcode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> from = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(ResultHeading_.headingcode), headingcode));

        buildWhereClause(criteria, wherePredicates);
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(String headingCode) {

        delete(get(headingCode));
    }
}
