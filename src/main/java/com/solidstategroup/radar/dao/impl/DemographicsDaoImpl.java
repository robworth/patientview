package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.util.TripleDes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemographicsDaoImpl extends BaseDaoImpl implements DemographicsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemographicsDaoImpl.class);

    private UtilityDao utilityDao;

    public Demographics getDemographicsByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate
                    .queryForObject("SELECT * FROM tbl_Demographics WHERE RADAR_NO = ?", new Object[]{radarNumber},
                            new DemographicsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No demographic record found for radar number {}", radarNumber);
            return null;
        }
    }

    private class DemographicsRowMapper implements RowMapper<Demographics> {
        public Demographics mapRow(ResultSet resultSet, int i) throws SQLException {
            Demographics demographics = new Demographics();
            demographics.setId(resultSet.getLong("RADAR_NO"));
            demographics.setDateRegistered(resultSet.getDate("DATE_REG"));

            // These need to be decrypted from the database
            demographics.setNhsNumber(getDecryptedString(resultSet, "NHS_NO"));
            demographics.setHospitalNumber(getDecryptedString(resultSet, "HOSP_NO"));
            demographics.setSurname(getDecryptedString(resultSet, "SNAME"));
            demographics.setSurnameAlias(getDecryptedString(resultSet, "SNAME_ALIAS"));
            demographics.setForename(getDecryptedString(resultSet, "FNAME"));

            // Date needs to be decrypted to string, then parsed
            String dateOfBirthString = getDecryptedString(resultSet, "DOB");
            if (StringUtils.isNotBlank(dateOfBirthString)) {
                try {
                    Date dateOfBirth = new SimpleDateFormat("dd.MM.yyyy").parse(dateOfBirthString);
                    demographics.setDateOfBirth(dateOfBirth);
                } catch (ParseException e) {
                    LOGGER.error("Could not parse date of birth {}", dateOfBirthString);
                }
            }

            // Addresses, all encrypted too
            demographics.setAddress1(getDecryptedString(resultSet, "ADD1"));
            demographics.setAddress2(getDecryptedString(resultSet, "ADD2"));
            demographics.setAddress3(getDecryptedString(resultSet, "ADD3"));
            demographics.setAddress4(getDecryptedString(resultSet, "ADD4"));
            demographics.setPostcode(getDecryptedString(resultSet, "POSTCODE"));
            demographics.setPreviousPostcode(getDecryptedString(resultSet, "POSTCODE_OLD"));

//            demographics.setEthnicity();
//            demographics.setSex();

            demographics.setConsent(resultSet.getBoolean("CONSENT"));

            // Set the centre if we have an ID
            Long renalUnitId = resultSet.getLong("RENAL_UNIT");
            if (renalUnitId != null) {
                demographics.setRenalUnit(utilityDao.getCentre(renalUnitId));
            }

            // Todo: Some more fields...
            return demographics;
        }
    }

    private String getDecryptedString(ResultSet resultSet, String column) {
        try {
            // Catch the exception and log rather than throwing from entire row mapper
            return TripleDes.decrypt(resultSet.getBytes(column));
        } catch (Exception e) {
            LOGGER.error("Could not decrypt column data {} - {}", column, e.getMessage());
            return null;
        }
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
