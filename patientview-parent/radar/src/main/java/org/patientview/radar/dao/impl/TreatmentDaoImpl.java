package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.TreatmentDao;
import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreatmentDaoImpl extends BaseDaoImpl implements TreatmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentDaoImpl.class);
    private SimpleJdbcInsert treatmentInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        treatmentInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_rrt_treatment")
                .usingGeneratedKeyColumns("tID").usingColumns("RADAR_NO", "MODALITY", "DATE_START", "DATE_STOP",
                        "UNIT_CODE", "FIRST_FLAG");
    }

    public void saveTreatment(final Treatment treatment) {
        Map<String, Object> treatmentMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", treatment.getRadarNumber());
                put("MODALITY", treatment.getTreatmentModality() != null ? treatment.getTreatmentModality().getId() :
                        null);
                put("DATE_START", treatment.getStartDate());
                put("DATE_STOP", treatment.getEndDate());
                put("UNIT_CODE", null);
                put("FIRST_FLAG", null);
            }
        };

        if (treatment.hasValidId()) {
            treatmentMap.put("tID", treatment.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_rrt_treatment " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "MODALITY =:MODALITY, " +
                    "DATE_START =:DATE_START, " +
                    "DATE_STOP =:DATE_STOP, " +
                    "UNIT_CODE =:UNIT_CODE, " +
                    "FIRST_FLAG =:FIRST_FLAG " +
                    " WHERE tID = :tID;", treatmentMap);

        } else {
            Number id = treatmentInsert.executeAndReturnKey(treatmentMap);
            treatment.setId(id.longValue());
        }
    }

    public void deleteTreatment(Treatment treatment) {
        Map<String, Object> treatmentMap = new HashMap<String, Object>();
        treatmentMap.put("tID", treatment.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_rrt_treatment " +
                "WHERE tID = :tID;", treatmentMap);
    }

    public Treatment getTreatment(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_RRT_TREATMENT WHERE tID = ?", new Object[]{id},
                    new TreatmentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No treatment found for ID {}", id);
            return null;
        }
    }

    public List<Treatment> getTreatmentsByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_RRT_TREATMENT WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new TreatmentRowMapper());
    }

    public TreatmentModality getTreatmentModality(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_RRT_Modality WHERE mID = ?", new Object[]{id},
                    new TreatmentModalityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No Treatment modality found for ID {}", id);
            return null;
        }
    }


    public List<TreatmentModality> getTreatmentModalities() {
        return jdbcTemplate.query("SELECT * FROM tbl_RRT_Modality WHERE mID <= 19;", new TreatmentModalityRowMapper());
    }

    private class TreatmentRowMapper implements RowMapper<Treatment> {
        public Treatment mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct an object and set fields
            Treatment treatment = new Treatment();
            treatment.setId(resultSet.getLong("tID"));
            treatment.setRadarNumber(resultSet.getLong("RADAR_NO"));

            // Dates
            treatment.setStartDate(resultSet.getDate("DATE_START"));
            treatment.setEndDate(resultSet.getDate("DATE_STOP"));

            // Modality
            long modalityId = resultSet.getLong("MODALITY");
            if (modalityId > 0) {
                treatment.setTreatmentModality(getTreatmentModality(modalityId));
            }

            return treatment;
        }
    }

    private class TreatmentModalityRowMapper implements RowMapper<TreatmentModality> {
        public TreatmentModality mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a Modality object and set fields
            TreatmentModality treatmentModality = new TreatmentModality();
            treatmentModality.setId(resultSet.getLong("mID"));
            treatmentModality.setDescription(resultSet.getString("mType"));
            return treatmentModality;
        }
    }
}
