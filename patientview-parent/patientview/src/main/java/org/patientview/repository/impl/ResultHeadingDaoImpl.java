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

import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.ResultHeading;
import org.patientview.patientview.model.ResultHeading_;
import org.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ResultHeadingDao;
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
@Repository(value = "resultHeadingDao")
public class ResultHeadingDaoImpl extends AbstractHibernateDAO<ResultHeading> implements ResultHeadingDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ResultHeading get(String headingcode, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> from = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(ResultHeading_.headingcode), headingcode));

        buildWhereClause(criteria, wherePredicates);
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ResultHeading> getAll(Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> root = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(ResultHeading_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<ResultHeading> getAll(Specialty specialty, String username) {

        List<Object> params = new ArrayList<Object>();

        String sql = " SELECT DISTINCT result_heading.* "
                + " FROM testresult "
                + " LEFT JOIN unit ON unit.unitcode = testresult.unitcode "
                + " JOIN user, usermapping, result_heading "
                + " WHERE user.username = ? "
                + " AND result_heading.specialty_id = ? "
                + " AND user.username = usermapping.username "
                + " AND usermapping.nhsno = testresult.nhsno "
                + " AND testresult.testcode = result_heading.headingcode ";

        params.add(username);
        params.add(specialty.getId());

        return jdbcTemplate.query(sql, params.toArray(), new ResultHeadingMapper());
    }


    @Override
    public List<ResultHeading> get(int panel, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> root = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(ResultHeading_.panel), panel));
        wherePredicates.add(builder.equal(root.get(ResultHeading_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(ResultHeading_.panelorder)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void delete(String headingCode, Specialty specialty) {

        delete(get(headingCode, specialty));
    }

    @Override
    public List<Panel> getPanels(Specialty specialty) {

        String sql = "SELECT DISTINCT panel FROM result_heading WHERE panel != 0 ORDER BY panel ASC";

        Query query = getEntityManager().createNativeQuery(sql);

        List resultsObjs = query.getResultList();
        List<Panel> results = new ArrayList<Panel>();
        for (Object object : resultsObjs) {
            results.add(new Panel((Integer) object));
        }

        return results;
    }

    private class ResultHeadingMapper implements RowMapper<ResultHeading> {

        @Override
        public ResultHeading mapRow(ResultSet resultSet, int i) throws SQLException {

            ResultHeading resultHeading = new ResultHeading();
            resultHeading.setId(resultSet.getLong("id"));
            resultHeading.setHeading(resultSet.getString("heading"));
            resultHeading.setHeadingcode(resultSet.getString("headingcode"));
            resultHeading.setLink(resultSet.getString("link"));
            resultHeading.setRollover(resultSet.getString("rollover"));

            return resultHeading;
        }
    }
}
