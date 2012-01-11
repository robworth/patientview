package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.TherapyDao;
import com.solidstategroup.radar.model.sequenced.Therapy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TherapyDaoImpl extends BaseDaoImpl implements TherapyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TherapyDaoImpl.class);

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

    private class TherapyRowMapper implements RowMapper<Therapy> {
        public Therapy mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a therapy object and populate the fields
            Therapy therapy = new Therapy();

            therapy.setId(resultSet.getLong("tID"));
            therapy.setRadarNumber(resultSet.getLong("RADAR_NO"));
            therapy.setDate(resultSet.getDate("DATE_TREAT"));

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
