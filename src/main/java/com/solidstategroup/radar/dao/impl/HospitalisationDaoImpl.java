package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.HospitalisationDao;
import com.solidstategroup.radar.model.Hospitalisation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HospitalisationDaoImpl extends BaseDaoImpl implements HospitalisationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalisationDaoImpl.class);

    public Hospitalisation getHospitalisation(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Hospitalisation WHERE hID = ?", new Object[]{id},
                    new HospitalisationRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No hospitalisation record with ID {}", id);
            return null;
        }
    }

    public List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_Hospitalisation WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new HospitalisationRowMapper());
    }

    private class HospitalisationRowMapper implements RowMapper<Hospitalisation> {
        public Hospitalisation mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct hospitalisation object and set fields
            Hospitalisation hospitalisation = new Hospitalisation();

            // IDs
            hospitalisation.setId(resultSet.getLong("hID"));
            hospitalisation.setRadarNumber(resultSet.getLong("RADAR_NO"));

            // Rest of the fields..
            hospitalisation.setDateOfAdmission(resultSet.getDate("DATE_ADMIT"));
            hospitalisation.setDateOfDischarge(resultSet.getDate("DATE_DISCHARGE"));
            hospitalisation.setReason(resultSet.getString("REASON_ADMIT"));
            hospitalisation.setComments(resultSet.getString("COMMENT"));

            return hospitalisation;
        }
    }
}
