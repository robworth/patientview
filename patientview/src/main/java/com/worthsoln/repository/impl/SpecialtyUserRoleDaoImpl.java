package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.SpecialtyUserRole_;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.SpecialtyUserRoleDao;
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
@Repository(value = "specialtyUserRoleDao")
public class SpecialtyUserRoleDaoImpl extends AbstractHibernateDAO<SpecialtyUserRole>
        implements SpecialtyUserRoleDao {

    @Override
    public List<SpecialtyUserRole> get(User user) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SpecialtyUserRole> criteria = builder.createQuery(SpecialtyUserRole.class);
        Root<SpecialtyUserRole> from = criteria.from(SpecialtyUserRole.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(SpecialtyUserRole_.user), user));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
