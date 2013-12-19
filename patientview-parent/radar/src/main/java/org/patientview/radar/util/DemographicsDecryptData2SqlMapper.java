/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.util;

import org.apache.commons.lang.StringUtils;
import org.patientview.model.Patient;
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

        List<Patient> patientList = jdbcTemplate.query("SELECT * FROM patient",
                new EncryptedDemographicsRowMapper());

        StringBuilder outputText = new StringBuilder();
        for (Patient patient : patientList) {
            String updateStatement = "UPDATE patient SET ";

            if (patient.getNhsno() != null) {
                updateStatement += " nhsno = '" + patient.getNhsno() + "', ";
            }

            if (patient.getHospitalnumber() != null) {
                updateStatement += " hospitalnumber = \"" + patient.getHospitalnumber() + "\", ";
            }

            if (patient.getSurname() != null) {
                updateStatement += " surname = \"" + patient.getSurname() + "\", ";
            }

            if (patient.getForename() != null) {
                updateStatement += " forename = \"" + patient.getForename() + "\", ";
            }

            if (patient.getSurnameAlias() != null) {
                updateStatement += " surnameAlias = \"" + patient.getSurnameAlias() + "\", ";
            }

            if (patient.getDob() != null) {
                // just guess what a sane date format is
                updateStatement += " dateofbirth = \""
                        + new SimpleDateFormat(DATE_FORMAT_2).format(patient.getDob()) + "\", ";
            }

            if (patient.getAddress1() != null) {
                updateStatement += " address1 = \"" + patient.getAddress1() + "\", ";
            }

            if (patient.getAddress2() != null) {
                updateStatement += " address2 = \"" + patient.getAddress2() + "\", ";
            }

            if (patient.getAddress3() != null) {
                updateStatement += " address3 = \"" + patient.getAddress3() + "\", ";
            }

            if (patient.getAddress4() != null) {
                updateStatement += " address4 = \"" + patient.getAddress4() + "\", ";
            }

            if (patient.getPostcode() != null) {
                updateStatement += " POSTCODE = \"" + patient.getPostcode() + "\", ";
            }

            if (patient.getPostcodeOld() != null) {
                updateStatement += " postcodeOld = \"" + patient.getPostcodeOld() + "\", ";
            }

            updateStatement += " radarNo = " + patient.getId();
            updateStatement += " WHERE radarNo = " + patient.getId();
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

    private class EncryptedDemographicsRowMapper implements RowMapper<Patient> {
        public Patient mapRow(ResultSet resultSet, int i) throws SQLException {
            Patient patient = new Patient();
            patient.setId(resultSet.getLong("radarNo"));

            try {
                patient.setNhsno(getDecryptedString(patient.getId() + "", "nhsno",
                        resultSet.getBytes("nhsno")));
                patient.setHospitalnumber(getDecryptedString(patient.getId() + "", "hospitalnumber",
                        resultSet.getBytes("hospitalnumber")));
                patient.setSurname(getDecryptedString(patient.getId() + "", "surname",
                        resultSet.getBytes("surname")));
                patient.setSurnameAlias(getDecryptedString(patient.getId() + "", "surnameAlias",
                        resultSet.getBytes("surnameAlias")));
                patient.setForename(getDecryptedString(patient.getId() + "", "forename",
                        resultSet.getBytes("forename")));

                // Date needs to be decrypted to string, then parsed
                String dateOfBirthString = getDecryptedString(patient.getId() + "", "dateofbirth",
                        resultSet.getBytes("dateofbirth"));

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
                        patient.setDob(dateOfBirth);
                    } else {
                        LOGGER.error("Could not parse date of birth from any format for dob {}",
                                dateOfBirthString);
                    }
                }

                // Addresses, all encrypted too
                patient.setAddress1(getDecryptedString(patient.getId() + "", "address1",
                        resultSet.getBytes("address1")));
                patient.setAddress2(getDecryptedString(patient.getId() + "", "address2",
                        resultSet.getBytes("address2")));
                patient.setAddress3(getDecryptedString(patient.getId() + "", "address3",
                        resultSet.getBytes("address3")));
                patient.setAddress4(getDecryptedString(patient.getId() + "", "address4",
                        resultSet.getBytes("address4")));
                patient.setPostcode(getDecryptedString(patient.getId() + "", "POSTCODE",
                        resultSet.getBytes("POSTCODE")));
                patient.setPostcodeOld(getDecryptedString(patient.getId() + "", "postcodeOld",
                        resultSet.getBytes("postcodeOld")));

            } catch (Exception e) {
                LOGGER.error("Could not decrypt demographics information for demographics {}", patient.getId());
                LOGGER.debug(e.getMessage(), e);
            }

            return patient;
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
