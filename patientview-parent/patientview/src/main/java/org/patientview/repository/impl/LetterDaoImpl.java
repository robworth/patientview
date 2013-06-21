/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.repository.impl;

import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.LetterDao;
import org.patientview.repository.UserMappingDao;
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
            String sql = "SELECT DISTINCT letter.* FROM letter, usermapping "
                    + "WHERE letter.nhsno = usermapping.nhsno "
                    + "AND usermapping.username = :username "
                    + "ORDER BY letter.date DESC";

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
        Query query = getEntityManager().createQuery("DELETE FROM letter WHERE nhsno = :nhsno AND unitcode "
                + "= :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
