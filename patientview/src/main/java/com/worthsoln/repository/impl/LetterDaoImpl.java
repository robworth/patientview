package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.LetterDao;
import com.worthsoln.repository.UserMappingDao;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository(value = "letterDao")
public class LetterDaoImpl extends AbstractHibernateDAO<Letter> implements LetterDao {

    @Inject
    private UserMappingDao userMappingDao;

    @Override
    public List<Letter> get(String username, Specialty specialty) {
        if (null != username && !"".equals(username)) {
            String sql = "SELECT DISTINCT letter.* FROM letter, usermapping " +
                    "WHERE letter.nhsno = usermapping.nhsno " +
                    "AND usermapping.username = :username " +
                    "ORDER BY letter.date DESC";

            Query query = getEntityManager().createNativeQuery(sql, Letter.class);

            query.setParameter("username", username);

            List<Letter> letters = query.getResultList();

            return letters;
        }

        return Collections.emptyList();
    }

    @Override
    public void delete(String nhsno, String unitcode, Date date) {
        Query query = getEntityManager().createQuery(
                "DELETE FROM letter WHERE nhsno = :nhsno AND unitcode = :unitcode AND date = :date");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        query.setParameter("date", calendar);

        query.executeUpdate();
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        Query query = getEntityManager().createQuery("DELETE FROM letter WHERE nhsno = :nhsno AND unitcode " +
                "= :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
