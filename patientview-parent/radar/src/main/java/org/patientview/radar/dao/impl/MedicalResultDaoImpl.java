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

import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.generic.MedicalResultDao;
import org.patientview.radar.model.BaseModel;
import org.patientview.radar.model.generic.MedicalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MedicalResultDaoImpl extends BaseDaoImpl implements MedicalResultDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalResultDaoImpl.class);
    private SimpleJdbcInsert medicalResultInsert;

    private DiseaseGroupDao diseaseGroupDao;

    private static final String UREA = "urea";
    private static final String CREATININE = "creatinine";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String BPSYS = "bpsys";
    private static final String BPDIA = "bpdia";
    private static final String ANTIHYPERTENSIVE = "antihypertensive";
    private static final String PCR = "pcr";
    private static final String ACR = "acr";

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        medicalResultInsert = new SimpleJdbcInsert(dataSource).withTableName("testresult")
                .usingGeneratedKeyColumns("id")
                .usingColumns("RADAR_NO", "unitcode", "testcode", "datestamp", "prepost", "value", "nhsNo"
                );
    }

    public void save(MedicalResult medicalResult) {
        // each field of the medical result has to be entered in its own row
        // convert medical result object into a list of item objects, each item represents a row/field
        List<MedicalResultItem> medicalResultItems = mapMedicalResultToMedicalResultItems(medicalResult);

        Map<String, Object>[] medicalResultsMaps = new HashMap[medicalResultItems.size()];

        // set the map for each item
        for (int i = 0; i < medicalResultItems.size(); i++) {
            MedicalResultItem item = medicalResultItems.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("RADAR_NO", medicalResult.getRadarNo());
            map.put("unitcode", medicalResult.getDiseaseGroup().getId());
            map.put("testcode", item.getTestcode());
            map.put("value", item.getObjectValue());
            map.put("datestamp", item.getDate());
            map.put("nhsNo", medicalResult.getNhsNo());
            medicalResultsMaps[i] = map;
        }

        // if record exists then update
        for (Map map : medicalResultsMaps) {
            String unitCode = (String) map.get("unitcode");
            String testCode = (String) map.get("testcode");
            Object value = map.get("value");

            // see if there is already a row for this test per uni and radar no and update
            MedicalResultItem medicalResultItem = getMedicalResultItem(medicalResult.getRadarNo(), unitCode, testCode);

            if (medicalResultItem != null && medicalResultItem.hasValidId() &&
                    !medicalResult.isToBeUpdated()) {

                // if the value is not null then update it else delete the existing result
                if (value != null) {
                    String setString = " SET ";
                    List<String> keyList = new ArrayList<String>(map.keySet());

                    for (int i = 0; i < keyList.size(); i++) {
                        String key = keyList.get(i);
                        setString += " " + key + " = " + ":" + key;
                        setString += (i < keyList.size() - 1) ? ", " : "";
                    }

                    namedParameterJdbcTemplate.update("UPDATE testresult " + setString + " WHERE id = "
                            + medicalResultItem.getId(), map);
                } else {
                    namedParameterJdbcTemplate.update("DELETE from testresult WHERE id = " + medicalResultItem.getId(),
                            map);
                }
            } else {
                // only insert if it has a value
                if (value != null) {
                    medicalResultInsert.executeAndReturnKey(map);
                }
            }
        }
    }

    public MedicalResult getMedicalResult(long radarNumber, String unitCode) {
        // each field of the medical result has its own row of results
        // read each row as a item object
        List<MedicalResultItem> medicalResultItems = jdbcTemplate.query("SELECT * FROM testresult WHERE " +
                "RADAR_NO = ? AND unitCode = ?",
                new Object[]{radarNumber, unitCode}, new MedicalResultItemRowMapper());

        // iterate through the row/medical result items and set the model properties
        if (!medicalResultItems.isEmpty()) {
            MedicalResult medicalResult = parseMedicalResultItems(medicalResultItems);
            medicalResult.setRadarNo(radarNumber);
            return medicalResult;
        }

        return null;
    }

    private MedicalResultItem getMedicalResultItem(long radarNumber, String unitCode, String testCode) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM testresult WHERE " +
                    "RADAR_NO = ? AND unitCode = ? AND testCode = ?",
                    new Object[]{radarNumber, unitCode, testCode}, new MedicalResultItemRowMapper());
        } catch (Exception e) {
            return null; // record doesnt exist
        }
    }

    public void setDiseaseGroupDao(DiseaseGroupDao diseaseGroupDao) {
        this.diseaseGroupDao = diseaseGroupDao;
    }

    private class MedicalResultItemRowMapper implements RowMapper<MedicalResultItem> {
        public MedicalResultItem mapRow(ResultSet resultSet, int i) throws SQLException {
            MedicalResultItem item = new MedicalResultItem();
            item.setId(resultSet.getLong("id"));
            item.setRadarNo(resultSet.getLong("RADAR_NO"));
            item.setDate(resultSet.getDate("datestamp"));
            item.setTestcode(resultSet.getString("testcode"));
            item.setValue(resultSet.getString("value"));
            item.setDiseaseGroupId(resultSet.getString("unitcode"));
            return item;
        }
    }

    /**
     * maps a MedicalResult into a list of Item objects
     *
     * @param medicalResult
     * @return list of MedicalResultItem which represent a row in the db
     */
    private List<MedicalResultItem> mapMedicalResultToMedicalResultItems(MedicalResult medicalResult) {
        List<MedicalResultItem> medicalResultItems = new ArrayList<MedicalResultItem>();

        String[] testCodes = {UREA, CREATININE, WEIGHT, HEIGHT, BPSYS, BPDIA, ANTIHYPERTENSIVE, PCR, ACR};

        for (String code : testCodes) {
            MedicalResultItem item = new MedicalResultItem();
            item.setRadarNo(medicalResult.getRadarNo());
            item.setDiseaseGroupId(medicalResult.getDiseaseGroup().getId());
            item.setTestcode(code);

            if (code.equalsIgnoreCase(UREA)) {
                item.setObjectValue(medicalResult.getBloodUrea());
                item.setDate(medicalResult.getBloodUreaDate());
            } else if (code.equalsIgnoreCase(CREATININE)) {
                item.setObjectValue(medicalResult.getSerumCreatanine());
                item.setDate(medicalResult.getCreatanineDate());
            } else if (code.equalsIgnoreCase(WEIGHT)) {
                item.setObjectValue(medicalResult.getWeight());
                item.setDate(medicalResult.getWeightDate());
            } else if (code.equalsIgnoreCase(HEIGHT)) {
                item.setObjectValue(medicalResult.getHeight());
                item.setDate(medicalResult.getHeightDate());
            } else if (code.equalsIgnoreCase(BPSYS)) {
                item.setObjectValue(medicalResult.getBpSystolic());
                item.setDate(medicalResult.getBpDate());
            } else if (code.equalsIgnoreCase(BPDIA)) {
                item.setObjectValue(medicalResult.getBpDiastolic());
                item.setDate(medicalResult.getBpDate());
            } else if (code.equalsIgnoreCase(ANTIHYPERTENSIVE)) {
                if (medicalResult.getAntihypertensiveDrugs() != null) {
                    item.setObjectValue(medicalResult.getAntihypertensiveDrugs().getId());
                    item.setDate(medicalResult.getAntihypertensiveDrugsDate());
                }
            } else if (code.equalsIgnoreCase(PCR)) {
                item.setObjectValue(medicalResult.getPcr());
                item.setDate(medicalResult.getPcrDate());
            } else if (code.equalsIgnoreCase(ACR)) {
                item.setObjectValue(medicalResult.getAcr());
                item.setDate(medicalResult.getAcrDate());
            }

            medicalResultItems.add(item);
        }

        return medicalResultItems;
    }

    private MedicalResult parseMedicalResultItems(List<MedicalResultItem> medicalResultItems) {
        MedicalResult medicalResult = new MedicalResult();

        for (MedicalResultItem item : medicalResultItems) {
            // we only really need to do this once but this is crap
            medicalResult.setDiseaseGroup(diseaseGroupDao.getById(item.getDiseaseGroupId()));
            medicalResult.setRadarNo(item.getRadarNo());

            Date date = item.getDate();

            if (item.getTestcode().equalsIgnoreCase(UREA)) {
                if (item.getValue() != null) {
                    Double urea = Double.parseDouble(item.getValue());
                    medicalResult.setBloodUrea(urea);
                }
                medicalResult.setBloodUreaDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(CREATININE)) {
                if (item.getValue() != null) {
                    Double creatanine = Double.parseDouble(item.getValue());
                    medicalResult.setSerumCreatanine(creatanine);
                }
                medicalResult.setCreatanineDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(WEIGHT)) {
                if (item.getValue() != null) {
                    Double weight = Double.parseDouble(item.getValue());
                    medicalResult.setWeight(weight);
                }
                medicalResult.setWeightDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(HEIGHT)) {
                if (item.getValue() != null) {
                    Double height = Double.parseDouble(item.getValue());
                    medicalResult.setHeight(height);
                }
                medicalResult.setHeightDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(BPSYS)) {
                if (item.getValue() != null) {
                    Integer bpSys = Integer.parseInt(item.getValue());
                    medicalResult.setBpSystolic(bpSys);
                }
                medicalResult.setBpDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(BPDIA)) {
                if (item.getValue() != null) {
                    Integer bpDia = Integer.parseInt(item.getValue());
                    medicalResult.setBpDiastolic(bpDia);
                }
                medicalResult.setBpDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(ANTIHYPERTENSIVE)) {
                if (item.getValue() != null) {
                    MedicalResult.YesNo yesNo = MedicalResult.YesNo.getById(item.getValue());
                    medicalResult.setAntihypertensiveDrugs(yesNo);
                }
                medicalResult.setAntihypertensiveDrugsDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(PCR)) {
                if (item.getValue() != null) {
                    medicalResult.setPcr(Integer.parseInt(item.getValue()));
                }
                medicalResult.setPcrDate(date);
            } else if (item.getTestcode().equalsIgnoreCase(ACR)) {
                if (item.getValue() != null) {
                    medicalResult.setAcr(Integer.parseInt(item.getValue()));
                }
                medicalResult.setAcrDate(date);
            }
        }

        return medicalResult;
    }

    /**
     * represents a row - since a single medical result object is stored as many rows
     */
    private class MedicalResultItem extends BaseModel {
        Long radarNo;
        String testcode;
        Date date;
        String value;
        Object objectValue;
        String diseaseGroupId;

        public Long getRadarNo() {
            return radarNo;
        }

        public void setRadarNo(Long radarNo) {
            this.radarNo = radarNo;
        }

        public String getTestcode() {

            // All the results must be written to the testresult table so testresult.testcode matches the
            // result_heading.headingcode, thus all should be lowercase.
            // ACR, PCR, BPsys and BPdia should be acr, pcr, bpsys and bpdia

            if (testcode != null && testcode.length() > 0) {
                testcode = testcode.toLowerCase();
            }

            return testcode;
        }

        public void setTestcode(String testcode) {
            this.testcode = testcode;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getObjectValue() {
            return objectValue;
        }

        public void setObjectValue(Object objectValue) {
            this.objectValue = objectValue;
        }

        public String getDiseaseGroupId() {
            return diseaseGroupId;
        }

        public void setDiseaseGroupId(String diseaseGroupId) {
            this.diseaseGroupId = diseaseGroupId;
        }
    }
}
