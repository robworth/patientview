package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.filter.PatientUserFilter;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.user.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    // tables
    private static final String USER_TABLE_NAME = "user"; // main user table
    private static final String USER_MAPPING_TABLE_NAME = "rdr_user_mapping"; // maps user accounts to roles in radar
    private static final String ADMIN_USER_TABLE_NAME = "tbl_adminusers"; // maps user accounts to roles in radar
    private static final String PROFESSIONAL_USER_TABLE_NAME = "tbl_users"; // rdr specific user
    private static final String PATIENT_USER_TABLE_NAME = "tbl_patient_users"; // rdr specific patient information

    // user mapping table fields
    private static final String ID_FIELD_NAME = "id";
    private static final String USER_MAPPING_USER_ID_FIELD_NAME = "userId";
    private static final String USER_MAPPING_ROLE_FIELD_NAME = "role";
    private static final String USER_MAPPING_RADAR_USER_ID_FIELD_NAME = "radarUserId";

    // user table fields
    private static final String USER_USERNAME_FIELD_NAME = "username";
    private static final String USER_PASSWORD_FIELD_NAME = "password";
    private static final String USER_EMAIL_FIELD_NAME = "email";
    private static final String USER_NAME_FIELD_NAME = "name";
    private static final String USER_SCREEN_NAME_FIELD_NAME = "screenname";
    private static final String USER_DUMMY_PATIENT_FIELD_NAME = "dummypatient";

    // admin user fields
    private static final String ADMIN_USER_ID_FIELD_NAME = "uID";
    private static final String ADMIN_USER_NAME_FIELD_NAME = "uName";

    // patient table fields
    private static final String PATIENT_USER_ID_FIELD_NAME = "pId";
    private static final String PATIENT_USER_RADAR_NO_FIELD_NAME = "RADAR_NO";
    private static final String PATIENT_USER_DOB_FIELD_NAME = "pDOB";
    private static final String PATIENT_USER_DATE_OF_REGISTRATION_FIELD_NAME = "pDateReg";

    // professional user table fields
    private static final String PROFESSIONAL_USER_ID_FIELD_NAME = "uID";
    private static final String PROFESSIONAL_USER_SURNAME_FIELD_NAME = "uSurname";
    private static final String PROFESSIONAL_USER_FORENAME_FIELD_NAME = "uForename";
    private static final String PROFESSIONAL_USER_TITLE_FIELD_NAME = "uTitle";
    private static final String PROFESSIONAL_USER_PHONE_FIELD_NAME = "uPhone";
    private static final String PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME = "uDateJoin";
    private static final String PROFESSIONAL_USER_GMC_FIELD_NAME = "uGMC";
    private static final String PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME = "uCentre";
    private static final String PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME = "uRole";

    private SimpleJdbcInsert userInsert;
    private SimpleJdbcInsert userMappingInsert;
    private SimpleJdbcInsert adminUsersInsert;
    private SimpleJdbcInsert professionalUsersInsert;
    private SimpleJdbcInsert patientUsersInsert;

    private UtilityDao utilityDao;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);

        userInsert = new SimpleJdbcInsert(dataSource).withTableName(USER_TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(USER_USERNAME_FIELD_NAME, USER_PASSWORD_FIELD_NAME,
                        USER_EMAIL_FIELD_NAME, USER_NAME_FIELD_NAME, USER_DUMMY_PATIENT_FIELD_NAME,
                        USER_SCREEN_NAME_FIELD_NAME);

        userMappingInsert = new SimpleJdbcInsert(dataSource).withTableName(USER_MAPPING_TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(USER_MAPPING_USER_ID_FIELD_NAME, USER_MAPPING_ROLE_FIELD_NAME,
                        USER_MAPPING_RADAR_USER_ID_FIELD_NAME);

        adminUsersInsert = new SimpleJdbcInsert(dataSource).withTableName(ADMIN_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(ADMIN_USER_ID_FIELD_NAME)
                .usingColumns(ADMIN_USER_NAME_FIELD_NAME);

        professionalUsersInsert = new SimpleJdbcInsert(dataSource).withTableName(PROFESSIONAL_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(PROFESSIONAL_USER_ID_FIELD_NAME)
                .usingColumns(PROFESSIONAL_USER_SURNAME_FIELD_NAME, PROFESSIONAL_USER_FORENAME_FIELD_NAME,
                        PROFESSIONAL_USER_TITLE_FIELD_NAME, PROFESSIONAL_USER_GMC_FIELD_NAME,
                        PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME,
                        PROFESSIONAL_USER_PHONE_FIELD_NAME, PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME,
                        PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME);

        patientUsersInsert = new SimpleJdbcInsert(dataSource).withTableName(PATIENT_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(PATIENT_USER_ID_FIELD_NAME)
                .usingColumns(PATIENT_USER_RADAR_NO_FIELD_NAME, PATIENT_USER_DOB_FIELD_NAME,
                        PATIENT_USER_DATE_OF_REGISTRATION_FIELD_NAME);
    }

    public AdminUser getAdminUser(String email) {
        try {
            return jdbcTemplate.queryForObject(buildBaseUserSelectFromStatement(ADMIN_USER_TABLE_NAME)
                    + buildUserWhereEmailStatement(ADMIN_USER_TABLE_NAME, ADMIN_USER_ID_FIELD_NAME),
                    new Object[]{email, User.ROLE_ADMIN}, new AdminUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not admin user " + USER_TABLE_NAME + " with " + USER_EMAIL_FIELD_NAME + " {}", email);
        }

        return null;
    }

    public List<AdminUser> getAdminUsers() {
        String sql = buildBaseUserSelectFromStatement(ADMIN_USER_TABLE_NAME) +
                " WHERE " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_ROLE_FIELD_NAME
                + " = '" + User.ROLE_ADMIN + "'" + " " +
                " AND " + ADMIN_USER_TABLE_NAME + "." + ADMIN_USER_ID_FIELD_NAME
                + " = " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_RADAR_USER_ID_FIELD_NAME +
                " AND " + USER_TABLE_NAME + "." + ID_FIELD_NAME
                + " = " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_USER_ID_FIELD_NAME;

        return jdbcTemplate.query(sql, new Object[]{}, new AdminUserRowMapper());
    }

    public void saveAdminUser(final AdminUser adminUser) {
        // save details of the user into the radar tables
        Map<String, Object> adminUserMap = new HashMap<String, Object>() {
            {
                put(ADMIN_USER_NAME_FIELD_NAME, adminUser.getName());
                put(ADMIN_USER_ID_FIELD_NAME, adminUser.getId());
            }
        };

        if (adminUser.hasValidId()) {
            String updateSql = buildUpdateQuery(ADMIN_USER_TABLE_NAME, ADMIN_USER_ID_FIELD_NAME, adminUserMap);
            namedParameterJdbcTemplate.update(updateSql, adminUserMap);
        } else {
            Number id = adminUsersInsert.executeAndReturnKey(adminUserMap);
            adminUser.setId(id.longValue());
        }

        // save main user login into the shared rpv table
        saveUser(adminUser);
    }

    public ProfessionalUser getProfessionalUser(Long id) {
        try {
            return jdbcTemplate.queryForObject(buildBaseUserSelectFromStatement(PROFESSIONAL_USER_TABLE_NAME)
                    + buildUserWhereIdStatement(PROFESSIONAL_USER_TABLE_NAME, PROFESSIONAL_USER_ID_FIELD_NAME),
                    new Object[]{id, User.ROLE_PROFESSIONAL}, new ProfessionalUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not professional user with " + PROFESSIONAL_USER_ID_FIELD_NAME + " {}", id);
        }

        return null;
    }

    public ProfessionalUser getProfessionalUser(String email) {
        try {
            return jdbcTemplate.queryForObject(buildBaseUserSelectFromStatement(PROFESSIONAL_USER_TABLE_NAME)
                    + buildUserWhereEmailStatement(PROFESSIONAL_USER_TABLE_NAME, PROFESSIONAL_USER_ID_FIELD_NAME),
                    new Object[]{email, User.ROLE_PROFESSIONAL}, new ProfessionalUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not professional user with " + USER_EMAIL_FIELD_NAME + " {}", email);
        }

        return null;
    }

    public List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage) {
        if (filter == null) {
            filter = new ProfessionalUserFilter();
        }

        List<String> sqlQueries = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        // normal sql query without any filter options
        sqlQueries.add(buildSelectFromStatement(USER_TABLE_NAME, USER_MAPPING_TABLE_NAME,
                PROFESSIONAL_USER_TABLE_NAME, "tbl_Centres") +
                " WHERE " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_ROLE_FIELD_NAME
                    + " = '" + User.ROLE_PROFESSIONAL + "'" + " " +
                " AND " + PROFESSIONAL_USER_TABLE_NAME + "." + PROFESSIONAL_USER_ID_FIELD_NAME
                    + " = " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_RADAR_USER_ID_FIELD_NAME +
                " AND " + USER_TABLE_NAME + "." + ID_FIELD_NAME
                    + " = " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_USER_ID_FIELD_NAME +
                " AND " + PROFESSIONAL_USER_TABLE_NAME + "." + PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME
                    + " = tbl_Centres.cID"
        );

        // if there are search queries then build the where
        if (filter.hasSearchCriteria()) {
            sqlQueries.add(" AND " + buildWhereQuery(false, filter.getSearchFields(), true, params));
        }

        // if the filter has a sort then order by it
        if (filter.hasSortFilter()) {
            sqlQueries.add(buildOrderQuery(filter.getSortField(), filter.isReverse()));
        }

        // if a range has been set limit the results
        sqlQueries.add(buildLimitQuery(page, numberPerPage, params));

        // combine the statement and return result
        return jdbcTemplate.query(StringUtils.join(sqlQueries.toArray(), " "), params.toArray(),
                new ProfessionalUserRowMapper());
    }

    public void saveProfessionalUser(final ProfessionalUser professionalUser) throws Exception {
        // save details of the user into the radar tables
        Map<String, Object> professionalUserMap = new HashMap<String, Object>() {
            {
                put(PROFESSIONAL_USER_SURNAME_FIELD_NAME, professionalUser.getSurname());
                put(PROFESSIONAL_USER_FORENAME_FIELD_NAME, professionalUser.getForename());
                put(PROFESSIONAL_USER_TITLE_FIELD_NAME, professionalUser.getTitle());
                put(PROFESSIONAL_USER_GMC_FIELD_NAME, professionalUser.getGmc());
                put(PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME, professionalUser.getRole());
                put(PROFESSIONAL_USER_PHONE_FIELD_NAME, professionalUser.getPhone());
                put(PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME, professionalUser.getCentre().getId());
                put(PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME, professionalUser.getDateRegistered());
                put(PROFESSIONAL_USER_ID_FIELD_NAME, professionalUser.getId());
            }
        };

        if (professionalUser.hasValidId()) {
            String updateSql = buildUpdateQuery(PROFESSIONAL_USER_TABLE_NAME, PROFESSIONAL_USER_ID_FIELD_NAME, professionalUserMap);
            namedParameterJdbcTemplate.update(updateSql, professionalUserMap);
        } else {
            Number id = professionalUsersInsert.executeAndReturnKey(professionalUserMap);
            professionalUser.setId(id.longValue());
        }

        // save main user login into the shared rpv table
        saveUser(professionalUser);
    }

    public void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception {
        // delete the main user login object
        deleteUser(professionalUser);

        // delete the radar specific user information for professional users
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put(PROFESSIONAL_USER_ID_FIELD_NAME, professionalUser.getId());

        namedParameterJdbcTemplate.update("DELETE FROM " + PROFESSIONAL_USER_TABLE_NAME + " WHERE "
                + PROFESSIONAL_USER_ID_FIELD_NAME + " = :" + PROFESSIONAL_USER_ID_FIELD_NAME, professionalUserMap);
    }

    public PatientUser getPatientUser(Long id) {
        try {
            return jdbcTemplate.queryForObject(buildBaseUserSelectFromStatement(PATIENT_USER_TABLE_NAME)
                    + buildUserWhereIdStatement(PATIENT_USER_TABLE_NAME, PATIENT_USER_ID_FIELD_NAME),
                    new Object[]{id, User.ROLE_PATIENT}, new PatientUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not patient user with " + ID_FIELD_NAME + " {}", id);
        }

        return null;
    }

    public PatientUser getPatientUser(String email) {
        try {
            return jdbcTemplate.queryForObject(buildBaseUserSelectFromStatement(PATIENT_USER_TABLE_NAME)
                    + buildUserWhereEmailStatement(PATIENT_USER_TABLE_NAME, PATIENT_USER_ID_FIELD_NAME),
                    new Object[]{email, User.ROLE_PATIENT}, new PatientUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not patient user with " + USER_EMAIL_FIELD_NAME + " {}", email);
        }

        return null;
    }

    public List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage) {
        if (filter == null) {
            filter = new PatientUserFilter();
        }

        List<String> sqlQueries = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        // normal sql query without any filter options
        sqlQueries.add(buildBaseUserSelectFromStatement(PATIENT_USER_TABLE_NAME) +
                " WHERE " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_ROLE_FIELD_NAME
                    + " = '" + User.ROLE_PATIENT + "'" + " " +
                " AND " + PATIENT_USER_TABLE_NAME + "." + PATIENT_USER_ID_FIELD_NAME
                    + " = " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_RADAR_USER_ID_FIELD_NAME +
                " AND " + USER_TABLE_NAME + "." + ID_FIELD_NAME
                    + " = " + USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_USER_ID_FIELD_NAME
        );

        // if there are search queries then build the where
        if (filter.hasSearchCriteria()) {
            sqlQueries.add(" AND " + buildWhereQuery(false, filter.getSearchFields(), true, params));
        }

        // if the filter has a sort then order by it
        if (filter.hasSortFilter()) {
            sqlQueries.add(buildOrderQuery(filter.getSortField(), filter.isReverse()));
        }

        // if a range has been set limit the results
        sqlQueries.add(buildLimitQuery(page, numberPerPage, params));

        // combine the statement and return result
        return jdbcTemplate.query(StringUtils.join(sqlQueries.toArray(), " "), params.toArray(),
                new PatientUserRowMapper());
    }

    public void savePatientUser(final PatientUser patientUser) throws Exception {
        // save details of the user into the radar tables
        Map<String, Object> patientUserMap = new HashMap<String, Object>() {
            {
                put(PATIENT_USER_ID_FIELD_NAME, patientUser.getId());
                put(PATIENT_USER_RADAR_NO_FIELD_NAME, patientUser.getRadarNumber());
                put(PATIENT_USER_DOB_FIELD_NAME, patientUser.getDateOfBirth());
                put(PATIENT_USER_DATE_OF_REGISTRATION_FIELD_NAME, patientUser.getDateRegistered());
            }
        };

        if (patientUser.hasValidId()) {
            String updateSql = buildUpdateQuery(PATIENT_USER_TABLE_NAME, PATIENT_USER_ID_FIELD_NAME, patientUserMap);
            namedParameterJdbcTemplate.update(updateSql, patientUserMap);
        } else {
            Number id = patientUsersInsert.executeAndReturnKey(patientUserMap);
            patientUser.setId(id.longValue());
        }

        // save main user login into the shared rpv table
        saveUser(patientUser);
    }

    public void deletePatientUser(PatientUser patientUser) throws Exception {
        // delete the main user login object
        deleteUser(patientUser);

        // delete the radar specific user information for professional users
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put(PATIENT_USER_ID_FIELD_NAME, patientUser.getId());

        namedParameterJdbcTemplate.update("DELETE FROM " + PATIENT_USER_TABLE_NAME + " WHERE "
                + PATIENT_USER_ID_FIELD_NAME + " = :" + PATIENT_USER_ID_FIELD_NAME, professionalUserMap);
    }

    /**
     * Will map the base user properties from the RPV user table to the user being pulled out for radar
     * @param resultSet ResultSet
     * @param user User object to map to
     * @return User
     * @throws SQLException
     */
    private User mapUserObject(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(resultSet.getLong(ID_FIELD_NAME));
        user.setUsername(resultSet.getString(USER_USERNAME_FIELD_NAME));
        user.setPassword(resultSet.getString(USER_PASSWORD_FIELD_NAME));
        user.setEmail(resultSet.getString(USER_EMAIL_FIELD_NAME));
        return user;
    }

    /**
     * Will save the base user properties to the shared table with RPV
     * @param user User
     */
    private void saveUser(User user) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(ID_FIELD_NAME, user.getId());
        userMap.put(USER_USERNAME_FIELD_NAME, user.getUsername());
        userMap.put(USER_PASSWORD_FIELD_NAME, user.getPassword());
        userMap.put(USER_NAME_FIELD_NAME, user.getName());
        userMap.put(USER_EMAIL_FIELD_NAME, user.getEmail());
        userMap.put(USER_DUMMY_PATIENT_FIELD_NAME, false);
        userMap.put(USER_SCREEN_NAME_FIELD_NAME, user.getUsername());

        if (user.hasValidUserId()) {
            namedParameterJdbcTemplate.update(buildUpdateQuery(USER_TABLE_NAME, ID_FIELD_NAME, userMap), userMap);
        } else {
            Number id = userInsert.executeAndReturnKey(userMap);
            user.setUserId(id.longValue());
        }

        // have to also create a record in the radar mapping table so we know what role it is
        if (user.hasValidId()) {
            saveUserMapping(user);
        }
    }

    /**
     * Remove a user from radar - this will delete the record in the shared RPV table and the radar user mapping
     * @param user User
     */
    private void deleteUser(User user) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(ID_FIELD_NAME, user.getId());

        // delete the main user object
        namedParameterJdbcTemplate.update("DELETE FROM " + USER_TABLE_NAME + " WHERE "
                + ID_FIELD_NAME + " = :" + ID_FIELD_NAME, userMap);

        // delete the user mapping for this user
        deleteUserMapping(user);
    }

    /**
     * Users are saved in a shared table with RPV
     * Radar has different roles to RPV and has its own user mapping table that has the role the user has been assigned
     * @param user User
     */
    private void saveUserMapping(User user) {
        // we only ever want one user mapping per user so just delete any existing and re add
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(USER_MAPPING_USER_ID_FIELD_NAME, user.getUserId());
        userMap.put(USER_MAPPING_ROLE_FIELD_NAME, user.getSecurityRole());
        userMap.put(USER_MAPPING_RADAR_USER_ID_FIELD_NAME, user.getId());

        // delete any mappings already so we dont end up with two
        deleteUserMapping(user);

        // add mapping
        userMappingInsert.execute(userMap);
    }

    /**
     * Delete any user mappings roles for this user
     * @param user User
     */
    private void deleteUserMapping(User user) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(USER_MAPPING_USER_ID_FIELD_NAME, user.getId());

        namedParameterJdbcTemplate.update("DELETE FROM " + USER_MAPPING_TABLE_NAME + " WHERE "
                + USER_MAPPING_USER_ID_FIELD_NAME + " = :" + USER_MAPPING_USER_ID_FIELD_NAME, userMap);
    }

    /**
     * Will build the main select/from statement for the users table
     * @param userTable Radar user table to use in the select
     * @return String
     */
    private String buildBaseUserSelectFromStatement(String userTable) {
        return buildSelectFromStatement(USER_TABLE_NAME, USER_MAPPING_TABLE_NAME, userTable);
    }

    /**
     * Will take a list of tables and build a select from statement in the form of
     * SELECT tableName1.*, tableName2 FROM tableName1, tableName2
     * @param tableNames List of tables to include in the select
     * @return String
     */
    private String buildSelectFromStatement(String... tableNames) {
        String sql = "SELECT ";
        int count = 0;

        // build the field selets, we are just assuming all rows from each table
        for (String table : tableNames) {
            sql += table + ".*";

            if (count < tableNames.length - 1) {
                sql += ", ";
            }

            count++;
        }

        return sql += " FROM " + StringUtils.join(tableNames, ", ");
    }

    /**
     * Builds a general user select statement to pull back by the radar user id not the RPV user id
     * @param userTable User table we are selecting from
     * @param mapIdField ID field of that user table to match to
     * @return String
     */
    private String buildUserWhereIdStatement(String userTable, String mapIdField) {
        return " WHERE " +
                    USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_RADAR_USER_ID_FIELD_NAME + " = ? " +
                " AND " +
                    USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_ROLE_FIELD_NAME + " = ? " +
                " AND " +
                    userTable + "." + mapIdField + " = " + USER_MAPPING_TABLE_NAME + "."
                        + USER_MAPPING_RADAR_USER_ID_FIELD_NAME +
                " AND " +
                    USER_TABLE_NAME + "." + ID_FIELD_NAME + " = " + USER_MAPPING_TABLE_NAME
                + "." + USER_MAPPING_USER_ID_FIELD_NAME;
    }

    /**
     * Builds a general user select statement to pull back by the email address of the user
     * @param userTable User table we are selecting from
     * @param mapIdField ID field of that user table to match to
     * @return String
     */
    private String buildUserWhereEmailStatement(String userTable, String mapIdField) {
        return " WHERE " +
                    USER_TABLE_NAME + "." + USER_EMAIL_FIELD_NAME + " = ? " +
                "AND " +
                    USER_MAPPING_TABLE_NAME + "." + USER_MAPPING_ROLE_FIELD_NAME + " = ? " +
                "AND " +
                    USER_TABLE_NAME + "." + ID_FIELD_NAME + " = " + USER_MAPPING_TABLE_NAME + "."
                        + USER_MAPPING_USER_ID_FIELD_NAME + " " +
                "AND " +
                    userTable + "." + mapIdField + " = " + USER_MAPPING_TABLE_NAME + "."
                        + USER_MAPPING_RADAR_USER_ID_FIELD_NAME;

    }

    private class AdminUserRowMapper implements RowMapper<AdminUser> {
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = (AdminUser) mapUserObject(resultSet, new AdminUser());
            adminUser.setId(resultSet.getLong(ADMIN_USER_ID_FIELD_NAME));
            adminUser.setName(resultSet.getString(ADMIN_USER_NAME_FIELD_NAME));
            return adminUser;
        }
    }

    private class ProfessionalUserRowMapper implements RowMapper<ProfessionalUser> {
        public ProfessionalUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // map the base user properties
            ProfessionalUser professionalUser = (ProfessionalUser) mapUserObject(resultSet, new ProfessionalUser());

            // now map the specific props for this user in radar
            professionalUser.setId(resultSet.getLong(PROFESSIONAL_USER_ID_FIELD_NAME));
            professionalUser.setSurname(resultSet.getString(PROFESSIONAL_USER_SURNAME_FIELD_NAME));
            professionalUser.setForename(resultSet.getString(PROFESSIONAL_USER_FORENAME_FIELD_NAME));
            professionalUser.setTitle(resultSet.getString(PROFESSIONAL_USER_TITLE_FIELD_NAME));
            professionalUser.setGmc(resultSet.getString(PROFESSIONAL_USER_GMC_FIELD_NAME));
            professionalUser.setRole(resultSet.getString(PROFESSIONAL_USER_CENTRE_ROLE_FIELD_NAME));
            professionalUser.setPhone(resultSet.getString(PROFESSIONAL_USER_PHONE_FIELD_NAME));
            professionalUser.setDateRegistered(resultSet.getDate(PROFESSIONAL_USER_DATE_JOINED_FIELD_NAME));

            // Set the centre
            Long centreId = resultSet.getLong(PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME);
            if (centreId != null && centreId > 0) {
                professionalUser.setCentre(utilityDao.getCentre(centreId));
            }

            return professionalUser;
        }
    }

    private class PatientUserRowMapper implements RowMapper<PatientUser> {
        public PatientUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // map the base user properties
            PatientUser patientUser = (PatientUser) mapUserObject(resultSet, new PatientUser());

            // map radar specific ones
            patientUser.setId(resultSet.getLong(PATIENT_USER_ID_FIELD_NAME));
            patientUser.setDateOfBirth(resultSet.getDate(PATIENT_USER_DOB_FIELD_NAME));
            patientUser.setDateRegistered(resultSet.getDate(PATIENT_USER_DATE_OF_REGISTRATION_FIELD_NAME));
            patientUser.setRadarNumber(resultSet.getLong(PATIENT_USER_RADAR_NO_FIELD_NAME));

            // legacy - patients never had emails but rpv table does so just patient username
            if (patientUser.getEmail() == null || patientUser.getEmail().length() == 0) {
                patientUser.setEmail(patientUser.getUsername());
            }

            return patientUser;
        }
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
