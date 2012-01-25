package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.TreatmentDao;
import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.model.TreatmentModality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TreatmentDaoImpl extends BaseDaoImpl implements TreatmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentDaoImpl.class);

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
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_RT_Modality WHERE mID = ?", new Object[]{id},
                    new TreatmentModalityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No Treatment modality found for ID {}", id);
            return null;
        }
    }


    public List<TreatmentModality> getTreatmentModalities() {
        return jdbcTemplate.query("SELECT * FROM tbl_RT_Modality", new TreatmentModalityRowMapper());
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

            // Todo: Add these fields
            /*
	UNIT_CODE int,
	FIRST_FLAG bit
             */
        }
    }

    private class TreatmentModalityRowMapper implements RowMapper<TreatmentModality> {
        public TreatmentModality mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a Modality object and set fields
            TreatmentModality treatmentModality = new TreatmentModality();
            treatmentModality.setId(resultSet.getLong("mID"));
            treatmentModality.setDescription(resultSet.getString("mDesc"));
            return treatmentModality;
        }
    }
}
