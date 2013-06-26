package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.TransplantDao;
import org.patientview.radar.model.Transplant;
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

public class TransplantDaoImpl extends BaseDaoImpl implements TransplantDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransplantDaoImpl.class);
    private SimpleJdbcInsert transplantInsert;
    private SimpleJdbcInsert rejectDataInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        transplantInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_transplant")
                .usingGeneratedKeyColumns("trID").usingColumns("RADAR_NO",
                        "DATE_TRANSPLANT", "TRANS_TYPE", "TRANSPLANT_COUNTER", "DATE_NEPHRECT", "TRANS_RECURR",
                        "DATE_RECURR_TXK", "DATE_TX_REJECT", "DATE_BX_TXK");

        rejectDataInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_transplant_reject")
                .usingGeneratedKeyColumns("recID").usingColumns("trID", "trRejectDate", "trBiopsyDate",
                        "trFailureDate");
    }

    public void saveTransplant(final Transplant transplant) {
        Map<String, Object> transplantMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", transplant.getRadarNumber());
                put("DATE_TRANSPLANT", transplant.getDate());
                put("TRANS_TYPE", transplant.getModality() != null ? transplant.getModality().getId() : null);
                put("TRANSPLANT_COUNTER", transplant.getCounter());
                put("DATE_NEPHRECT", null);
                put("TRANS_RECURR", transplant.getRecurr());
                put("DATE_RECURR_TXK", transplant.getDateRecurr());
                put("DATE_TX_REJECT", transplant.getDateRejected());
                put("DATE_BX_TXK", transplant.getDateBiopsy());
            }
        };

        if (transplant.hasValidId()) {
            transplantMap.put("trID", transplant.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_transplant " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "DATE_TRANSPLANT = :DATE_TRANSPLANT, " +
                    "TRANS_TYPE = :TRANS_TYPE, " +
                    "TRANSPLANT_COUNTER = :TRANSPLANT_COUNTER, " +
                    "DATE_NEPHRECT = :DATE_NEPHRECT, " +
                    "TRANS_RECURR = :TRANS_RECURR, " +
                    "DATE_RECURR_TXK = :DATE_RECURR_TXK, " +
                    "DATE_TX_REJECT = :DATE_TX_REJECT, " +
                    "DATE_BX_TXK = :DATE_BX_TXK" +
                    " WHERE trID = :trID;", transplantMap);

        } else {
            Number id = transplantInsert.executeAndReturnKey(transplantMap);
            transplant.setId(id.longValue());
        }
        Transplant.RejectData rejectData = transplant.getDateFailureRejectData();
        if (rejectData.getFailureDate() != null) {
            rejectData.setTransplantId(transplant.getId());
            saveRejectData(rejectData);
        }
    }

    public void deleteTransplant(Transplant transplant) {
        Map<String, Object> transplantTreatmentMap = new HashMap<String, Object>();
        transplantTreatmentMap.put("trID", transplant.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_transplant " +
                "WHERE trID = :trID;", transplantTreatmentMap);
    }

    public Transplant getTransplant(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Transplant WHERE trID = ?", new Object[]{id},
                    new TransplantRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No transplant found for ID {}", id);
            return null;
        }
    }

    public List<Transplant> getTransplantsByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_Transplant WHERE radar_no = ?", new Object[]{radarNumber},
                new TransplantRowMapper());
    }

    public List<Transplant.Modality> getTransplantModalitites() {
        return jdbcTemplate.query("SELECT * FROM tbl_transplant_modality", new TransplantModalityRowMapper());
    }

    public Transplant.Modality getTransplantModality(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_transplant_modality WHERE trID = ?", new Object[]{id},
                    new TransplantModalityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }


    }

    public void saveRejectData(final Transplant.RejectData rejectData) {
        Map<String, Object> rejectDataMap = new HashMap<String, Object>() {
            {
                put("trID", rejectData.getTransplantId());
                put("trRejectDate", rejectData.getRejectedDate());
                put("trBiopsyDate", rejectData.getBiopsyDate());
                put("trFailureDate", rejectData.getFailureDate());
            }
        };

        if (rejectData.hasValidId()) {
            rejectDataMap.put("recID", rejectData.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_transplant_reject " +
                    "SET trID = :trID, " +
                    "trRejectDate = :trRejectDate, " +
                    "trBiopsyDate = :trBiopsyDate, " +
                    "trFailureDate = :trFailureDate " +
                    " WHERE recID = :recID;", rejectDataMap);

        } else {
            Number id = rejectDataInsert.executeAndReturnKey(rejectDataMap);
            rejectData.setId(id.longValue());
        }
    }

    public void deleteRejectData(Transplant.RejectData rejectData) {
        Map<String, Object> rejectDataMap = new HashMap<String, Object>();
        rejectDataMap.put("recID", rejectData.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_transplant_reject " +
                "WHERE recID = :recID;", rejectDataMap);
    }

    public List<Transplant.RejectData> getRejectDataByTransplantNumber(Long transplantId) {
        return jdbcTemplate.query("SELECT * FROM tbl_transplant_reject WHERE trID = ? AND trFailureDate IS NULL",
                new Object[]{transplantId}, new TransplantRejectRowMapper());
    }

    public Transplant.RejectData getRejectData(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_transplant_reject WHERE recID = ?", new Object[]{id},
                    new TransplantRejectRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private Transplant.RejectData getFailureDateRejectDataByTransplantNumber(Long transplantId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_transplant_reject WHERE trID = ? AND " +
                    "trFailureDate IS NOT NULL", new Object[]{transplantId}, new TransplantRejectRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    private class TransplantRowMapper implements RowMapper<Transplant> {
        public Transplant mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct transplant object and set fields
            Transplant transplant = new Transplant();

            // IDs
            transplant.setId(resultSet.getLong("trID"));
            transplant.setRadarNumber(resultSet.getLong("RADAR_NO"));

            transplant.setDate(resultSet.getDate("DATE_TRANSPLANT"));

            long transpantId = resultSet.getLong("TRANS_TYPE");
            transplant.setModality(getTransplantModality(transpantId));

            transplant.setCounter(resultSet.getInt("TRANSPLANT_COUNTER"));

            // Recurr stuff and dates
            transplant.setRecurr(resultSet.getBoolean("TRANS_RECURR"));
            transplant.setDateRecurr(resultSet.getDate("DATE_RECURR_TXK"));
            transplant.setDateRejected(resultSet.getDate("DATE_TX_REJECT"));
            transplant.setDateBiopsy(resultSet.getDate("DATE_BX_TXK"));
            Transplant.RejectData failureDateRejectData = getFailureDateRejectDataByTransplantNumber(
                    transplant.getId());

            if (failureDateRejectData == null) {
                failureDateRejectData = new Transplant.RejectData();
                failureDateRejectData.setTransplantId(transplant.getId());
            }
            transplant.setDateFailureRejectData(failureDateRejectData);

            return transplant;
        }
    }

    private class TransplantModalityRowMapper implements RowMapper<Transplant.Modality> {

        public Transplant.Modality mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct an object and set the fields
            Transplant.Modality modality = new Transplant.Modality();
            modality.setId(resultSet.getLong("trID"));
            modality.setDescription(resultSet.getString("trDesc"));
            return modality;
        }
    }

    private class TransplantRejectRowMapper implements RowMapper<Transplant.RejectData> {
        public Transplant.RejectData mapRow(ResultSet resultSet, int i) throws SQLException {
            Transplant.RejectData rejectData = new Transplant.RejectData();
            rejectData.setId(resultSet.getLong("recID"));
            rejectData.setTransplantId(resultSet.getLong("trID"));
            rejectData.setRejectedDate(resultSet.getDate("trRejectDate"));
            rejectData.setBiopsyDate(resultSet.getDate("trBiopsyDate"));
            rejectData.setFailureDate(resultSet.getDate("trFailureDate"));
            return rejectData;
        }
    }
}
