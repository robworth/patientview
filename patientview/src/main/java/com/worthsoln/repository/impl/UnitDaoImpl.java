package com.worthsoln.repository.impl;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.Unit_;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UnitDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Note: I have changed the implementation to allow units to be returned when the specialty is null
 *  i.e. we are not a logged in user.  (PC 01/03/2013)
 *  The unitcode is unique so we should not get NonUniqueResultException
 */
@Repository(value = "unitDao")
public class UnitDaoImpl extends AbstractHibernateDAO<Unit> implements UnitDao {

    @Override
    public Unit get(String unitCode, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Unit_.unitcode), unitCode));

        if (specialty != null) {
            wherePredicates.add(builder.equal(from.get(Unit_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Unit> getAll(boolean sortByName, Specialty specialty) {

        if (sortByName) {

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
            Root<Unit> from = criteria.from(Unit.class);
            List<Predicate> wherePredicates = new ArrayList<Predicate>();

            if (specialty != null) {
                wherePredicates.add(builder.equal(from.get(Unit_.specialty), specialty));
            }

            buildWhereClause(criteria, wherePredicates);

            criteria.orderBy(builder.asc(from.get(Unit_.name)));

            return getEntityManager().createQuery(criteria).getResultList();
        } else {
            return getAll();
        }
    }

    @Override
    public List<Unit> getUnitsWithUser(Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.isNotNull(from.get(Unit_.unituser)));
        wherePredicates.add(builder.notEqual(from.get(Unit_.unituser), ""));
            
        if (specialty != null) {
            wherePredicates.add(builder.equal(from.get(Unit_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<Unit> get(List<String> usersUnitCodes, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        if (usersUnitCodes != null && usersUnitCodes.size() > 0) {
            wherePredicates.add(from.get(Unit_.unitcode).in(usersUnitCodes.toArray(new String[usersUnitCodes.size()])));
        }

        if (specialty != null) {
            wherePredicates.add(builder.equal(from.get(Unit_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<Unit> get(List<String> usersUnitCodes, String[] notTheseUnitCodes, String[] plusTheseUnitCodes,
                          Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        // add in the additional ones, only add in if we have usersUnitCodes, otherwise we need all of them
        if (plusTheseUnitCodes != null && plusTheseUnitCodes.length > 0 && usersUnitCodes != null
                && usersUnitCodes.size() > 0) {
            Collections.addAll(usersUnitCodes, plusTheseUnitCodes);
        }

        if (usersUnitCodes != null && usersUnitCodes.size() > 0) {
            wherePredicates.add(from.get(Unit_.unitcode).in(usersUnitCodes.toArray(new String[usersUnitCodes.size()])));
        }

        if (notTheseUnitCodes != null) {
            for (String notUnitCode : notTheseUnitCodes) {
                wherePredicates.add(builder.notEqual(from.get(Unit_.unitcode), notUnitCode));
            }
        }

        if (specialty != null) {
            wherePredicates.add(builder.equal(from.get(Unit_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(from.get(Unit_.name)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<UnitAdmin> getUnitUsers(String unitcode, Specialty specialty) {
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
                "   sur.specialty_id = :specialtyId " +
                "AND " +
                "   um.unitcode = :unitcode " +
                "AND " +
                "   (sur.role = 'unitadmin' OR sur.role = 'unitstaff')";

        Query query = getEntityManager().createNativeQuery(sql, User.class);

        query.setParameter("specialtyId", specialty.getId());
        query.setParameter("unitcode", unitcode);

        List<User> users =  query.getResultList();

        List<UnitAdmin> unitAdmins = new ArrayList<UnitAdmin>();

        for (User user : users) {
            UnitAdmin unitAdmin = new UnitAdmin();
            unitAdmin.setUsername(user.getUsername());
            unitAdmin.setName(user.getName());
            unitAdmin.setEmail(user.getEmail());
            unitAdmin.setEmailverfied(user.isEmailverified());
            unitAdmin.setRole(user.getRole());
            unitAdmin.setFirstlogon(user.isFirstlogon());
            unitAdmins.add(unitAdmin);
            unitAdmin.setScreenname(user.getScreenname());
        }

        return unitAdmins;
    }
}
