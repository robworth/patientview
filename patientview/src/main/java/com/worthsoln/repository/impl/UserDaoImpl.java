package com.worthsoln.repository.impl;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.User_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 */
@Repository(value = "userDao")
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

    @Override
    public List<UnitAdmin> getUnitUsers(String unitcode, Tenancy tenancy) {

        DatabaseDAO dao = new DatabaseDAO("patientview");

        UnitUsersDao unitUserDao = new UnitUsersDao(unitcode, tenancy);
        return dao.retrieveList(unitUserDao);
    }
}
