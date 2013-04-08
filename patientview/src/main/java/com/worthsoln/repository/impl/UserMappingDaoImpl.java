package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.UserMapping_;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UserMappingDao;
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
@Repository(value = "userMappingDao")
public class UserMappingDaoImpl extends AbstractHibernateDAO<UserMapping> implements UserMappingDao {

    @Override
    public void deleteUserMappings(String username, String unitcode, Specialty specialty) {

        List<UserMapping> userMappings = getAll(username, unitcode, specialty);

        try {
            for (UserMapping userMapping : userMappings) {
                delete(userMapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserMapping> getAll(String username, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserMapping> criteria = builder.createQuery(UserMapping.class);
        Root<UserMapping> from = criteria.from(UserMapping.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UserMapping_.username), username));
        wherePredicates.add(builder.equal(from.get(UserMapping_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<UserMapping> getAllExcludeUnitcode(String username, String unitcode, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserMapping> criteria = builder.createQuery(UserMapping.class);
        Root<UserMapping> from = criteria.from(UserMapping.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UserMapping_.username), username));
        wherePredicates.add(builder.notEqual(from.get(UserMapping_.unitcode), unitcode));
        wherePredicates.add(builder.equal(from.get(UserMapping_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<UserMapping> getAll(String username, String unitcode, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserMapping> criteria = builder.createQuery(UserMapping.class);
        Root<UserMapping> from = criteria.from(UserMapping.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UserMapping_.username), username));
        wherePredicates.add(builder.equal(from.get(UserMapping_.unitcode), unitcode));
        wherePredicates.add(builder.equal(from.get(UserMapping_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<UserMapping> getAllForNhsNo(String nhsNo, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserMapping> criteria = builder.createQuery(UserMapping.class);
        Root<UserMapping> from = criteria.from(UserMapping.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UserMapping_.nhsno), nhsNo));
        wherePredicates.add(builder.equal(from.get(UserMapping_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public String getUsersRealUnitcodeBestGuess(String username, Specialty specialty) {

        List<UserMapping> userMappings = getAllExcludeUnitcode(username, UnitUtils.PATIENT_ENTERS_UNITCODE, specialty);

        if (userMappings == null || userMappings.isEmpty()) {
            return "";
        } else {
            return userMappings.get(0).getUnitcode();
        }
    }

    @Override
    public String getUsersRealNhsNoBestGuess(String username, Specialty specialty) {

        List<UserMapping> userMappings = getAllExcludeUnitcode(username, UnitUtils.PATIENT_ENTERS_UNITCODE, specialty);

        if (userMappings == null || userMappings.isEmpty()) {
            return "";
        } else {
            return userMappings.get(0).getNhsno();
        }
    }

    @Override
    public UserMapping getUserMappingPatientEntered(User user, Specialty specialty) {

        List<UserMapping> userMappings = getAll(user.getUsername(), specialty);

        UserMapping patientEntryUserMapping = null;
        UserMapping anyOtherUserMapping = null;

        for (UserMapping currentUserMapping : userMappings) {

            if (UnitUtils.PATIENT_ENTERS_UNITCODE.equals(currentUserMapping.getUnitcode())) {
                patientEntryUserMapping = currentUserMapping;
            } else {
                anyOtherUserMapping = currentUserMapping;
            }
        }

        if (patientEntryUserMapping == null) {
            if (anyOtherUserMapping != null) {
                patientEntryUserMapping = new UserMapping(anyOtherUserMapping.getUsername(),
                        UnitUtils.PATIENT_ENTERS_UNITCODE, anyOtherUserMapping.getNhsno());
                patientEntryUserMapping.setSpecialty(specialty);
                save(patientEntryUserMapping);
                return patientEntryUserMapping;
            }
        } else {
            return patientEntryUserMapping;
        }

        return null;
    }

    @Override
    public List<UserMapping> getUsersSiblings(String username, String unitcode, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserMapping> criteria = builder.createQuery(UserMapping.class);
        Root<UserMapping> from = criteria.from(UserMapping.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        Predicate username1 = builder.equal(from.get(UserMapping_.username), username);
        Predicate username2 = builder.equal(from.get(UserMapping_.username), username + "-GP");

        Predicate unitCode1 = builder.equal(from.get(UserMapping_.unitcode), unitcode);
        Predicate unitCode2 = builder.equal(from.get(UserMapping_.unitcode), "PATIENT");

        wherePredicates.add(builder.or(username1, username2));
        wherePredicates.add(builder.or(unitCode1, unitCode2));
        wherePredicates.add(builder.equal(from.get(UserMapping_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<UserMapping> getDuplicateUsers(String nhsno, String username, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserMapping> criteria = builder.createQuery(UserMapping.class);
        Root<UserMapping> from = criteria.from(UserMapping.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UserMapping_.nhsno), nhsno));
        wherePredicates.add(builder.notEqual(from.get(UserMapping_.username), username));
        wherePredicates.add(builder.notLike(from.get(UserMapping_.username), "%-GP"));
        wherePredicates.add(builder.equal(from.get(UserMapping_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
