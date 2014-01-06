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

package org.patientview.radar.dao.alport.impl;

import org.patientview.radar.dao.alport.DeafnessDao;
import org.patientview.radar.dao.impl.BaseDaoImpl;
import org.patientview.radar.model.alport.Deafness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeafnessDaoImpl extends BaseDaoImpl implements DeafnessDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneticsDaoImpl.class);

    private static final String TABLE_NAME = "rdr_alport_deafness";
    private static final String ID_FIELD_NAME = "id";
    private static final String RADAR_NO_FIELD_NAME = "radar_no";
    private static final String EVIDENCE_OF_DEAFNESS_FIELD_NAME = "evidenceOfDeafness";
    private static final String DATE_PROBLEM_FIRST_NOTICED_FIELD_NAME = "dateProblemFirstNoticed";
    private static final String DATE_STARTED_USING_HEARING_AID_FIELD_NAME = "dateStartedUsingHearingAid";

    private SimpleJdbcInsert deafnessInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        deafnessInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(RADAR_NO_FIELD_NAME, EVIDENCE_OF_DEAFNESS_FIELD_NAME,
                        DATE_PROBLEM_FIRST_NOTICED_FIELD_NAME, DATE_STARTED_USING_HEARING_AID_FIELD_NAME);
    }

    public void save(Deafness deafness) {
        Map<String, Object> geneticsMap = new HashMap<String, Object>();
        geneticsMap.put(ID_FIELD_NAME, deafness.getId());
        geneticsMap.put(RADAR_NO_FIELD_NAME, deafness.getRadarNo());
        geneticsMap.put(EVIDENCE_OF_DEAFNESS_FIELD_NAME, deafness.getEvidenceOfDeafness().getId());
        geneticsMap.put(DATE_PROBLEM_FIRST_NOTICED_FIELD_NAME, deafness.getDateProblemFirstNoticed());
        geneticsMap.put(DATE_STARTED_USING_HEARING_AID_FIELD_NAME, deafness.getDateStartedUsingHearingAid());

        if (deafness.hasValidId()) {
            namedParameterJdbcTemplate.update(buildUpdateQuery(TABLE_NAME, ID_FIELD_NAME, geneticsMap), geneticsMap);
        } else {
            Number id = deafnessInsert.executeAndReturnKey(geneticsMap);
            deafness.setId(id.longValue());
        }
    }

    public Deafness get(Long radarNo) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE " + RADAR_NO_FIELD_NAME + " = ?",
                    new Object[]{radarNo}, new DeafnessRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not find row in table " + TABLE_NAME + " with " + RADAR_NO_FIELD_NAME + " {}", radarNo);
        }
        return null;
    }

    private class DeafnessRowMapper implements RowMapper<Deafness> {
        public Deafness mapRow(ResultSet rs, int rowNum) throws SQLException {
            Deafness deafness = new Deafness();

            deafness.setId(rs.getLong(ID_FIELD_NAME));
            deafness.setRadarNo(rs.getLong(RADAR_NO_FIELD_NAME));
            deafness.setEvidenceOfDeafness(Deafness.EvidenceOfDeafness.getEvidenceOfDeafness(
                    rs.getInt(EVIDENCE_OF_DEAFNESS_FIELD_NAME)));
            deafness.setDateProblemFirstNoticed(rs.getDate(DATE_PROBLEM_FIRST_NOTICED_FIELD_NAME));
            deafness.setDateStartedUsingHearingAid(rs.getDate(DATE_STARTED_USING_HEARING_AID_FIELD_NAME));

            return deafness;
        }
    }
}
