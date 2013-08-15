package org.patientview.radar.dao.alport.impl;

import org.patientview.radar.dao.alport.GeneticsDao;
import org.patientview.radar.dao.impl.BaseDaoImpl;
import org.patientview.radar.model.Genetics;
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

public class GeneticsDaoImpl extends BaseDaoImpl implements GeneticsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneticsDaoImpl.class);

    private static final String TABLE_NAME = "rdc_genetic_test";
    private static final String ID_FIELD_NAME = "id";
    private static final String RADAR_NO_FIELD_NAME = "radar_no";
    private static final String TESTS_DONE_FIELD_NAME = "testsDone";
    private static final String LAB_WHERE_TESTS_WERE_DONE_FIELD_NAME = "labWhereTestWasDone";
    private static final String TESTS_DONE_ON_FIELD_NAME = "testDoneOn";
    private static final String REFERENCE_NUMBER_FIELD_NAME = "referenceNumber";
    private static final String WHAT_RESULTS_SHOWED_FIELD_NAME = "whatResultsShowed";
    private static final String KEY_EVIDENCE_FIELD_NAME = "keyEvidence";

    private SimpleJdbcInsert geneticsInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        geneticsInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(RADAR_NO_FIELD_NAME, TESTS_DONE_FIELD_NAME, LAB_WHERE_TESTS_WERE_DONE_FIELD_NAME,
                        TESTS_DONE_ON_FIELD_NAME, REFERENCE_NUMBER_FIELD_NAME, WHAT_RESULTS_SHOWED_FIELD_NAME,
                        KEY_EVIDENCE_FIELD_NAME);
    }

    public void save(Genetics genetics) {
        Map<String, Object> geneticsMap = new HashMap<String, Object>();
        geneticsMap.put(ID_FIELD_NAME, genetics.getId());
        geneticsMap.put(RADAR_NO_FIELD_NAME, genetics.getRadarNo());
        geneticsMap.put(TESTS_DONE_FIELD_NAME, genetics.getTestsDone().getId());
        geneticsMap.put(LAB_WHERE_TESTS_WERE_DONE_FIELD_NAME, genetics.getLabWhereTestWasDone());
        geneticsMap.put(TESTS_DONE_ON_FIELD_NAME, genetics.getTestDoneOn());
        geneticsMap.put(REFERENCE_NUMBER_FIELD_NAME, genetics.getReferenceNumber());
        geneticsMap.put(WHAT_RESULTS_SHOWED_FIELD_NAME, genetics.getWhatResultsShowed());
        geneticsMap.put(KEY_EVIDENCE_FIELD_NAME, genetics.getKeyEvidence());

        if (genetics.hasValidId()) {
            namedParameterJdbcTemplate.update(buildUpdateQuery(TABLE_NAME, ID_FIELD_NAME, geneticsMap), geneticsMap);
        } else {
            Number id = geneticsInsert.executeAndReturnKey(geneticsMap);
            genetics.setId(id.longValue());
        }
    }

    public Genetics get(Long radarNo) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE " + RADAR_NO_FIELD_NAME + " = ?",
                    new Object[]{radarNo}, new GeneticsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not find row in table " + TABLE_NAME + " with " + RADAR_NO_FIELD_NAME + " {}", radarNo);
        }
        return null;
    }

    private class GeneticsRowMapper implements RowMapper<Genetics> {
        public Genetics mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genetics genetics = new Genetics();

            genetics.setId(rs.getLong(ID_FIELD_NAME));
            genetics.setRadarNo(rs.getLong(RADAR_NO_FIELD_NAME));
            genetics.setTestsDone(Genetics.TestsDone.getTestsDone(rs.getInt(TESTS_DONE_FIELD_NAME)));
            genetics.setLabWhereTestWasDone(rs.getString(LAB_WHERE_TESTS_WERE_DONE_FIELD_NAME));
            genetics.setTestDoneOn(rs.getString(TESTS_DONE_ON_FIELD_NAME));
            genetics.setReferenceNumber(rs.getString(REFERENCE_NUMBER_FIELD_NAME));
            genetics.setWhatResultsShowed(rs.getString(WHAT_RESULTS_SHOWED_FIELD_NAME));
            genetics.setKeyEvidence(rs.getString(KEY_EVIDENCE_FIELD_NAME));

            return genetics;
        }
    }
}
