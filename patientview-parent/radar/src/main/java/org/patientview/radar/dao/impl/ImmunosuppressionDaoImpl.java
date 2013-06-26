package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.ImmunosuppressionDao;
import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;
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

public class ImmunosuppressionDaoImpl extends BaseDaoImpl implements ImmunosuppressionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmunosuppressionDaoImpl.class);
    private SimpleJdbcInsert immunoSuppressionTreatmentInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        immunoSuppressionTreatmentInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_immunsup_treatment")
                .usingGeneratedKeyColumns("tID").usingColumns("RADAR_NO", "IMMUNSUP_DRUG_STARTDATE",
                        "IMMUNSUP_DRUG_ENDDATE", "IMMUNSUP_DRUG", "CYCLOPHOS_TOT_DOSE", "FIRST_FLAG");
    }

    public void saveImmunosuppressionTreatment(final ImmunosuppressionTreatment immunosuppressionTreatment) {
        Map<String, Object> immunoSuppressionTreatmentMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", immunosuppressionTreatment.getRadarNumber());
                put("IMMUNSUP_DRUG_STARTDATE", immunosuppressionTreatment.getStartDate());
                put("IMMUNSUP_DRUG_ENDDATE", immunosuppressionTreatment.getEndDate());
                put("IMMUNSUP_DRUG", immunosuppressionTreatment.getImmunosuppression() != null ?
                        immunosuppressionTreatment.getImmunosuppression().getId() : null);
                put("CYCLOPHOS_TOT_DOSE", immunosuppressionTreatment.getCyclophosphamideTotalDose());
                put("FIRST_FLAG", immunosuppressionTreatment.getFirstFlag());
            }
        };

        if (immunosuppressionTreatment.hasValidId()) {
            immunoSuppressionTreatmentMap.put("tID", immunosuppressionTreatment.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_immunsup_treatment " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "IMMUNSUP_DRUG_STARTDATE = :IMMUNSUP_DRUG_STARTDATE, " +
                    "IMMUNSUP_DRUG_ENDDATE = :IMMUNSUP_DRUG_ENDDATE, " +
                    "IMMUNSUP_DRUG = :IMMUNSUP_DRUG, " +
                    "CYCLOPHOS_TOT_DOSE = :CYCLOPHOS_TOT_DOSE, " +
                    "FIRST_FLAG = :FIRST_FLAG " +
                    " WHERE tID = :tID;", immunoSuppressionTreatmentMap);

        } else {
            Number id = immunoSuppressionTreatmentInsert.executeAndReturnKey(immunoSuppressionTreatmentMap);
            immunosuppressionTreatment.setId(id.longValue());
        }
    }

    public void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment) {
        Map<String, Object> immunoSuppressionTreatmentMap = new HashMap<String, Object>();
        immunoSuppressionTreatmentMap.put("tID", immunosuppressionTreatment.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_immunsup_treatment " +
                "WHERE tID = :tID;", immunoSuppressionTreatmentMap);
    }

    public ImmunosuppressionTreatment getImmunosuppressionTreatment(long id) {

        // Old SQL:

        // SELECT tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG, tbl_IMMUNSUP_TREATMENT.FIRST_FLAG, tbl_IMMUNSUP_TREATMENT.tID,
        // tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG_STARTDATE, tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG_ENDDATE,
        // tbl_IMMUNSUP_TREATMENT.CYCLOPHOS_TOT_DOSE, tbl_ImmunoSupp.imDesc
        // FROM tbl_IMMUNSUP_TREATMENT
        // INNER JOIN tbl_ImmunoSupp ON tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG = tbl_ImmunoSupp.imID
        // WHERE (tbl_IMMUNSUP_TREATMENT.RADAR_NO = @RADAR_NO )
        // ORDER BY tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG_STARTDATE DESC
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_IMMUNSUP_TREATMENT WHERE tID = ?", new Object[]{id},
                    new ImmunosuppressionTreatmentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get result for tbl_IMMUNSUP_TREATMENT with ID {}", id);
            return null;
        }
    }

    public List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_IMMUNSUP_TREATMENT WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new ImmunosuppressionTreatmentRowMapper());
    }

    public List<Immunosuppression> getImmunosuppressions() {
        return jdbcTemplate.query("SELECT * FROM tbl_ImmunoSupp", new ImmunosuppressionRowMapper());
    }


    public Immunosuppression getImmunosuppression(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_ImmunoSupp WHERE imID = ?", new Object[]{id},
                new ImmunosuppressionRowMapper());
    }

    private class ImmunosuppressionRowMapper implements RowMapper<Immunosuppression> {

        public Immunosuppression mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct an object and set the fields
            Immunosuppression immunosuppression = new Immunosuppression();
            immunosuppression.setId(resultSet.getLong("imID"));
            immunosuppression.setDescription(resultSet.getString("imDesc"));
            immunosuppression.setGroup(resultSet.getInt("Group"));
            return immunosuppression;
        }
    }

    private class ImmunosuppressionTreatmentRowMapper implements RowMapper<ImmunosuppressionTreatment> {
        public ImmunosuppressionTreatment mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct object and set fields
            ImmunosuppressionTreatment immunosuppressionTreatment = new ImmunosuppressionTreatment();
            immunosuppressionTreatment.setId(resultSet.getLong("tID"));
            immunosuppressionTreatment.setRadarNumber(resultSet.getLong("RADAR_NO"));

            // Dates
            immunosuppressionTreatment.setStartDate(resultSet.getDate("IMMUNSUP_DRUG_STARTDATE"));
            immunosuppressionTreatment.setEndDate(resultSet.getDate("IMMUNSUP_DRUG_ENDDATE"));

            // Set drug
            long immunosuppressionId = resultSet.getLong("IMMUNSUP_DRUG");
            if (immunosuppressionId > 0) {
                immunosuppressionTreatment.setImmunosuppression(getImmunosuppression(immunosuppressionId));
            }
            immunosuppressionTreatment.setCyclophosphamideTotalDose(resultSet.getDouble("CYCLOPHOS_TOT_DOSE"));

            // The mystery field...
            immunosuppressionTreatment.setFirstFlag(resultSet.getBoolean("FIRST_FLAG"));

            return immunosuppressionTreatment;
        }
    }

}
