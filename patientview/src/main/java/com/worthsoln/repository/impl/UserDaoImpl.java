package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.User_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
    public List<User> get(User user, Specialty specialty, String userType) {
        String sql = "SELECT " +
                "  u.*  " +
                "FROM " +
                "   User u, " +
                "   UserMapping um, " +
                "   SpecialtyUserRole sur " +
                "WHERE " +
                "   u.username = um.username " +
                "AND " +
                "   u.id = sur.user_id " +
                "AND " +
                "   sur.role = :userType " +
                "AND " +
                "   um.unitcode IN ( " +
                            "SELECT " +
                            "    unitcode " +
                            "FROM " +
                            "    UserMapping " +
                            "WHERE " +
                            "  username = :username " +
                            "AND " +
                            "   sur.specialty_id = :specialtyId " +
                    ")";



        Query query = getEntityManager().createNativeQuery(sql, User.class);

        query.setParameter("userType", userType);
        query.setParameter("username", user.getName());
        query.setParameter("specialtyId", specialty.getId());

        return query.getResultList();
    }
}
