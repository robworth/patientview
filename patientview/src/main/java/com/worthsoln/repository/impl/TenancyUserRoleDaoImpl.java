package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.TenancyUserRole_;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.TenancyUserRoleDao;
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
@Repository(value = "tenancyUserRoleDao")
public class TenancyUserRoleDaoImpl extends AbstractHibernateDAO<TenancyUserRole>
        implements TenancyUserRoleDao {

    @Override
    public List<TenancyUserRole> get(User user) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TenancyUserRole> criteria = builder.createQuery(TenancyUserRole.class);
        Root<TenancyUserRole> from = criteria.from(TenancyUserRole.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(TenancyUserRole_.user), user));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
