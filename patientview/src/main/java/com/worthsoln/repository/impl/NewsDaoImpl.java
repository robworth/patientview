package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.News;
import com.worthsoln.patientview.model.News_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.NewsDao;
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
@Repository(value = "newsDao")
public class NewsDaoImpl extends AbstractHibernateDAO<News> implements NewsDao {

    @Override
    public List<News> getNewsForEveryone() {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(News_.everyone), true));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getAdminNewsForUnitCodes(List<String> unitCodes) {

        unitCodes.add("all");   // some oddness to override stuff

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);

        Predicate unitCodePredicate = from.get(News_.unitcode).in(unitCodes.toArray(new String[unitCodes.size()]));
        Predicate adminPredicate = builder.equal(from.get(News_.admin), true);
        Predicate patientPredicate = builder.equal(from.get(News_.patient), true);
        Predicate everyonePredicate = builder.equal(from.get(News_.everyone), true);

        Predicate adminOrPatientPredicate =  getEntityManager().getCriteriaBuilder()
                .or(adminPredicate, patientPredicate);

        Predicate securedNews = getEntityManager().getCriteriaBuilder()
                .and(unitCodePredicate, adminOrPatientPredicate);

        Predicate fullPredicate = getEntityManager().getCriteriaBuilder()
                .or(securedNews, everyonePredicate);

        criteria.where(fullPredicate);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getAdminEditNewsForUnitCodes(List<String> unitCodes) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);

        Predicate unitCodePredicate = from.get(News_.unitcode).in(unitCodes.toArray(new String[unitCodes.size()]));
        Predicate adminPredicate = builder.equal(from.get(News_.admin), true);
        Predicate patientPredicate = builder.equal(from.get(News_.patient), true);

        Predicate adminOrPatientPredicate =  getEntityManager().getCriteriaBuilder()
                .or(adminPredicate, patientPredicate);

        Predicate securedNews = getEntityManager().getCriteriaBuilder()
                .and(unitCodePredicate, adminOrPatientPredicate);

        criteria.where(securedNews);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getPatientNewsForUnitCodes(List<String> unitCodes) {

        unitCodes.add("all");   // some oddness to override stuff

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);

        Predicate unitCodePredicate = from.get(News_.unitcode).in(unitCodes.toArray(new String[unitCodes.size()]));
        Predicate patientPredicate = builder.equal(from.get(News_.patient), true);
        Predicate everyonePredicate = builder.equal(from.get(News_.everyone), true);

        Predicate securedNews = getEntityManager().getCriteriaBuilder()
                .and(unitCodePredicate, patientPredicate);

        Predicate fullPredicate = getEntityManager().getCriteriaBuilder()
                .or(securedNews, everyonePredicate);

        criteria.where(fullPredicate);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
