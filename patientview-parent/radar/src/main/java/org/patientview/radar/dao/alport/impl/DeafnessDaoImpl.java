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
    private static final String AGE_PROBLEM_FIRST_NOTICED_FIELD_NAME = "ageProblemFirstNoticed";
    private static final String AGE_STARTED_USING_HEARING_AID_FIELD_NAME = "ageStartedUsingHearingAid";

    private SimpleJdbcInsert deafnessInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        deafnessInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(RADAR_NO_FIELD_NAME, EVIDENCE_OF_DEAFNESS_FIELD_NAME,
                        AGE_PROBLEM_FIRST_NOTICED_FIELD_NAME, AGE_STARTED_USING_HEARING_AID_FIELD_NAME);
    }

    public void save(Deafness deafness) {
        Map<String, Object> geneticsMap = new HashMap<String, Object>();
        geneticsMap.put(ID_FIELD_NAME, deafness.getId());
        geneticsMap.put(RADAR_NO_FIELD_NAME, deafness.getRadarNo());
        geneticsMap.put(EVIDENCE_OF_DEAFNESS_FIELD_NAME, deafness.getEvidenceOfDeafness().getId());
        geneticsMap.put(AGE_PROBLEM_FIRST_NOTICED_FIELD_NAME, deafness.getAgeProblemFirstNoticed());
        geneticsMap.put(AGE_STARTED_USING_HEARING_AID_FIELD_NAME, deafness.getAgeStartedUsingHearingAid());

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
            deafness.setAgeProblemFirstNoticed(rs.getInt(AGE_PROBLEM_FIRST_NOTICED_FIELD_NAME));
            deafness.setAgeStartedUsingHearingAid(rs.getInt(AGE_STARTED_USING_HEARING_AID_FIELD_NAME));

            return deafness;
        }
    }
}
