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
import org.patientview.patientview.medicine.MedicineWithShortName;
import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.Medicine_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.MedicineDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Repository(value = "medicineDao")
public class MedicineDaoImpl extends AbstractHibernateDAO<Medicine> implements MedicineDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Medicine> getMedicines(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Medicine> criteria = builder.createQuery(Medicine.class);
        Root<Medicine> medicineRoot = criteria.from(Medicine.class);

        criteria.where(builder.equal(medicineRoot.get(Medicine_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MedicineWithShortName> get(Set<String> nhsnos, int page, int pagesize) {
        List<Object> params = new ArrayList<Object>();

        String sql = "SELECT m.nhsno, m.unitcode, m.startdate, m.name, m.dose, m.id, u.shortname "
                + "FROM medicine m LEFT JOIN unit u ON u.unitcode = m.unitcode ";

        if (!nhsnos.isEmpty()) {
            sql += " WHERE ( ";
            sql += StringUtils.repeat(" m.nhsno = ? ", "or", nhsnos.size());
            sql += " )";
            params.addAll(nhsnos);
        }

        if (page != 0 && pagesize != 0) {
            sql += " LIMIT  ?, ?";

            params.add((page - 1) * pagesize);
            params.add(pagesize);
        }

        return jdbcTemplate.query(sql, params.toArray(), new MedicineMapper());
    }

    @Override
    public Long getCount(Set<String> nhsnos) {
        List<Object> params = new ArrayList<Object>();

        String sql = "SELECT count(m.id) "
                + "FROM medicine m LEFT JOIN unit u ON u.unitcode = m.unitcode ";
        if (!nhsnos.isEmpty()) {
            sql += " WHERE ( ";
            sql += StringUtils.repeat(" m.nhsno = ? ", "or", nhsnos.size());
            sql += " )";
            params.addAll(nhsnos);
        }

        return jdbcTemplate.queryForObject(sql, params.toArray(), Long.class);
    }

    private class MedicineMapper implements RowMapper<MedicineWithShortName> {

        @Override
        public MedicineWithShortName mapRow(ResultSet resultSet, int i) throws SQLException {

            MedicineWithShortName row = new MedicineWithShortName();
            row.setId(resultSet.getLong("id"));
            row.setNhsno(resultSet.getString("nhsno"));
            row.setUnitcode(resultSet.getString("unitcode"));
            Calendar calendar =  Calendar.getInstance();
            calendar.setTime(resultSet.getDate("startdate"));
            row.setStartdate(calendar);
            row.setName(resultSet.getString("name"));
            row.setDose(resultSet.getString("dose"));
            row.setShortname(resultSet.getString("shortname"));
            return row;
        }
    }



    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete procedure");
        }

        Query query = getEntityManager().createQuery(
                "DELETE FROM medicine WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
