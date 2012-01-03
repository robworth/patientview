package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    public PatientUser getPatientUser(String email) {
        return null;  // Todo: Implement
    }

    public ProfessionalUser getProfessionalUser(String email) {
        try {
            // Return a professional user object queried for using given email
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_users WHERE uEmail = ?", new Object[]{email},
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
            professionalUser.setPassword(resultSet.getString("uPass"));
            professionalUser.setUsername(resultSet.getString("uUserName"));

            // Todo: Centre

            return professionalUser;
        }
    }
}
