package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPage_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.SplashPageDao;
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
@Repository(value = "splashPageDao")
public class SplashPageDaoImpl extends AbstractHibernateDAO<SplashPage> implements SplashPageDao {

    @Override
    public List<SplashPage> getAll(List<String> unitcodes) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SplashPage> criteria = builder.createQuery(SplashPage.class);
        Root<SplashPage> from = criteria.from(SplashPage.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        if (unitcodes != null && unitcodes.size() > 0) {
            wherePredicates.add(from.get(SplashPage_.unitcode).in(unitcodes.toArray(new String[unitcodes.size()])));
        }

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
