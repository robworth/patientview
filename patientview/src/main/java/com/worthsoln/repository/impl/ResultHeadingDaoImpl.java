package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.model.ResultHeading_;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ResultHeadingDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
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
    public ResultHeading get(String headingcode, Tenancy tenancy) {

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
    public List<ResultHeading> getAll(Tenancy tenancy) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> root = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(ResultHeading_.tenancy), tenancy));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<ResultHeading> get(int panel, Tenancy tenancy) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> root = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(ResultHeading_.panel), panel));
        wherePredicates.add(builder.equal(root.get(ResultHeading_.tenancy), tenancy));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(ResultHeading_.panelorder)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void delete(String headingCode, Tenancy tenancy) {

        delete(get(headingCode, tenancy));
    }

    @Override
    public List<Panel> getPanels(Tenancy tenancy) {

        String sql = "SELECT DISTINCT panel FROM result_heading WHERE panel != 0 ORDER BY panel ASC";

        Query query = getEntityManager().createNativeQuery(sql);

        List resultsObjs = query.getResultList();
        List<Panel> results = new ArrayList<Panel>();
        for (Object object : resultsObjs) {
            results.add(new Panel((Integer) object));
        }

        return results;
    }
}
