package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.User_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UserDao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 */
public class UserDaoImpl extends AbstractHibernateDAO<User> implements UserDao {

    @Override
    public User get(String username) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get(User_.username), username));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
