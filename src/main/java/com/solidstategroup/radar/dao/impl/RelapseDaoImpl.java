package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.RelapseDao;
import com.solidstategroup.radar.model.enums.KidneyTransplantedNative;
import com.solidstategroup.radar.model.sequenced.Relapse;
import com.solidstategroup.radar.model.enums.RemissionAchieved;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RelapseDaoImpl extends BaseDaoImpl implements RelapseDao {

    public Relapse getRelapse(long id) {
        return jdbcTemplate
                .queryForObject("SELECT * FROM tbl_Relapse WHERE relID = ?", new Object[]{id}, new RelapseRowMapper());
    }

    public List<Relapse> getRelapsesByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_Relapse WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new RelapseRowMapper());
    }

    private class RelapseRowMapper implements RowMapper<Relapse> {
        public Relapse mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a relapse object and set the fields
            Relapse relapse = new Relapse();

            relapse.setId(resultSet.getLong("relID"));
            relapse.setRadarNumber(resultSet.getLong("RADAR_NO"));
            relapse.setDateOfRelapse(resultSet.getDate("DATE_ONSET_RELAP"));

            relapse.setTransplantedNative(
                    BaseDaoImpl.getEnumValue(KidneyTransplantedNative.class, resultSet.getInt("RELAP_TX_NAT")));

            relapse.setViralTrigger(resultSet.getString("TRIG_VIRAL"));
            relapse.setImmunisationTrigger(resultSet.getString("TRIG_IMMUN"));
            relapse.setOtherTrigger(resultSet.getString("TRIG_OTHER"));

            relapse.setDrug1(resultSet.getString("RELAP_DRUG_1"));
            relapse.setDrug2(resultSet.getString("RELAP_DRUG_2"));
            relapse.setDrug3(resultSet.getString("RELAP_DRUG_3"));

            relapse.setRemissionAchieved(
                    BaseDaoImpl.getEnumValue(RemissionAchieved.class, resultSet.getInt("REMISS_ACHIEVE")));

            relapse.setDateOfRemission(resultSet.getDate("DATE_REMISSION"));
            relapse.setSequenceNumber(resultSet.getInt("SEQ_NO"));

            return relapse;
        }
    }
}
