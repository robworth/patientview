/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.RelapseDao;
import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.enums.RemissionAchieved;
import org.patientview.radar.model.sequenced.Relapse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelapseDaoImpl extends BaseDaoImpl implements RelapseDao {

    private SimpleJdbcInsert relapseInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        relapseInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_relapse")
                .usingGeneratedKeyColumns("relID").usingColumns("RADAR_NO", "DATE_ONSET_RELAP", "RELAP_TX_NAT",
                        "TRIG_VIRAL", "TRIG_IMMUN", "TRIG_OTHER", "RELAP_DRUG_1", "RELAP_DRUG_2", "RELAP_DRUG_3",
                        "REMISS_ACHIEVE", "DATE_REMISSION", "SEQ_NO");
    }

    public void saveRelapse(final Relapse relapse) {
        Map<String, Object> relapseMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", relapse.getRadarNumber());
                put("DATE_ONSET_RELAP", relapse.getDateOfRelapse());
                put("RELAP_TX_NAT", relapse.getTransplantedNative() != null ?
                        relapse.getTransplantedNative().getId() : null);
                put("TRIG_VIRAL", relapse.getViralTrigger());
                put("TRIG_IMMUN", relapse.getImmunisationTrigger());
                put("TRIG_OTHER", relapse.getOtherTrigger());
                put("RELAP_DRUG_1", relapse.getDrug1());
                put("RELAP_DRUG_2", relapse.getDrug2());
                put("RELAP_DRUG_3", relapse.getDrug3());
                put("REMISS_ACHIEVE", relapse.getRemissionAchieved() != null ?
                        relapse.getRemissionAchieved().getId() : null);
                put("DATE_REMISSION", relapse.getDateOfRemission());
                put("SEQ_NO", relapse.getSequenceNumber());
            }
        };

        if (relapse.hasValidId()) {
            relapseMap.put("relID", relapse.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_relapse " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "DATE_ONSET_RELAP = :DATE_ONSET_RELAP, " +
                    "RELAP_TX_NAT = :RELAP_TX_NAT, " +
                    "TRIG_VIRAL = :TRIG_VIRAL, " +
                    "TRIG_IMMUN = :TRIG_IMMUN, " +
                    "TRIG_OTHER = :TRIG_OTHER, " +
                    "RELAP_DRUG_1 = :RELAP_DRUG_1, " +
                    "RELAP_DRUG_2 = :RELAP_DRUG_2, " +
                    "RELAP_DRUG_3 = :RELAP_DRUG_3, " +
                    "REMISS_ACHIEVE = :REMISS_ACHIEVE, " +
                    "DATE_REMISSION = :DATE_REMISSION, " +
                    "SEQ_NO = :SEQ_NO " +
                    " WHERE relID = :relID;", relapseMap);

        } else {
            Number id = relapseInsert.executeAndReturnKey(relapseMap);
            relapse.setId(id.longValue());
        }
    }

    public void deleteRelapse(Relapse relapse) {
        Map<String, Object> relapseMap = new HashMap<String, Object>();
        relapseMap.put("relID", relapse.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_relapse " +
                "WHERE relID = :relID;", relapseMap);
    }

    public Relapse getRelapse(long id) {
        try {
            return jdbcTemplate
                    .queryForObject("SELECT * FROM tbl_Relapse WHERE relID = ?",
                            new Object[]{id}, new RelapseRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

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
