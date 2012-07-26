package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.Unit_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UnitDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Repository(value = "unitDao")
public class UnitDaoImpl extends AbstractHibernateDAO<Unit> implements UnitDao {

    @Override
    public Unit get(String unitCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Unit_.unitcode), unitCode));

        buildWhereClause(criteria, wherePredicates);
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Unit> getAll(boolean sortByName) {

        if (sortByName) {

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
            Root<Unit> from = criteria.from(Unit.class);

            criteria.orderBy(builder.asc(from.get(Unit_.name)));

            return getEntityManager().createQuery(criteria).getResultList();
        } else {
            return getAll();
        }
    }

    @Override
    public List<Unit> getUnitsWithUser() {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.isNotNull(from.get(Unit_.unituser)));
        wherePredicates.add(builder.notEqual(from.get(Unit_.unituser), ""));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<Unit> get(List<String> usersUnitCodes) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(from.get(Unit_.unitcode).in(usersUnitCodes.toArray(new String[usersUnitCodes.size()])));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<Unit> get(List<String> usersUnitCodes, String[] notTheseUnitCodes, String[] plusTheseUnitCodes) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        // add in the additional ones, only add in if we have usersUnitCodes, otherwise we need all of them
        if (plusTheseUnitCodes != null && plusTheseUnitCodes.length > 0 && usersUnitCodes != null
                && usersUnitCodes.size() > 0) {
            Collections.addAll(usersUnitCodes, plusTheseUnitCodes);
        }

        if (usersUnitCodes != null && usersUnitCodes.size() > 0) {
            wherePredicates.add(from.get(Unit_.unitcode).in(usersUnitCodes.toArray(new String[usersUnitCodes.size()])));
        }

        if (notTheseUnitCodes != null) {
            for (String notUnitCode : notTheseUnitCodes) {
                wherePredicates.add(builder.notEqual(from.get(Unit_.unitcode), notUnitCode));
            }
        }

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(from.get(Unit_.name)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
