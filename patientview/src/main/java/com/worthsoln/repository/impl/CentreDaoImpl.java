package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Centre;
import com.worthsoln.patientview.model.Centre_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.CentreDao;
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
@Repository(value = "centreDao")
public class CentreDaoImpl extends AbstractHibernateDAO<Centre> implements CentreDao {

    @Override
    public Centre get(String centreCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Centre> criteria = builder.createQuery(Centre.class);
        Root<Centre> from = criteria.from(Centre.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Centre_.centreCode), centreCode));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String centreCode) {

        Centre centre = get(centreCode);
        if (centre != null && centre.hasValidId()) {
            delete(centre);
        }
    }
}
