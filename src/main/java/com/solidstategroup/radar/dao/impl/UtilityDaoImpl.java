package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Consultant;
import com.solidstategroup.radar.model.Country;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Relative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilityDaoImpl extends BaseDaoImpl implements UtilityDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityDaoImpl.class);

    public Centre getCentre(long id) {
        return jdbcTemplate
                .queryForObject("SELECT * FROM tbl_Centres WHERE cID = ?", new Object[]{id}, new CentreRowMapper());
    }

    public List<Centre> getCentres() {
        return jdbcTemplate.query("SELECT * FROM tbl_Centres", new CentreRowMapper());
    }

    public Consultant getConsultant(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_Consultants WHERE cID = ?", new Object[]{id},
                new ConsultantRowMapper());
    }

    public List<Consultant> getConsultants() {
        return jdbcTemplate.query("SELECT * FROM tbl_Consultants", new ConsultantRowMapper());
    }

    public Country getCountry(long id) {
        return jdbcTemplate
                .queryForObject("SELECT * FROM tbl_Country WHERE cID = ?", new Long[]{id}, new CountryRowMapper());
    }

    public List<Country> getCountries() {
        return jdbcTemplate.query("SELECT * FROM tbl_Country", new CountryRowMapper());
    }

    public Ethnicity getEthnicityByCode(String ethnicityCode) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Ethnicity WHERE eCode = ?",
                    new Object[]{ethnicityCode}, new EthnicityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get ethnicity with code {}", ethnicityCode);
            return null;
        }
    }

    public List<Ethnicity> getEthnicities() {
        return jdbcTemplate.query("SELECT * FROM tbl_Ethnicity", new EthnicityRowMapper());
    }

    public Relative getRelative(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Relative WHERE rID = ?", new Object[]{id},
                    new RelativeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get relative with ID {}", id);
            return null;
        }
    }

    public List<Relative> getRelatives() {
        return jdbcTemplate.query("SELECT * FROM tbl_Relative", new RelativeRowMapper());
    }

    private class CentreRowMapper implements RowMapper<Centre> {
        public Centre mapRow(ResultSet resultSet, int i) throws SQLException {
            // Create a centre and set the fields from the resultset
            Centre centre = new Centre();
            centre.setId(resultSet.getLong("cID"));
            centre.setName(resultSet.getString("cName"));
            centre.setAbbreviation(resultSet.getString("cAbbrev"));
            // Set country from our DAO
            centre.setCountry(getCountry(resultSet.getLong("cCountry")));
            return centre;
        }
    }

    private class CountryRowMapper implements RowMapper<Country> {
        public Country mapRow(ResultSet resultSet, int i) throws SQLException {
            // Create a country and set the fields from our resultset
            Country country = new Country();
            country.setId(resultSet.getLong("cID"));
            country.setName(resultSet.getString("cName"));
            return country;
        }
    }

    private class EthnicityRowMapper implements RowMapper<Ethnicity> {
        public Ethnicity mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct ethnicity object and set fields
            Ethnicity ethnicity = new Ethnicity();
            ethnicity.setId(resultSet.getLong("eID"));
            ethnicity.setName(resultSet.getString("eName"));
            ethnicity.setCode(resultSet.getString("eCode"));
            return ethnicity;
        }
    }

    private class RelativeRowMapper implements RowMapper<Relative> {
        public Relative mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a relative object and set all the fields
            Relative relative = new Relative();
            relative.setId(resultSet.getLong("rID"));
            relative.setName(resultSet.getString("RELATIVE"));
            return relative;
        }
    }

    private class ConsultantRowMapper implements RowMapper<Consultant> {
        public Consultant mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a consultant object and set all the fields
            Consultant consultant = new Consultant();
            consultant.setId(resultSet.getLong("cID"));
            consultant.setSurname(resultSet.getString("cSNAME"));
            consultant.setForename(resultSet.getString("cFNAME"));

            // Centre could be null, in which case we get a 0 returned by getLong
            long centreId = resultSet.getLong("cCentre");
            if (centreId > 0) {
                consultant.setCentre(getCentre(centreId));
            }

            return consultant;
        }
    }
}
