package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Letter_;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.LetterDao;
import com.worthsoln.repository.UserMappingDao;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository(value = "letterDao")
public class LetterDaoImpl extends AbstractHibernateDAO<Letter> implements LetterDao {

    @Inject
    private UserMappingDao userMappingDao;

    @Override
    public List<Letter> get(String username, Specialty specialty) {
        String usersNhsNo = userMappingDao.getUsersRealNhsNoBestGuess(username, specialty);

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

    @Override
    public void delete(String nhsno, String unitcode, Date date) {
        Query query = getEntityManager().createQuery(
                "DELETE FROM letter WHERE nhsno = :nhsno AND unitcode = :unitcode AND date = :date");

        query.setParameter("nhsno", unitcode);
        query.setParameter("unitcode", unitcode);
        query.setParameter("date", new Timestamp(date.getTime()));

        query.executeUpdate();
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        Query query = getEntityManager().createQuery("DELETE FROM letter WHERE nhsno = :nhsno AND unitcode " +
                "= :unitcode");

        query.setParameter("nhsno", unitcode);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
