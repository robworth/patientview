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

import org.patientview.patientview.model.UktStatus;
import org.patientview.patientview.model.UktStatus_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.UktStatusDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "uktStatusDao")
public class UktStatusDaoImpl extends AbstractHibernateDAO<UktStatus> implements UktStatusDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UktStatus get(String nhsno) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UktStatus> criteria = builder.createQuery(UktStatus.class);
        Root<UktStatus> from = criteria.from(UktStatus.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(UktStatus_.nhsno), nhsno));

        buildWhereClause(criteria, wherePredicates);
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Query query = getEntityManager().createNativeQuery("DELETE FROM uktstatus");
        query.executeUpdate();
    }

    @Override
    public List<UktStatus> getByNhsNo(String nhsNo) {
        String sql = "SELECT * From uktstatus where nhsno = ? ";

        List<Object> params = new ArrayList<Object>();
        params.add(nhsNo);
        return jdbcTemplate.query(sql, params.toArray(), new UktStatusMapper());
    }

    private class UktStatusMapper implements RowMapper<UktStatus> {
        @Override
        public UktStatus mapRow(ResultSet resultSet, int i) throws SQLException {
            UktStatus uktStatus = new UktStatus();
            uktStatus.setNhsno(resultSet.getString("nhsno"));

            return uktStatus;
        }
    }
}
