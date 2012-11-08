package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.filter.PatientUserFilter;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
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

    private UtilityDao utilityDao;
    private SimpleJdbcInsert patientUsersInsert;
    private SimpleJdbcInsert professionalUsersInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        patientUsersInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Patient_Users")
                .usingGeneratedKeyColumns("pID")
                .usingColumns("RADAR_NO", "pUserName", "pPassWord", "pDOB", "pDateReg"
                );

        professionalUsersInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Users")
                .usingGeneratedKeyColumns("uID")
                .usingColumns("uSurname", "uForename", "uTitle", "uGMC", "uRole",
                        "uEmail", "uPhone", "uCentre", "uDateJoin", "uUserName", "uPass");
    }

    public AdminUser getAdminUser(String email) {
        try {
            // Return a patient user object queried for using given email
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_adminusers WHERE uEmail = ?",
                    new Object[]{email}, new AdminUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // Add debug logging
            LOGGER.debug("Could not find row in table tbl_adminusers with uEmail {}", email);
        }
        return null;
    }

    public PatientUser getPatientUser(Long id) {
        try {
            // Return a patient user object queried for using given email
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Patient_Users WHERE pID = ?",
                    new Object[]{id}, new PatientUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // Add debug logging
            LOGGER.debug("Could not find row in table tbl_Patient_Users with pID {}", id);
        }
        return null;
    }

    public PatientUser getPatientUser(String email) {
        try {
            // Return a patient user object queried for using given email
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Patient_Users WHERE pUserName = ?",
                    new Object[]{email}, new PatientUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // Add debug logging
            LOGGER.debug("Could not find row in table tbl_Patient_Users with pUserName {}", email);
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
        sqlQueries.add("SELECT " +
                "   tbl_Patient_Users.*, " +
                "   tbl_Demographics.sName, " +
                "   tbl_Demographics.fName " +
                "FROM " +
                "   tbl_Patient_Users " +
                "INNER JOIN " +
                "   tbl_Demographics " +
                "ON " +
                "   tbl_Patient_Users.RADAR_NO = tbl_Demographics.RADAR_NO");

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
                new PatientUserRowMapper());
    }

    public void savePatientUser(final PatientUser patientUser) throws Exception {
        Map<String, Object> patientUserMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", patientUser.getRadarNumber());
                put("pUserName", patientUser.getUsername());
                put("pPassWord", patientUser.getPasswordHash());
                put("pDOB", patientUser.getDateOfBirth());
                put("pDateReg", patientUser.getDateRegistered());
                put("pID", patientUser.getId());
            }
        };
        
        if (patientUser.hasValidId()) {
            String updateSql = buildUpdateQuery("tbl_Patient_Users", "pID", patientUserMap);
            namedParameterJdbcTemplate.update(updateSql, patientUserMap);
        } else {
            Number id = patientUsersInsert.executeAndReturnKey(patientUserMap);
            patientUser.setId(id.longValue());
        }
    }

    public void deletePatientUser(PatientUser patientUser) throws Exception {
        Map<String, Object> patientUserMap = new HashMap<String, Object>();
        patientUserMap.put("pID", patientUser.getId());
        patientUserMap.put("RADAR_NO", patientUser.getRadarNumber());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_Demographics WHERE RADAR_NO = :RADAR_NO;", patientUserMap);
        namedParameterJdbcTemplate.update("DELETE FROM tbl_Patient_Users WHERE pID = :pID;", patientUserMap);
    }

    public ProfessionalUser getProfessionalUser(Long id) {
        if (id != null) {
            try {
                return jdbcTemplate.queryForObject("SELECT * FROM tbl_Users WHERE uID = ?", new Object[]{id},
                        new ProfessionalUserRowMapper());
            } catch (EmptyResultDataAccessException e) {
                // Add debug logging
                LOGGER.debug("Could not find row in table tbl_users with uID {}", id);
            }
        }

        return null;
    }

    public ProfessionalUser getProfessionalUser(String email) {
        try {
            // Return a professional user object queried for using given email
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Users WHERE uEmail = ?", new Object[]{email},
                    new ProfessionalUserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // Add debug logging
            LOGGER.debug("Could not find row in table tbl_users with uEmail {}", email);
        }
        return null;
    }

    public void saveProfessionalUser(final ProfessionalUser professionalUser) throws Exception {
        Map<String, Object> professionalUserMap = new HashMap<String, Object>() {
            {
                put("uSurname", professionalUser.getSurname());
                put("uForename", professionalUser.getForename());
                put("uTitle", professionalUser.getTitle());
                put("uGMC", professionalUser.getGmc());
                put("uRole", professionalUser.getRole());
                put("uEmail", professionalUser.getEmail());
                put("uPhone", professionalUser.getPhone());
                put("uCentre", professionalUser.getCentre().getId());
                put("uDateJoin", professionalUser.getDateRegistered());
                put("uUserName", professionalUser.getUsernameHash());
                put("uPass", professionalUser.getPasswordHash());
                put("uID", professionalUser.getId());
            }
        };

        if (professionalUser.hasValidId()) {
            String updateSql = buildUpdateQuery("tbl_Users", "uID", professionalUserMap);
            namedParameterJdbcTemplate.update(updateSql, professionalUserMap);
        } else {
            Number id = professionalUsersInsert.executeAndReturnKey(professionalUserMap);
            professionalUser.setId(id.longValue());
        }
    }

    public void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception {
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put("uID", professionalUser.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_Users WHERE uID = :uID;", professionalUserMap);        
    }

    public List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage) {
        if (filter == null) {
            filter = new ProfessionalUserFilter();
        }

        List<String> sqlQueries = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        // normal sql query without any filter options
        sqlQueries.add("SELECT " +
                "   tbl_Users.*, " +
                "   tbl_Centres.cName AS cName " +
                "FROM " +
                "   tbl_Users " +
                "INNER JOIN " +
                "   tbl_Centres " +
                "ON " +
                "   tbl_Users.uCentre = tbl_Centres.cID");

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

    private class ProfessionalUserRowMapper implements RowMapper<ProfessionalUser> {
        public ProfessionalUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a professional user object and set all the fields
            ProfessionalUser professionalUser = new ProfessionalUser();
            professionalUser.setId(resultSet.getLong("uID"));
            professionalUser.setSurname(resultSet.getString("uSurname"));
            professionalUser.setForename(resultSet.getString("uForename"));
            professionalUser.setTitle(resultSet.getString("uTitle"));
            professionalUser.setGmc(resultSet.getString("UGMC"));
            professionalUser.setRole(resultSet.getString("uRole"));
            professionalUser.setEmail(resultSet.getString("uEmail"));
            professionalUser.setPhone(resultSet.getString("uPhone"));
            professionalUser.setDateRegistered(resultSet.getDate("uDateJoin"));
            professionalUser.setPasswordHash(resultSet.getBytes("uPass"));
            professionalUser.setUsernameHash(resultSet.getBytes("uUserName"));

            // Set the centre
            Long centreId = resultSet.getLong("uCentre");
            if (centreId != null) {
                professionalUser.setCentre(utilityDao.getCentre(centreId));
            }

            return professionalUser;
        }
    }
    
    private class AdminUserRowMapper implements RowMapper<AdminUser> {
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();
            adminUser.setName(resultSet.getString("uName"));
            adminUser.setEmail(resultSet.getString("uEmail"));
            adminUser.setPasswordHash(resultSet.getBytes("uPass"));
            adminUser.setUsername(resultSet.getString("uUserName"));
            return adminUser;
        }
    }

    private class PatientUserRowMapper implements RowMapper<PatientUser> {
        public PatientUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a patient user and set all the fields, pretty trivial
            PatientUser patientUser = new PatientUser();
            patientUser.setId(resultSet.getLong("pID"));
            patientUser.setUsername(resultSet.getString("pUserName"));
            patientUser.setPasswordHash(resultSet.getBytes("pPassWord"));
            patientUser.setDateOfBirth(resultSet.getDate("pDOB"));
            patientUser.setDateRegistered(resultSet.getDate("pDateReg"));
            patientUser.setRadarNumber(resultSet.getLong("radar_no"));
            return patientUser;
        }
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
