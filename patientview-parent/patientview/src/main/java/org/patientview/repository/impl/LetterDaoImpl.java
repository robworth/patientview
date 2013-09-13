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

import org.apache.commons.lang.StringUtils;
import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.LetterDao;
import org.patientview.repository.UserMappingDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;

@Repository(value = "letterDao")
public class LetterDaoImpl extends AbstractHibernateDAO<Letter> implements LetterDao {

    @Inject
    private UserMappingDao userMappingDao;

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


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
    public List<Letter> get(Set<String> nhsnos, int page, int pagesize) {
        List<Object> params = new ArrayList<Object>();
        String sql = "SELECT * FROM letter ";

        if (!nhsnos.isEmpty()) {
            sql += " WHERE ( ";
            sql += StringUtils.repeat(" nhsno = ? ", "or", nhsnos.size());
            sql += " )";
            params.addAll(nhsnos);
        }

        sql += " ORDER BY date";
        if (page != 0 && pagesize != 0) {
            sql += " LIMIT  ?, ?";

            params.add((page - 1) * pagesize);
            params.add(pagesize);
        }

        return jdbcTemplate.query(sql, params.toArray(), new LetterMapper());
    }

    @Override
    public Long getCount(Set<String> nhsnos) {
        List<Object> params = new ArrayList<Object>();

        String sql = "SELECT count(id) FROM letter ";
        if (!nhsnos.isEmpty()) {
            sql += " WHERE ( ";
            sql += StringUtils.repeat(" nhsno = ? ", "or", nhsnos.size());
            sql += " )";
            params.addAll(nhsnos);
        }

        return jdbcTemplate.queryForObject(sql, params.toArray(), Long.class);
    }

    private class LetterMapper implements RowMapper<Letter> {

        @Override
        public Letter mapRow(ResultSet resultSet, int i) throws SQLException {

            Letter row = new Letter();
            row.setId(resultSet.getLong("id"));
            row.setNhsno(resultSet.getString("nhsno"));
            row.setUnitcode(resultSet.getString("unitcode"));
            Calendar calendar =  Calendar.getInstance();
            calendar.setTime(resultSet.getDate("date"));
            row.setDate(calendar);
            row.setType(resultSet.getString("type"));
            row.setContent(resultSet.getString("content"));
            return row;
        }
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
