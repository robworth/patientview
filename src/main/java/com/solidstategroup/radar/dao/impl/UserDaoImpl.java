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
    private static final String USER_TABLE_NAME = "user";
    private static final String USER_MAPPING_TABLE_NAME = "rdr_user_mapping";
    private static final String PROFESSIONAL_USER_TABLE_NAME = "rdr_professional_user";

    // user mapping table fields
    private static final String ID_FIELD_NAME = "id";
    private static final String USER_ID_FIELD_NAME = "userId";
    private static final String ROLE_FIELD_NAME = "role";

    // user table fields
    private static final String CREATED_FIELD_NAME = "created";
    private static final String USERNAME_FIELD_NAME = "username";
    private static final String PASSWORD_FIELD_NAME = "password";
    private static final String EMAIL_FIELD_NAME = "email";
    private static final String TITLE_FIELD_NAME = "title";
    private static final String FORENAME_FIELD_NAME = "forename";
    private static final String SURNAME_FIELD_NAME = "surname";
    private static final String TELEPHONE_FIELD_NAME = "telephone";
    private static final String SCREEN_NAME_FIELD_NAME = "screenName";

    // professional user table fields
    private static final String GMC_FIELD_NAME = "gmc";
    private static final String CENTRE_ID_FIELD_NAME = "centreId";
    private static final String CENTRE_ROLE_FIELD_NAME = "centreRole";

    private UtilityDao utilityDao;

    private SimpleJdbcInsert userInsert;
    private SimpleJdbcInsert userMappingInsert;
    private SimpleJdbcInsert professionalUserInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);

        userInsert = new SimpleJdbcInsert(dataSource).withTableName(USER_TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(CREATED_FIELD_NAME, USERNAME_FIELD_NAME, PASSWORD_FIELD_NAME, EMAIL_FIELD_NAME,
                        TITLE_FIELD_NAME, FORENAME_FIELD_NAME, SURNAME_FIELD_NAME, TELEPHONE_FIELD_NAME,
                        SCREEN_NAME_FIELD_NAME);

        userMappingInsert = new SimpleJdbcInsert(dataSource).withTableName(USER_MAPPING_TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(USER_ID_FIELD_NAME, ROLE_FIELD_NAME);

        professionalUserInsert = new SimpleJdbcInsert(dataSource).withTableName(PROFESSIONAL_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(USER_ID_FIELD_NAME, GMC_FIELD_NAME, CENTRE_ID_FIELD_NAME, CENTRE_ROLE_FIELD_NAME);
    }

    public AdminUser getAdminUser(String email) {
        try {
            return jdbcTemplate.queryForObject("" +
                    "SELECT " +
                    "   user.*, userMapping.* " +
                    "FROM " +
                        USER_TABLE_NAME + " user, " +
                        USER_MAPPING_TABLE_NAME + " userMapping " +
                    "WHERE " +
                    "   user." + EMAIL_FIELD_NAME + " = ? " +
                    "AND" +
                    "   usermapping." + ROLE_FIELD_NAME + " = ? " +
                    "AND" +
                    "   user." + ID_FIELD_NAME + " = userMapping." + USER_ID_FIELD_NAME,
                    new Object[]{email, User.ROLE_ADMIN}, new AdminUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not admin user " + USER_TABLE_NAME + " with " + EMAIL_FIELD_NAME + " {}", email);
        }

        return null;
    }

    public void saveAdminUser(final AdminUser adminUser) {
        // save main user record - there is nothing special about the admin user so its just a normal user record
        // with a radar mapping to the admin role
        saveUser(adminUser);
    }

    public ProfessionalUser getProfessionalUser(Long id) {
        try {
            return jdbcTemplate.queryForObject("" +
                    "SELECT " +
                    "   user.*, " +
                    "   userMapping.*, " +
                    "   professionalUser.* " +
                    "FROM " +
                        USER_TABLE_NAME + " user, " +
                        USER_MAPPING_TABLE_NAME + " userMapping, " +
                        PROFESSIONAL_USER_TABLE_NAME + " professionalUser " +
                    "WHERE " +
                    "   usermapping." + ROLE_FIELD_NAME + " = ? " +
                    "AND" +
                    "   usermapping." + USER_ID_FIELD_NAME + " = ? "+
                    "AND " +
                    "   user." + ID_FIELD_NAME + " = professionalUser." + USER_ID_FIELD_NAME,
                    new Object[]{User.ROLE_PROFESSIONAL, id}, new ProfessionalUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not professional user with " + ID_FIELD_NAME + " {}", id);
        }

        return null;
    }

    public ProfessionalUser getProfessionalUser(String email) {
        try {
            return jdbcTemplate.queryForObject("" +
                    "SELECT " +
                    "   user.*, " +
                    "   userMapping.*, " +
                    "   professionalUser.* " +
                    "FROM " +
                        USER_TABLE_NAME + " user, " +
                        USER_MAPPING_TABLE_NAME + " userMapping, " +
                        PROFESSIONAL_USER_TABLE_NAME + " professionalUser " +
                    "WHERE " +
                    "   user." + EMAIL_FIELD_NAME + " = ? " +
                    "AND" +
                    "   usermapping." + ROLE_FIELD_NAME + " = ? " +
                    "AND" +
                    "   user." + ID_FIELD_NAME + " = userMapping." + USER_ID_FIELD_NAME +
                    " AND " +
                    "   user." + ID_FIELD_NAME + " = professionalUser." + USER_ID_FIELD_NAME,
                    new Object[]{email, User.ROLE_PROFESSIONAL}, new ProfessionalUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not professional user with " + EMAIL_FIELD_NAME + " {}", email);
        }

        return null;
    }

    public void saveProfessionalUser(final ProfessionalUser professionalUser) throws Exception {
        // save main user details
        saveUser(professionalUser);

        // save the specific radar professional user details in seperate table
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put(USER_ID_FIELD_NAME, professionalUser.getId());
        professionalUserMap.put(GMC_FIELD_NAME, professionalUser.getGmc());
        professionalUserMap.put(CENTRE_ID_FIELD_NAME, professionalUser.getCentre().getId());
        professionalUserMap.put(CENTRE_ROLE_FIELD_NAME, professionalUser.getRole());

        // make sure we only ever have 1 record for this user
        namedParameterJdbcTemplate.update("DELETE FROM " + PROFESSIONAL_USER_TABLE_NAME + " WHERE "
                + USER_ID_FIELD_NAME + " = :" + USER_ID_FIELD_NAME, professionalUserMap);

        // then save the data
        professionalUserInsert.execute(professionalUserMap);
    }

    public void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception {
        // delete the main user object
        deleteUser(professionalUser);

        // delete the radar specific user information for professional users
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put(USER_ID_FIELD_NAME, professionalUser.getId());

        namedParameterJdbcTemplate.update("DELETE FROM " + PROFESSIONAL_USER_TABLE_NAME + " WHERE "
                + USER_ID_FIELD_NAME + " = :" + USER_ID_FIELD_NAME, professionalUserMap);

    }

    public List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage) {
        if (filter == null) {
            filter = new ProfessionalUserFilter();
        }

        List<String> sqlQueries = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        // normal sql query without any filter options
        sqlQueries.add("" +
                "SELECT " +
                "   user.*, " +
                "   userMapping.*, " +
                "   professionalUser.* " +
                "FROM " +
                    USER_TABLE_NAME + " user, " +
                    USER_MAPPING_TABLE_NAME + " userMapping, " +
                    PROFESSIONAL_USER_TABLE_NAME + " professionalUser " +
                "WHERE " +
                "   usermapping." + ROLE_FIELD_NAME + " = ? " +
                " AND " +
                "   user." + ID_FIELD_NAME + " = professionalUser." + USER_ID_FIELD_NAME
        );

        // if there are search queries then build the where
        if (filter.hasSearchCriteria()) {
            sqlQueries.add(buildWhereQuery(filter.getSearchFields(), true, params));
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

    public PatientUser getPatientUser(Long id) {
        // todo
        return null;
    }

    public PatientUser getPatientUser(String email) {
        // todo
        return null;
    }

    public List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage) {
        // todo
        return null;
    }

    public void savePatientUser(final PatientUser patientUser) throws Exception {
        // todo
    }

    public void deletePatientUser(PatientUser patientUser) throws Exception {
        // todo
    }

    private class AdminUserRowMapper implements RowMapper<AdminUser> {
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            return (AdminUser) mapUserObject(resultSet, new AdminUser());
        }
    }

    private class ProfessionalUserRowMapper implements RowMapper<ProfessionalUser> {
        public ProfessionalUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // map the base user properties
            ProfessionalUser professionalUser = (ProfessionalUser) mapUserObject(resultSet, new ProfessionalUser());

            // now map the specific props for this user in radar
            professionalUser.setGmc(resultSet.getString(GMC_FIELD_NAME));
            professionalUser.setRole(resultSet.getString(CENTRE_ROLE_FIELD_NAME));

            // Set the centre
            Long centreId = resultSet.getLong(CENTRE_ID_FIELD_NAME);
            if (centreId != null && centreId > 0) {
                professionalUser.setCentre(utilityDao.getCentre(centreId));
            }

            return professionalUser;
        }
    }

    private class PatientUserRowMapper implements RowMapper<PatientUser> {
        public PatientUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // todo
            return new PatientUser();
        }
    }

    /**
     * Will map the base user properties from the RPV user table to the user being pulled out for radar
     * @param resultSet ResultSet
     * @param user User object to map to
     * @return User
     * @throws SQLException
     */
    private User mapUserObject(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong(ID_FIELD_NAME));
        user.setCreated(resultSet.getDate(CREATED_FIELD_NAME));
        user.setUsername(resultSet.getString(USERNAME_FIELD_NAME));
        user.setPassword(resultSet.getString(PASSWORD_FIELD_NAME));
        user.setEmail(resultSet.getString(EMAIL_FIELD_NAME));
        user.setTitle(resultSet.getString(TITLE_FIELD_NAME));
        user.setForename(resultSet.getString(FORENAME_FIELD_NAME));
        user.setSurname(resultSet.getString(SURNAME_FIELD_NAME));
        user.setTelephone(resultSet.getString(TELEPHONE_FIELD_NAME));
        user.setScreenName(resultSet.getString(SCREEN_NAME_FIELD_NAME));
        return user;
    }

    /**
     * Will save the base user properties to the shared table with RPV
     * @param user User
     */
    private void saveUser(User user) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(ID_FIELD_NAME, user.getId());
        userMap.put(CREATED_FIELD_NAME, user.getCreated());
        userMap.put(USERNAME_FIELD_NAME, user.getUsername());
        userMap.put(PASSWORD_FIELD_NAME, user.getPassword());
        userMap.put(EMAIL_FIELD_NAME, user.getEmail());
        userMap.put(TITLE_FIELD_NAME, user.getTitle());
        userMap.put(FORENAME_FIELD_NAME, user.getForename());
        userMap.put(SURNAME_FIELD_NAME, user.getSurname());
        userMap.put(TELEPHONE_FIELD_NAME, user.getTelephone());
        userMap.put(SCREEN_NAME_FIELD_NAME, user.getScreenName());

        if (user.hasValidId()) {
            namedParameterJdbcTemplate.update(buildUpdateQuery(USER_TABLE_NAME, ID_FIELD_NAME, userMap), userMap);
        } else {
            Number id = userInsert.executeAndReturnKey(userMap);
            user.setId(id.longValue());
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
        userMap.put(USER_ID_FIELD_NAME, user.getId());
        userMap.put(ROLE_FIELD_NAME, user.getSecurityRole());

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
        userMap.put(USER_ID_FIELD_NAME, user.getId());

        namedParameterJdbcTemplate.update("DELETE FROM " + USER_MAPPING_TABLE_NAME + " WHERE "
                + USER_ID_FIELD_NAME + " = :" + USER_ID_FIELD_NAME, userMap);
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
