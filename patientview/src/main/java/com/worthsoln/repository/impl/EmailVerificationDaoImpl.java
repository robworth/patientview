package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.EmailVerification;
import com.worthsoln.patientview.model.EmailVerification_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.EmailVerificationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository(value = "emailVerificationDao")
public class EmailVerificationDaoImpl extends AbstractHibernateDAO<EmailVerification> implements EmailVerificationDao {

    @Override
    public List<EmailVerification> get(String verificationCode) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmailVerification> criteria = builder.createQuery(EmailVerification.class);
        Root<EmailVerification> emailVerificationRoot = criteria.from(EmailVerification.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(emailVerificationRoot.get(EmailVerification_.verificationcode),
                verificationCode));

        // TODO: not sure of the greater than and just using Calendar.getInstance()
        wherePredicates.add(builder.greaterThan(emailVerificationRoot.get(EmailVerification_.expirydatestamp),
                                Calendar.getInstance()));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void delete(String username) {
        // I assume there would be only one in there but creating the statement instead of pulling back
        // all the objects with that username and deleting each one individually
        Query query = getEntityManager().createNativeQuery("DELETE FROM " + EmailVerification.class.getSimpleName()
                + " WHERE " + EmailVerification_.username.getName() + " = :" + EmailVerification_.username.getName());

        query.setParameter(EmailVerification_.username.getName(), username);
        query.executeUpdate();
    }
}
