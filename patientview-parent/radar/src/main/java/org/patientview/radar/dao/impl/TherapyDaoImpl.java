package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.TherapyDao;
import org.patientview.radar.model.sequenced.Therapy;
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

public class TherapyDaoImpl extends BaseDaoImpl implements TherapyDao {

    private SimpleJdbcInsert therapyInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(TherapyDaoImpl.class);

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        therapyInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Therapy")
                .usingGeneratedKeyColumns("tID").usingColumns("RADAR_NO", "SIG_CHANGE_STATUS", "P_NSAID", "NSAID",
                        "P_DIURETIC", "DIURETIC", "P_ANTI_HTN", "ANTI_HTN", "P_ACE_INHIB", "ACE_INHIB", "P_ARB_ANTAG",
                        "ARB_ANTAG", "P_CA_CH_BLOCK", "CA_CH_BLOCK", "P_B_BLOCK", "B_BLOCK", "P_OTHER_ANTI_HTN",
                        "OTHER_ANTI_HTN", "P_INSULIN", "INSULIN", "P_LIP_LOWER_AG", "LIP_LOWER_AG", "P_EPO",
                        "EPO", "P_OTHER_DRUG1", "OTHER_DRUG1", "P_OTHER_DRUG2", "OTHER_DRUG2", "P_OTHER_DRUG3",
                        "OTHER_DRUG3", "P_OTHER_DRUG4", "OTHER_DRUG4", "P_IMMUN_SUP", "IMMUN_SUP", "P_IMMUN_SUP_DRUG",
                        "IMMUN_SUP_DRUG", "MONOCLONAL_YN", "MONOCLONAL_NAME", "DATE_TREAT", "TMT_MODALITY",
                        "SEQ_NO");
    }

    public void saveTherapy(final Therapy therapy) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", therapy.getRadarNumber());
                put("SIG_CHANGE_STATUS", null);
                put("P_NSAID", therapy.getNsaidPrior());
                put("NSAID", therapy.getNsaid());
                put("P_DIURETIC", therapy.getDiureticPrior());
                put("DIURETIC", therapy.getDiuretic());
                put("P_ANTI_HTN", therapy.getAntihypertensivePrior());
                put("ANTI_HTN", therapy.getAntihypertensive());
                put("P_ACE_INHIB", therapy.getAceInhibitorPrior());
                put("ACE_INHIB", therapy.getAceInhibitor());
                put("P_ARB_ANTAG", therapy.getArb1AntagonistPrior());
                put("ARB_ANTAG", therapy.getArb1Antagonist());
                put("P_CA_CH_BLOCK", therapy.getCalciumChannelBlockerPrior());
                put("CA_CH_BLOCK", therapy.getCalciumChannelBlocker());
                put("P_B_BLOCK", therapy.getBetaBlockerPrior());
                put("B_BLOCK", therapy.getBetaBlocker());
                put("P_OTHER_ANTI_HTN", therapy.getOtherAntihypertensivePrior());
                put("OTHER_ANTI_HTN", therapy.getOtherAntihypertensive());
                put("P_INSULIN", therapy.getInsulinPrior());
                put("INSULIN", therapy.getInsulin());
                put("P_LIP_LOWER_AG", therapy.getLipidLoweringAgentPrior());
                put("LIP_LOWER_AG", therapy.getLipidLoweringAgent());
                put("P_EPO", therapy.getEpoPrior());
                put("EPO", therapy.getEpo());
                put("P_OTHER_DRUG1", therapy.getOther1Prior());
                put("OTHER_DRUG1", therapy.getOther1());
                put("P_OTHER_DRUG2", therapy.getOther2Prior());
                put("OTHER_DRUG2", therapy.getOther2());
                put("P_OTHER_DRUG3", therapy.getOther3Prior());
                put("OTHER_DRUG3", therapy.getOther3());
                put("P_OTHER_DRUG4", therapy.getOther4Prior());
                put("OTHER_DRUG4", therapy.getOther4());
                put("P_IMMUN_SUP", null);
                put("IMMUN_SUP", null);
                put("P_IMMUN_SUP_DRUG", null);
                put("IMMUN_SUP_DRUG", null);
                put("MONOCLONAL_YN", null);
                put("MONOCLONAL_NAME", null);
                put("DATE_TREAT", therapy.getTreatmentRecordDate());
                put("TMT_MODALITY", null);
                put("SEQ_NO", therapy.getSequenceNumber());
            }
        };

        if (therapy.hasValidId()) {
            therapMap.put("tID", therapy.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_Therapy " +
                    "SET " +
                    "RADAR_NO = :RADAR_NO, " +
                    "SIG_CHANGE_STATUS = :SIG_CHANGE_STATUS, " +
                    "P_NSAID = :P_NSAID, " +
                    "NSAID = :NSAID, " +
                    "P_DIURETIC = :P_DIURETIC, " +
                    "DIURETIC = :DIURETIC, " +
                    "P_ANTI_HTN = :P_ANTI_HTN, " +
                    "ANTI_HTN = :ANTI_HTN, " +
                    "P_ACE_INHIB = :P_ACE_INHIB, " +
                    "ACE_INHIB = :ACE_INHIB, " +
                    "P_ARB_ANTAG = :P_ARB_ANTAG, " +
                    "ARB_ANTAG = :ARB_ANTAG, " +
                    "P_CA_CH_BLOCK = :P_CA_CH_BLOCK, " +
                    "CA_CH_BLOCK = :CA_CH_BLOCK, " +
                    "P_B_BLOCK = :P_B_BLOCK, " +
                    "B_BLOCK = :B_BLOCK, " +
                    "P_OTHER_ANTI_HTN = :P_OTHER_ANTI_HTN, " +
                    "OTHER_ANTI_HTN = :OTHER_ANTI_HTN, " +
                    "P_INSULIN = :P_INSULIN, " +
                    "INSULIN = :INSULIN, " +
                    "P_LIP_LOWER_AG = :P_LIP_LOWER_AG, " +
                    "LIP_LOWER_AG = :LIP_LOWER_AG, " +
                    "P_EPO = :P_EPO, " +
                    "EPO = :EPO, " +
                    "P_OTHER_DRUG1 = :P_OTHER_DRUG1, " +
                    "OTHER_DRUG1 = :OTHER_DRUG1, " +
                    "P_OTHER_DRUG2 = :P_OTHER_DRUG2, " +
                    "OTHER_DRUG2 = :OTHER_DRUG2, " +
                    "P_OTHER_DRUG3 = :P_OTHER_DRUG3, " +
                    "OTHER_DRUG3 = :OTHER_DRUG3, " +
                    "P_OTHER_DRUG4 = :P_OTHER_DRUG4, " +
                    "OTHER_DRUG4 = :OTHER_DRUG4, " +
                    "P_IMMUN_SUP = :P_IMMUN_SUP, " +
                    "IMMUN_SUP = :IMMUN_SUP, " +
                    "P_IMMUN_SUP_DRUG = :P_IMMUN_SUP_DRUG, " +
                    "IMMUN_SUP_DRUG = :IMMUN_SUP_DRUG, " +
                    "MONOCLONAL_YN = :MONOCLONAL_YN, " +
                    "MONOCLONAL_NAME = :MONOCLONAL_NAME, " +
                    "DATE_TREAT = :DATE_TREAT, " +
                    "TMT_MODALITY = :TMT_MODALITY, " +
                    "SEQ_NO = :SEQ_NO " +
                    " WHERE tID = :tID; ", therapMap);
        } else {
            Number id = therapyInsert.executeAndReturnKey(therapMap);
            therapy.setId(id.longValue());
        }

    }

    public Therapy getTherapy(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Therapy WHERE tID = ?", new Object[]{id},
                    new TherapyRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No therapy object found with ID {}", id);
            return null;
        }
    }

    public List<Therapy> getTherapyByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_Therapy WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new TherapyRowMapper());
    }

    public Therapy getFirstTherapyByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Therapy WHERE RADAR_NO = ? AND SEQ_NO = 1",
                    new Object[]{radarNumber}, new TherapyRowMapper());

        } catch (EmptyResultDataAccessException e) {
             return null;
        }
    }

    private class TherapyRowMapper implements RowMapper<Therapy> {
        public Therapy mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a therapy object and populate the fields
            Therapy therapy = new Therapy();

            therapy.setId(resultSet.getLong("tID"));
            therapy.setRadarNumber(resultSet.getLong("RADAR_NO"));
            therapy.setTreatmentRecordDate(resultSet.getDate("DATE_TREAT"));

            therapy.setNsaid(resultSet.getBoolean("NSAID"));
            therapy.setNsaidPrior(resultSet.getBoolean("P_NSAID"));

            therapy.setDiuretic(resultSet.getBoolean("DIURETIC"));
            therapy.setDiureticPrior(resultSet.getBoolean("P_DIURETIC"));

            therapy.setAntihypertensive(resultSet.getBoolean("ANTI_HTN"));
            therapy.setAntihypertensivePrior(resultSet.getBoolean("P_ANTI_HTN"));

            therapy.setAceInhibitor(resultSet.getBoolean("ACE_INHIB"));
            therapy.setAceInhibitorPrior(resultSet.getBoolean("P_ACE_INHIB"));

            therapy.setArb1Antagonist(resultSet.getBoolean("ARB_ANTAG"));
            therapy.setArb1AntagonistPrior(resultSet.getBoolean("P_ARB_ANTAG"));

            therapy.setCalciumChannelBlocker(resultSet.getBoolean("CA_CH_BLOCK"));
            therapy.setCalciumChannelBlockerPrior(resultSet.getBoolean("P_CA_CH_BLOCK"));

            therapy.setBetaBlocker(resultSet.getBoolean("B_BLOCK"));
            therapy.setBetaBlockerPrior(resultSet.getBoolean("P_B_BLOCK"));

            therapy.setOtherAntihypertensive(resultSet.getBoolean("OTHER_ANTI_HTN"));
            therapy.setOtherAntihypertensivePrior(resultSet.getBoolean("P_OTHER_ANTI_HTN"));

            therapy.setInsulin(resultSet.getBoolean("INSULIN"));
            therapy.setInsulinPrior(resultSet.getBoolean("P_INSULIN"));

            therapy.setLipidLoweringAgent(resultSet.getBoolean("LIP_LOWER_AG"));
            therapy.setLipidLoweringAgentPrior(resultSet.getBoolean("P_LIP_LOWER_AG"));

            therapy.setEpo(resultSet.getBoolean("EPO"));
            therapy.setEpoPrior(resultSet.getBoolean("P_EPO"));

            // All the drug fields
            therapy.setOther1(resultSet.getString("OTHER_DRUG1"));
            therapy.setOther1Prior(resultSet.getString("P_OTHER_DRUG1"));
            therapy.setOther2(resultSet.getString("OTHER_DRUG2"));
            therapy.setOther2Prior(resultSet.getString("P_OTHER_DRUG2"));
            therapy.setOther3(resultSet.getString("OTHER_DRUG3"));
            therapy.setOther3Prior(resultSet.getString("P_OTHER_DRUG3"));
            therapy.setOther4(resultSet.getString("OTHER_DRUG4"));
            therapy.setOther4Prior(resultSet.getString("P_OTHER_DRUG4"));

            // Commented out fields in legacy code: MONOCLONAL_YN, MONOCLONAL_NAME, IMMUN_SUP_DRUG, P_IMMUN_SUP_DRUG

            // I'm pretty sure these are commented too - P_IMMUN_SUP, IMMUN_SUP

            // There is a SIG_CHANGE_STATUS but I can't tell what this is for

            therapy.setSequenceNumber(resultSet.getInt("SEQ_NO"));
            return therapy;
        }
    }

}
