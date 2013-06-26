package org.patientview.radar.util.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.user.AdminUser;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.util.TripleDes;
import org.patientview.radar.util.UserUpgradeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "userUpgradeManager")
public class RadarRpvSingleUserTableExport implements UserUpgradeManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarRpvSingleUserTableExport.class);

    // user table fields
    private static final String USER_TABLE_NAME = "user"; // main user table
    private static final String ID_FIELD_NAME = "id";
    private static final String USER_USERNAME_FIELD_NAME = "username";
    private static final String USER_PASSWORD_FIELD_NAME = "password";
    private static final String USER_EMAIL_FIELD_NAME = "email";
    private static final String USER_NAME_FIELD_NAME = "name";
    private static final String USER_DUMMY_PATIENT_FIELD_NAME = "dummypatient";
    private static final String USER_EMAIL_VERIFIED_FIELD_NAME = "emailverified";

    // professional table fields
    private static final String PROFESSIONAL_USER_TABLE_NAME = "tbl_users"; // rdr specific user
    private static final String PROFESSIONAL_USER_ID_FIELD_NAME = "uID";
    private static final String PROFESSIONAL_USER_SURNAME_FIELD_NAME = "uSurname";
    private static final String PROFESSIONAL_USER_FORENAME_FIELD_NAME = "uForename";
    private static final String PROFESSIONAL_USER_TITLE_FIELD_NAME = "uTitle";
    private static final String PROFESSIONAL_USER_PHONE_FIELD_NAME = "uPhone";
    private static final String PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME = "uDateJoin";
    private static final String PROFESSIONAL_USER_GMC_FIELD_NAME = "uGMC";
    private static final String PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME = "uCentre";
    private static final String PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME = "uRole";

    @Autowired
    private UserDao userDao;

    @Autowired
    private UtilityDao utilityDao;

    @Autowired
    private DemographicsDao demographicsDao;

    @Autowired
    private ComboPooledDataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert userInsert;

    private SimpleJdbcInsert professionalUsersInsert;

    List<String> failedUsers = new ArrayList<String>();
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

        userInsert = new SimpleJdbcInsert(dataSource).withTableName(USER_TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(USER_USERNAME_FIELD_NAME, USER_PASSWORD_FIELD_NAME,
                        USER_EMAIL_FIELD_NAME, USER_NAME_FIELD_NAME, USER_DUMMY_PATIENT_FIELD_NAME);

        professionalUsersInsert = new SimpleJdbcInsert(dataSource).withTableName(PROFESSIONAL_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(PROFESSIONAL_USER_ID_FIELD_NAME)
                .usingColumns(PROFESSIONAL_USER_SURNAME_FIELD_NAME, PROFESSIONAL_USER_FORENAME_FIELD_NAME,
                        PROFESSIONAL_USER_TITLE_FIELD_NAME, PROFESSIONAL_USER_GMC_FIELD_NAME,
                        PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME,
                        PROFESSIONAL_USER_PHONE_FIELD_NAME, PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME,
                        PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME);
    }

    public void run() {
        /**
         * first we need to grab all the unitadmins from patient view as we want to create logins for these in radar
         * but dont save them yet as we dont want to then move all the radar logins over to patient view and do it all
         * twice
         *
         * grab unit admins from patient view
         *
         * then move all users from radar over to PV
         *
         * then use the unit admins we got at start and move them into radar
         *
         * SIMPLES!
         */
        List<ProfessionalUser> patientViewUnitAdmins = jdbcTemplate.query(
                "SELECT " +
                "   u.*, " +
                "   un.id AS centreId " +
                " FROM " +
                "   USER u, " +
                "   specialtyuserrole sur, " +
                "   usermapping um, " +
                "   unit un " +
                " WHERE " +
                "   sur.user_id = u.id " +
                " AND " +
                "   sur.role = 'unitstaff' " +
                " AND " +
                "   um.username = u.username " +
                " AND " +
                "   un.unitcode = um.unitcode ", new PatientViewUnitAdminRowMapper());



        // move pv users to radar
        for (ProfessionalUser professionalUser : patientViewUnitAdmins) {
            // check this user isnt in radar already as it will already have been mapped above
            if (!checkForProfessionalUser(professionalUser.getEmail())) {
                try {
                    Map<String, Object> professionalUserMap = new HashMap<String, Object>();
                    professionalUserMap.put(PROFESSIONAL_USER_SURNAME_FIELD_NAME, professionalUser.getSurname());
                    professionalUserMap.put(PROFESSIONAL_USER_FORENAME_FIELD_NAME, professionalUser.getForename());
                    professionalUserMap.put(PROFESSIONAL_USER_TITLE_FIELD_NAME, professionalUser.getTitle());
                    professionalUserMap.put(PROFESSIONAL_USER_GMC_FIELD_NAME, professionalUser.getGmc());
                    professionalUserMap.put(PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME, professionalUser.getRole());
                    professionalUserMap.put(PROFESSIONAL_USER_PHONE_FIELD_NAME, professionalUser.getPhone());
                    professionalUserMap.put(PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME,
                            professionalUser.getCentre().getId());
                    professionalUserMap.put(PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME,
                            professionalUser.getDateRegistered());

                    Number id = professionalUsersInsert.executeAndReturnKey(professionalUserMap);
                    professionalUser.setId(id.longValue());

                    userDao.saveUserMapping(professionalUser);
                    LOGGER.info("updated unitstaff: " + professionalUser.getUsername());
                } catch (Exception e) {
                    failedUsers.add("PV unitadmin failed: " + professionalUser.getId() + " - "
                            + professionalUser.getEmail());
                }
            }
        }

        for (String s : failedUsers) {
            LOGGER.error(s);
        }
    }

    private String decryptField(byte[] fieldData) throws Exception {
        byte[] copy = Arrays.copyOf(fieldData, fieldData.length);
        return TripleDes.decrypt(copy);
    }

    private class AdminUserRowMapper implements RowMapper<AdminUser> {
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setId(resultSet.getLong("uID"));
            adminUser.setName(resultSet.getString("uName"));
            adminUser.setEmail(resultSet.getString("uEmail"));

            try {
                adminUser.setPassword(User.getPasswordHash(decryptField(resultSet.getBytes("uPass"))));
                adminUser.setUsername(decryptField(resultSet.getBytes("uUserName")));
            } catch (Exception e) {
                LOGGER.error("Could not decrypt user information for admin user ", adminUser.getId());
            }

            return adminUser;
        }
    }

    private class ProfessionalUserRowMapper implements RowMapper<ProfessionalUser> {
        public ProfessionalUser mapRow(ResultSet resultSet, int i) throws SQLException {
            ProfessionalUser professionalUser = new ProfessionalUser();

            // now map the specific props for this user in radar
            professionalUser.setId(resultSet.getLong("uID"));
            professionalUser.setSurname(resultSet.getString("uSurname"));
            professionalUser.setForename(resultSet.getString("uForename"));
            professionalUser.setTitle(resultSet.getString("uTitle"));
            professionalUser.setGmc(resultSet.getString("uGMC"));
            professionalUser.setRole(resultSet.getString("uRole"));
            professionalUser.setPhone(resultSet.getString("uPhone"));
            professionalUser.setEmail(resultSet.getString("uEmail"));
            professionalUser.setDateRegistered(resultSet.getDate("uDateJoin"));

            // Set the centre
            Long centreId = resultSet.getLong("uCentre");
            if (centreId != null && centreId > 0) {
                professionalUser.setCentre(utilityDao.getCentre(centreId));
            }

            try {
                professionalUser.setPassword(User.getPasswordHash(decryptField(resultSet.getBytes("uPass"))));
                professionalUser.setUsername(decryptField(resultSet.getBytes("uUserName")));
            } catch (Exception e) {
                professionalUser.setUsername(resultSet.getString("uEmail"));
            }

            return professionalUser;
        }
    }

    private class PatientUserRowMapper implements RowMapper<PatientUser> {
        public PatientUser mapRow(ResultSet resultSet, int i) throws SQLException {
            PatientUser patientUser = new PatientUser();

            patientUser.setId(resultSet.getLong("pID"));
            patientUser.setRadarNumber(resultSet.getLong("RADAR_NO"));
            patientUser.setDateOfBirth(resultSet.getDate("pDOB"));
            patientUser.setDateRegistered(resultSet.getDate("pDateReg"));
            patientUser.setUsername(resultSet.getString("pUserName"));

            try {
                patientUser.setPassword(User.getPasswordHash(decryptField(resultSet.getBytes("pPassWord"))));
            } catch (Exception e) {
                LOGGER.error("Could not decrypt user information for patient user ", patientUser.getId());
            }

            patientUser.setEmail(patientUser.getUsername());

            return patientUser;
        }
    }

    private class PatientViewUnitAdminRowMapper implements RowMapper<ProfessionalUser> {
        public ProfessionalUser mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            ProfessionalUser professionalUser = new ProfessionalUser();

            professionalUser.setUserId(resultSet.getLong("id"));

            // name in pv is one field so just split on first space and hope its the surname
            String[] name = resultSet.getString("name").split(" ");
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

            professionalUser.setSurname(surname);
            professionalUser.setForename(forename);
            professionalUser.setEmail(resultSet.getString("email"));
            professionalUser.setDateRegistered(new Date());

            // Set the centre
            Long centreId = resultSet.getLong("centreId");
            if (centreId != null && centreId > 0) {
                professionalUser.setCentre(utilityDao.getCentre(centreId));
            }

            return professionalUser;
        }
    }

    private boolean checkForUsername(String username) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        return jdbcTemplate.queryForInt(sql, username) > 0;
    }

    private boolean checkForProfessionalUser(String email) {
        String sql = "SELECT COUNT(*) FROM tbl_users WHERE uEmail = ?";
        return jdbcTemplate.queryForInt(sql, email) > 0;
    }

    private Long getGPUserId(String username) {
        String sql = "SELECT id FROM user WHERE username = '" + username + "'";
        List<Long> gpUserId = jdbcTemplate.query(sql, new RowMapper<Long>() {
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("id");
            }
        });

        if (!gpUserId.isEmpty()) {
            return gpUserId.get(0);
        }

        return null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }
}
