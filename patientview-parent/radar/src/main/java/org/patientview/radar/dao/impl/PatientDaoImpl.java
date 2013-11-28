package org.patientview.radar.dao.impl;

import org.patientview.model.Patient;
import org.patientview.radar.dao.PatientDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 10:13
 */
public class PatientDaoImpl extends BaseDaoImpl implements PatientDao {

    public List<Patient> getPatientsByNhsNumber(String nhsNo) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT  id ");
        query.append(",       nhsno ");
        query.append(",       forename ");
        query.append(",       surname ");
        query.append(",       dateofbirth ");
        query.append(",       unitcode ");
        query.append(",       mostRecentTestResultDateRangeStopDate ");
        query.append("FROM    patient ");
        query.append("WHERE   nhsNo = ? ");
        query.append("ORDER BY   mostRecentTestResultDateRangeStopDate DESC ");

        return jdbcTemplate.query(query.toString(), new Object[]{nhsNo}, new PatientSearchMapper());
    }

    private class PatientSearchMapper implements RowMapper<Patient> {

        public Patient mapRow(ResultSet resultSet, int i) throws SQLException {
            Patient patient = new Patient();
            patient.setId(resultSet.getLong("id"));
            patient.setNhsno(resultSet.getString("nhsno"));
            patient.setSurname(resultSet.getString("surname"));
            patient.setForename(resultSet.getString("forename"));
            patient.setDateofbirth(resultSet.getString("dateofbirth"));
            patient.setUnitcode(resultSet.getString("unitcode"));
            patient.setMostRecentTestResultDateRangeStopDate(
                    resultSet.getDate("mostRecentTestResultDateRangeStopDate"));

            return patient;
        }
    }


}
