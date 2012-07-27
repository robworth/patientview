package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Letter_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.LetterDao;
import com.worthsoln.repository.UserMappingDao;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@Repository(value = "letterDao")
public class LetterDaoImpl extends AbstractHibernateDAO<Letter> implements LetterDao {

    @Inject
    private UserMappingDao userMappingDao;

    @Override
    public List<Letter> get(String username) {
        String usersNhsNo = userMappingDao.getUsersRealNhsNoBestGuess(username);

        if (usersNhsNo != null && usersNhsNo.length() > 0) {
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Letter> criteria = builder.createQuery(Letter.class);
            Root<Letter> letterRoot = criteria.from(Letter.class);

            criteria.where(builder.equal(letterRoot.get(Letter_.nhsno), usersNhsNo));

            criteria.orderBy(builder.desc(letterRoot.get(Letter_.date)));

            return getEntityManager().createQuery(criteria).getResultList();
        }

        return Collections.emptyList();
    }
}
