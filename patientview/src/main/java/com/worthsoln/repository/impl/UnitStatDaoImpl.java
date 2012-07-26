package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.patientview.model.UnitStat_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UnitStatDao;
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
@Repository(value = "unitStatDao")
public class UnitStatDaoImpl extends AbstractHibernateDAO<UnitStat> implements UnitStatDao {
    @Override
    public List<UnitStat> get(String unitCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UnitStat> criteria = builder.createQuery(UnitStat.class);
        Root<UnitStat> from = criteria.from(UnitStat.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UnitStat_.unitcode), unitCode));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
