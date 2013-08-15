package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.HospitalisationDao;
import org.patientview.radar.model.Hospitalisation;
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

public class HospitalisationDaoImpl extends BaseDaoImpl implements HospitalisationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalisationDaoImpl.class);
    private SimpleJdbcInsert hospitlisationInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        hospitlisationInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_hospitalisation")
                .usingGeneratedKeyColumns("hID").usingColumns("RADAR_NO", "DATE_ADMIT", "DATE_DISCHARGE",
                        "REASON_ADMIT", "COMMENT");
    }

    public void saveHospitilsation(final Hospitalisation hospitalisation) {
        Map<String, Object> hospitilisationMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", hospitalisation.getRadarNumber());
                put("DATE_ADMIT", hospitalisation.getDateOfAdmission());
                put("DATE_DISCHARGE", hospitalisation.getDateOfDischarge());
                put("REASON_ADMIT", hospitalisation.getReason());
                put("COMMENT", hospitalisation.getComments());
            }
        };

        if (hospitalisation.hasValidId()) {
            hospitilisationMap.put("hID", hospitalisation.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_hospitalisation " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "DATE_ADMIT = :DATE_ADMIT, " +
                    "DATE_DISCHARGE = :DATE_DISCHARGE, " +
                    "REASON_ADMIT = :REASON_ADMIT, " +
                    "COMMENT = :COMMENT " +
                    " WHERE hID = :hID;", hospitilisationMap);

        } else {
            Number id = hospitlisationInsert.executeAndReturnKey(hospitilisationMap);
            hospitalisation.setId(id.longValue());
        }
    }

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
