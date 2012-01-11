package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.ImmunosuppressionDao;
import com.solidstategroup.radar.model.Immunosuppression;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImmunosuppressionDaoImpl extends BaseDaoImpl implements ImmunosuppressionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmunosuppressionDaoImpl.class);

    public Immunosuppression getImmunosuppression(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_ImmunoSupp WHERE imID = ?", new Object[]{id},
                new ImmunosuppressionRowMapper());
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
