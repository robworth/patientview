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

package org.patientview.repository.impl;

import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.radar.Demographics;
import org.patientview.patientview.model.radar.Radar;
import org.patientview.patientview.model.radar.RadarUserMapping;
import org.patientview.patientview.model.radar.RadarUserMappingRowMapper;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.RadarDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "demographicsDao")
public class RadarDaoImpl extends AbstractHibernateDAO<Demographics> implements RadarDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    private SimpleJdbcInsert radarProfessionalUsersInsert;

    private SimpleJdbcInsert radarUserMappingInsert;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);

        radarProfessionalUsersInsert = new SimpleJdbcInsert(dataSource).withTableName(
                Radar.PROFESSIONAL_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(Radar.PROFESSIONAL_USER_ID_FIELD_NAME)
                .usingColumns(Radar.PROFESSIONAL_USER_SURNAME_FIELD_NAME, Radar.PROFESSIONAL_USER_FORENAME_FIELD_NAME,
                        Radar.PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME);

        radarUserMappingInsert = new SimpleJdbcInsert(dataSource).withTableName(Radar.USER_MAPPING_TABLE_NAME)
                .usingColumns(Radar.USER_MAPPING_USER_ID_FIELD_NAME, Radar.USER_MAPPING_ROLE_FIELD_NAME,
                        Radar.USER_MAPPING_RADAR_USER_ID_FIELD_NAME);
    }

    @Override
    public Demographics getDemographicsByNhsNo(String nhsno) {
        Query query = getEntityManager().createQuery(
                "SELECT radarNo "
                        + "FROM tbl_demographics "
                        + "WHERE NHS_NO = :nhsno");
        query.setParameter("nhsno", nhsno);

        List<Long> rawDemograpicsList = query.getResultList();

        Demographics demographics = new Demographics();

        if (rawDemograpicsList.size() != 0) {
            demographics.setRadarNo(rawDemograpicsList.get(0));
        }

        return demographics;
    }

    @Override
    public boolean userExistsInRadar(Long userId) {
        Integer count = jdbcTemplate.queryForInt("SELECT count(*) FROM rdr_user_mapping WHERE userId = " + userId);

        return count != null && count > 0;
    }

    @Override
    public void createProfessionalUserInRadar(User user, Unit unit) {
        String[] name = user.getName().split(" ");
        String forename = name[0];
        String surname = "";

        if (name.length > 1) {
            surname = name[1];

            if (name.length > 2) {
                for (int x = 2; x < name.length; x++) {
                    surname += " " + name[x];
                }
            }
        }

        // create the professional user record first
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put(Radar.PROFESSIONAL_USER_SURNAME_FIELD_NAME, surname);
        professionalUserMap.put(Radar.PROFESSIONAL_USER_FORENAME_FIELD_NAME, forename);
        professionalUserMap.put(Radar.PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME, unit.getId());

        Number professionalUserId = radarProfessionalUsersInsert.executeAndReturnKey(professionalUserMap);

        // then create the mapping from the professional user to the normal user
        Map<String, Object> userMappingMap = new HashMap<String, Object>();
        userMappingMap.put(Radar.USER_MAPPING_USER_ID_FIELD_NAME, user.getId());
        userMappingMap.put(Radar.USER_MAPPING_ROLE_FIELD_NAME, "ROLE_PROFESSIONAL");
        userMappingMap.put(Radar.USER_MAPPING_RADAR_USER_ID_FIELD_NAME, professionalUserId);

        radarUserMappingInsert.execute(userMappingMap);
    }

    @Override
    public void removeUserFromRadar(Long userId) {
        if (userExistsInRadar(userId)) {
            // work out what sort of user they are
            RadarUserMapping radarUserMapping = jdbcTemplate.queryForObject("SELECT * FROM rdr_user_mapping "
                    + "WHERE userId = " + userId, new RadarUserMappingRowMapper());

            // work out what tables we need to remove the user data from
            if (radarUserMapping != null) {
                if (radarUserMapping.getRole().equals("ROLE_ADMIN")) {
                    jdbcTemplate.execute("DELETE FROM tbladminusers WHERE uID = " + radarUserMapping.getRadarId());
                } else if (radarUserMapping.getRole().equals("ROLE_SUPER_USER")
                        || radarUserMapping.getRole().equals("ROLE_PROFESSIONAL")) {
                    jdbcTemplate.execute("DELETE FROM tbl_users WHERE uID = " + radarUserMapping.getRadarId());
                } else if (radarUserMapping.getRole().equals("ROLE_PATIENT")) {
                    jdbcTemplate.execute("DELETE FROM tbl_patient_users WHERE pID = " + radarUserMapping.getRadarId());
                }

                // finally delete the radar user mapping
                jdbcTemplate.execute("DELETE FROM rdr_user_mapping WHERE userId = " + userId);
            }
        }
    }
}
