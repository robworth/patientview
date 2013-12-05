package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.PatientLinkDao;
import org.patientview.model.PatientLink;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles the linkage between two patient records.
 *
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 16:00
 */
public class PatientLinkDaoImpl extends BaseDaoImpl implements PatientLinkDao {

    private SimpleJdbcInsert linkInsert;


    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        linkInsert = new SimpleJdbcInsert(dataSource).withTableName("rdr_patient_linkage")
                .usingGeneratedKeyColumns("id").usingColumns("source_nhsno", "source_unitcode", "dest_nhsno",
                        "dest_unitcode");
    }

    public PatientLink createLink(final PatientLink patientLink) {

        Map<String, Object> clinicalDataMap = new HashMap<String, Object>() {
            {
                put("source_nhsno", patientLink.getSourceNhsNO());
                put("source_unitcode", patientLink.getSourceUnit());
                put("dest_nhsno", patientLink.getDestinationNhsNo());
                put("dest_unitcode", patientLink.getDestinationUnit());

            }
        };

        Number id = linkInsert.executeAndReturnKey(clinicalDataMap);
        patientLink.setId(id.longValue());

        return patientLink;

    }

    public PatientLink getPatientLink(String nhsNo, String unitCode) {
        PatientLink patientLink = null;

        StringBuilder query = new StringBuilder();
        query.append("SELECT   * ");
        query.append("FROM     rdr_patient_linkage ");
        query.append("WHERE    source_nhsno = ? ");
        query.append("AND      source_unitcode = ? ");

        try {
            patientLink = jdbcTemplate.queryForObject(query.toString(), new Object[]{nhsNo, unitCode},
                    new PatientLinkDataRowMapper());
            return patientLink;
        } catch (Exception e) {
            return  null;
        }

    }

    public PatientLink getSourcePatientLink(String nhsNo, String unitCode) {
        PatientLink patientLink = null;

        StringBuilder query = new StringBuilder();
        query.append("SELECT   * ");
        query.append("FROM     rdr_patient_linkage ");
        query.append("WHERE    dest_nhsno = ? ");
        query.append("AND      dest_unitcode = ? ");

        try {
            patientLink = jdbcTemplate.queryForObject(query.toString(), new Object[]{nhsNo, unitCode},
                    new PatientLinkDataRowMapper());
            return patientLink;
        } catch (Exception e) {
            return  null;
        }

    }

    private class PatientLinkDataRowMapper implements RowMapper<PatientLink> {
        public PatientLink mapRow(ResultSet resultSet, int i) throws SQLException {
            PatientLink patientLink = new PatientLink();

            patientLink.setSourceNhsNO(resultSet.getString("source_nhsno"));
            patientLink.setSourceUnit(resultSet.getString("source_unitcode"));
            patientLink.setDestinationNhsNo(resultSet.getString("dest_nhsno"));
            patientLink.setDestinationUnit(resultSet.getString("dest_unitcode"));

            return patientLink;
        }

    }
}
