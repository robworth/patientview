package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Country;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Relative;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilityDaoImpl extends BaseDaoImpl implements UtilityDao {

    public List<Centre> getCentres() {
        return jdbcTemplate.query("SELECT * FROM tbl_Centres", new CentreRowMapper());
    }

    public Country getCountry(long id) {
        return jdbcTemplate
                .queryForObject("SELECT * FROM tbl_Country WHERE cID = ?", new Long[]{id}, new CountryRowMapper());
    }

    public List<Country> getCountries() {
        return jdbcTemplate.query("SELECT * FROM tbl_Country", new CountryRowMapper());
    }

    public List<Ethnicity> getEthnicities() {
        return jdbcTemplate.query("SELECT * FROM tbl_Ethnicity", new EthnicityRowMapper());
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
}
