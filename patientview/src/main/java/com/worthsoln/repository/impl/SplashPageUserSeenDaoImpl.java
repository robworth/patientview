package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.SplashPageUserSeen_;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.SplashPageUserSeenDao;
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
@Repository(value = "splashPageUserSeenDao")
public class SplashPageUserSeenDaoImpl extends AbstractHibernateDAO<SplashPageUserSeen>
        implements SplashPageUserSeenDao {

    @Override
    public List<SplashPageUserSeen> getSeenForPatient(User user) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SplashPageUserSeen> criteria = builder.createQuery(SplashPageUserSeen.class);
        Root<SplashPageUserSeen> from = criteria.from(SplashPageUserSeen.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(SplashPageUserSeen_.username), user.getUsername()));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
