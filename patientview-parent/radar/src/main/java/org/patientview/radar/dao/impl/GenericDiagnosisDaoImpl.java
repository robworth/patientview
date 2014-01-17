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

package org.patientview.radar.dao.impl;

import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;
import org.patientview.radar.dao.generic.GenericDiagnosisDao;
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
            genericDiagnosis.setPrdCode(resultSet.getString("RDR_DIAGNOSIS_MAPPING.PRDCode"));
            genericDiagnosis.setWorkingGroup(resultSet.getString("RDR_DIAGNOSIS_MAPPING.workingGroup"));
            Integer order = getIntegerWithNullCheck("ordering", resultSet);
            genericDiagnosis.setOrder(order != null ? order : 0);
            return genericDiagnosis;
        }
    }
}
