package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.util.TripleDes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private UtilityDao utilityDao;

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

            // Have to decrypt the username
            try {
                professionalUser.setUsername(TripleDes.decrypt(resultSet.getBytes("uUserName")));
            } catch (Exception e) {
                LOGGER.error("Could not set username for user {}, decryption failed", professionalUser.getEmail(), e);
            }

            // Set the centre
            Long centreId = resultSet.getLong("uCentre");
            if (centreId != null) {
                professionalUser.setCentre(utilityDao.getCentre(centreId));
            }

            return professionalUser;
        }
    }

    private class PatientUserRowMapper implements RowMapper<PatientUser> {
        public PatientUser mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a patient user and set all the fields, pretty trivial
            PatientUser patientUser = new PatientUser();
            patientUser.setUsername(resultSet.getString("pUserName"));
            patientUser.setPasswordHash(resultSet.getBytes("pPassWord"));
            patientUser.setDateOfBirth(resultSet.getDate("pDOB"));
            patientUser.setDateRegistered(resultSet.getDate("pDateReg"));
            return patientUser;
        }
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
