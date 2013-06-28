package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.GenericDiagnosis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GenericDiagnosisDaoImpl extends BaseDaoImpl implements GenericDiagnosisDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDiagnosisDaoImpl.class);

    public List<GenericDiagnosis> getAll() {
        return jdbcTemplate.query("SELECT * FROM rdr_prd_code, rdr_diagnosis_mapping" +
                " WHERE rdr_prd_code.ERA_EDTA_PRD_code = rdr_diagnosis_mapping.PRDCode",
                new GenericDiagnosisRowMapper());
    }

    public List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup) {
        List<GenericDiagnosis> genericDiagnosises = jdbcTemplate.query("SELECT * FROM rdr_prd_code, " +
                "rdr_diagnosis_mapping" +
                " WHERE rdr_prd_code.ERA_EDTA_PRD_code = rdr_diagnosis_mapping.PRDCode" +
                " AND rdr_diagnosis_mapping.workingGroup = ?", new Object[]{diseaseGroup.getId()},
                new GenericDiagnosisRowMapper());
        Collections.sort(genericDiagnosises);
        return genericDiagnosises;
    }

    public GenericDiagnosis get(String prdCode, String workingGroup) {
        try {
            List<GenericDiagnosis> genericDiagnosises = jdbcTemplate.query(
                    "SELECT * " +
                    "FROM RDR_PRD_CODE, RDR_DIAGNOSIS_MAPPING " +
                    "WHERE RDR_PRD_CODE.ERA_EDTA_PRD_code = RDR_DIAGNOSIS_MAPPING.PRDCode " +
                    "AND  RDR_DIAGNOSIS_MAPPING.PRDCode = ? AND RDR_DIAGNOSIS_MAPPING.workingGroup = ?",
                    new Object[]{prdCode, workingGroup}, new GenericDiagnosisRowMapper());

            if (genericDiagnosises != null && !genericDiagnosises.isEmpty()) {
                return genericDiagnosises.get(0);
            }
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("GenericDiagnosis with prdCode {} and workingGroup {} was not found", prdCode, workingGroup);
            return null;
        }

        return null;
    }

    private class GenericDiagnosisRowMapper implements RowMapper<GenericDiagnosis> {
        public GenericDiagnosis mapRow(ResultSet resultSet, int i) throws SQLException {
            GenericDiagnosis genericDiagnosis = new GenericDiagnosis();
            genericDiagnosis.setId(resultSet.getString("rdr_prd_code.ERA_EDTA_PRD_code"));
            genericDiagnosis.setTerm(resultSet.getString("rdr_prd_code.ERA_EDTA_primaryRenalDiagnosisTerm"));
            Integer order = getIntegerWithNullCheck("ordering", resultSet);
            genericDiagnosis.setOrder(order != null ? order : 0);
            return genericDiagnosis;
        }
    }
}
