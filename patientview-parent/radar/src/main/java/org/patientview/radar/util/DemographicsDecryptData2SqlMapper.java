package org.patientview.radar.util;

import org.patientview.radar.model.Demographics;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * {@link #run(javax.servlet.ServletContext)} gets the encrypted field data from demographics table, decrypts it,
 *      and creates an .sql file with the update statements for setting the decrypted values.
 *
 * Please make sure the folders in the {@link #FILE} exists.
 */
public class DemographicsDecryptData2SqlMapper {

    protected JdbcTemplate jdbcTemplate;

    private static final String FILE = "/radarunencrypteddemograpicstableexport/output/decrypted_demographics_data.sql";

    private static final String DATE_FORMAT = "dd.MM.y";
    private static final String DATE_FORMAT_2 = "dd-MM-y";
    private static final String DATE_FORMAT_3 = "dd/MM/y";

    private static final Logger LOGGER = LoggerFactory.getLogger(DemographicsDecryptData2SqlMapper.class);

    public void run(ServletContext servletContext) throws Exception {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(
                servletContext);

        jdbcTemplate = new JdbcTemplate((DataSource) webApplicationContext.getBean("dataSource"));

        List<Demographics> demographicsList = jdbcTemplate.query("SELECT * FROM TBL_DEMOGRAPHICS",
                new EncryptedDemographicsRowMapper());

        StringBuilder outputText = new StringBuilder();
        for (Demographics demographics : demographicsList) {
            String updateStatement = "UPDATE TBL_DEMOGRAPHICS SET ";

            if (demographics.getNhsNumber() != null) {
                updateStatement += " NHS_NO = '" + demographics.getNhsNumber() + "', ";
            }

            if (demographics.getHospitalNumber() != null) {
                updateStatement += " HOSP_NO = \"" + demographics.getHospitalNumber() + "\", ";
            }

            if (demographics.getSurname() != null) {
                updateStatement += " SNAME = \"" + demographics.getSurname() + "\", ";
            }

            if (demographics.getForename() != null) {
                updateStatement += " FNAME = \"" + demographics.getForename() + "\", ";
            }

            if (demographics.getSurnameAlias() != null) {
                updateStatement += " SNAME_ALIAS = \"" + demographics.getSurnameAlias() + "\", ";
            }

            if (demographics.getDateOfBirth() != null) {
                // just guess what a sane date format is
                updateStatement += " DOB = \""
                        + new SimpleDateFormat(DATE_FORMAT_2).format(demographics.getDateOfBirth()) + "\", ";
            }

            if (demographics.getAddress1() != null) {
                updateStatement += " ADD1 = \"" + demographics.getAddress1() + "\", ";
            }

            if (demographics.getAddress2() != null) {
                updateStatement += " ADD2 = \"" + demographics.getAddress2() + "\", ";
            }

            if (demographics.getAddress3() != null) {
                updateStatement += " ADD3 = \"" + demographics.getAddress3() + "\", ";
            }

            if (demographics.getAddress4() != null) {
                updateStatement += " ADD4 = \"" + demographics.getAddress4() + "\", ";
            }

            if (demographics.getPostcode() != null) {
                updateStatement += " POSTCODE = \"" + demographics.getPostcode() + "\", ";
            }

            if (demographics.getPreviousPostcode() != null) {
                updateStatement += " POSTCODE_OLD = \"" + demographics.getPreviousPostcode() + "\", ";
            }

            updateStatement += " RADAR_NO = " + demographics.getId();
            updateStatement += " WHERE RADAR_NO = " + demographics.getId();
            updateStatement += " ;";

            outputText.append(updateStatement);
        }

        // output all sql stuff to file
        FileWriter fileWriter = new FileWriter(FILE);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(outputText.toString());
        //Close the output stream
        bufferedWriter.close();
    }

    private class EncryptedDemographicsRowMapper implements RowMapper<Demographics> {
        public Demographics mapRow(ResultSet resultSet, int i) throws SQLException {
            Demographics demographics = new Demographics();
            demographics.setId(resultSet.getLong("RADAR_NO"));

            try {
                demographics.setNhsNumber(getDecryptedString(demographics.getId() + "", "NHS_NO",
                        resultSet.getBytes("NHS_NO")));
                demographics.setHospitalNumber(getDecryptedString(demographics.getId() + "", "HOSP_NO",
                        resultSet.getBytes("HOSP_NO")));
                demographics.setSurname(getDecryptedString(demographics.getId() + "", "SNAME",
                        resultSet.getBytes("SNAME")));
                demographics.setSurnameAlias(getDecryptedString(demographics.getId() + "", "SNAME_ALIAS",
                        resultSet.getBytes("SNAME_ALIAS")));
                demographics.setForename(getDecryptedString(demographics.getId() + "", "FNAME",
                        resultSet.getBytes("FNAME")));

                // Date needs to be decrypted to string, then parsed
                String dateOfBirthString = getDecryptedString(demographics.getId() + "", "DOB",
                        resultSet.getBytes("DOB"));

                if (StringUtils.isNotBlank(dateOfBirthString)) {
                    Date dateOfBirth = getDate(dateOfBirthString, DATE_FORMAT);

                    if (dateOfBirth == null) {
                        dateOfBirth = getDate(dateOfBirthString, DATE_FORMAT_2);
                    }

                    if (dateOfBirth == null) {
                        dateOfBirth = getDate(dateOfBirthString, DATE_FORMAT_3);
                    }

                    // If after trying those formats we don't have anything then log as error
                    if (dateOfBirth != null) {
                        demographics.setDateOfBirth(dateOfBirth);
                    } else {
                        LOGGER.error("Could not parse date of birth from any format for dob {}",
                                dateOfBirthString);
                    }
                }

                // Addresses, all encrypted too
                demographics.setAddress1(getDecryptedString(demographics.getId() + "", "ADD1",
                        resultSet.getBytes("ADD1")));
                demographics.setAddress2(getDecryptedString(demographics.getId() + "", "ADD2",
                        resultSet.getBytes("ADD2")));
                demographics.setAddress3(getDecryptedString(demographics.getId() + "", "ADD3",
                        resultSet.getBytes("ADD3")));
                demographics.setAddress4(getDecryptedString(demographics.getId() + "", "ADD4",
                        resultSet.getBytes("ADD4")));
                demographics.setPostcode(getDecryptedString(demographics.getId() + "", "POSTCODE",
                        resultSet.getBytes("POSTCODE")));
                demographics.setPreviousPostcode(getDecryptedString(demographics.getId() + "", "POSTCODE_OLD",
                        resultSet.getBytes("POSTCODE_OLD")));

            } catch (Exception e) {
                LOGGER.error("Could not decrypt demographics information for demographics {}", demographics.getId());
                e.printStackTrace();
            }

            return demographics;
        }
    }

    private String getDecryptedString(String radarNo, String fieldName, byte[] fieldData) throws Exception {
        if (fieldData != null && fieldData.length > 0) {
            try {
                byte[] copy = Arrays.copyOf(fieldData, fieldData.length);
                return TripleDes.decrypt(copy);
            } catch (Exception e) {
                LOGGER.error("Could not decrypt demographics information for radarNo {}, field {}, field data {}, " +
                        "message {}",
                        new Object[] {radarNo, fieldName, fieldData, e.getMessage()});
            }
        }

        return null;
    }

    private Date getDate(String dobStr, String dateFormat) {
        // It seems that the encrypted strings in the DB have different date formats, nice.
        try {
            return new SimpleDateFormat(dateFormat).parse(dobStr);
        } catch (Exception e) {
            // cya
        }
        return null;
    }
}
