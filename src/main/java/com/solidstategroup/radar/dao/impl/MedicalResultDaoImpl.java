package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.generic.DiseaseGroupDao;
import com.solidstategroup.radar.dao.generic.MedicalResultDao;
import com.solidstategroup.radar.model.generic.MedicalResult;
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

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        medicalResultInsert = new SimpleJdbcInsert(dataSource).withTableName("testresult");
    }

    public void save(MedicalResult medicalResult) {
        // each field of the medical result has to be entered in its own row
        // convert medical result object into a list of item objects, each item represents a row/field
        List<MedicalResultItem> medicalResultItems = mapMedicalResultToMedicalResultItems(medicalResult);
        Map<String, Object>[] medicalResultsMaps = new HashMap[medicalResultItems.size()];
        // each map for each medical result row/field

        // set the map for each item
        for (int i = 0; i < medicalResultItems.size(); i++) {
            MedicalResultItem item = medicalResultItems.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nhsno", medicalResult.getId());
            map.put("unitcode", medicalResult.getDiseaseGroup().getId());
            map.put("testcode", item.getTestcode());
            map.put("value", item.getObjectValue());
            map.put("datestamp", item.getDate());
            medicalResultsMaps[i] = map;
        }

        // if record exists then update
        if (getById(medicalResult.getId()) != null) {
            for (Map map : medicalResultsMaps) {
                String setString = " SET ";
                List<Object> keyList = new ArrayList(map.keySet());
                for (int i = 0; i < keyList.size(); i++) {
                    String key = (String) keyList.get(i);
                    if (!key.equals("nhsno")) {
                        setString += " " + key + " = " + ":" + key;
                        setString += (i < keyList.size() - 2) ? ", " : "";
                    }
                }
                namedParameterJdbcTemplate.update("UPDATE testresult " + setString + " WHERE nhsno = :nhsno AND" +
                        " testcode = :testcode;", map);
            }

            // else insert
        } else {
            medicalResultInsert.executeBatch(medicalResultsMaps);
        }
    }

    public MedicalResult getById(String id) {
        // each field of the medical result has its own row of results
        // read each row as a item object
        List<MedicalResultItem> medicalResultItems = jdbcTemplate.query("SELECT * FROM testresult WHERE nhsno = ?",
                new Object[]{id}, new MedicalResultItemRowMapper());

        // iterate through the row/medical result items and set the model properties
        if (!medicalResultItems.isEmpty()) {
            MedicalResult medicalResult = new MedicalResult();
            medicalResult.setId(id);

            for (MedicalResultItem item : medicalResultItems) {
                medicalResult.setDiseaseGroup(diseaseGroupDao.getById(item.getDiseaseGroupId()));

                Date date = item.getDate();
                if (item.getTestcode().equals("urea")) {
                    Double urea = Double.parseDouble(item.getValue());
                    medicalResult.setBloodUrea(urea);
                    medicalResult.setBloodUreaDate(date);
                } else if (item.getTestcode().equals("creatinine")) {
                    Double creatanine = Double.parseDouble(item.getValue());
                    medicalResult.setSerumCreatanine(creatanine);
                    medicalResult.setCreatanineDate(date);
                } else if (item.getTestcode().equals("weight")) {
                    Double weight = Double.parseDouble(item.getValue());
                    medicalResult.setWeight(weight);
                    medicalResult.setWeightDate(date);
                } else if (item.getTestcode().equals("height")) {
                    Double height = Double.parseDouble(item.getValue());
                    medicalResult.setHeight(height);
                    medicalResult.setHeightDate(date);
                } else if (item.getTestcode().equals("BPsys")) {
                    Integer bpSys = Integer.parseInt(item.getValue());
                    medicalResult.setBpSystolic(bpSys);
                    medicalResult.setBpDate(date);
                } else if (item.getTestcode().equals("BPdia")) {
                    Integer bpDia = Integer.parseInt(item.getValue());
                    medicalResult.setBpDiastolic(bpDia);
                } else if (item.getTestcode().equals("antihypertensive")) {
                    MedicalResult.YesNo yesNo = MedicalResult.YesNo.getById(item.getValue());
                    medicalResult.setAntihypertensiveDrugs(yesNo);
                }
            }
            return medicalResult;
        }

        return null;
    }

    public void setDiseaseGroupDao(DiseaseGroupDao diseaseGroupDao) {
        this.diseaseGroupDao = diseaseGroupDao;
    }

    private class MedicalResultItemRowMapper implements RowMapper<MedicalResultItem> {
        public MedicalResultItem mapRow(ResultSet resultSet, int i) throws SQLException {
            MedicalResultItem item = new MedicalResultItem();
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

        String[] testCodes = {"urea", "creatinine", "weight", "height", "BPsys", "BPdia", "antihypertensive"};
        for (String code : testCodes) {
            MedicalResultItem item = new MedicalResultItem();
            item.setTestcode(code);
            if (code.equals("urea")) {
                item.setObjectValue(medicalResult.getBloodUrea());
                item.setDate(medicalResult.getBloodUreaDate());
            } else if (code.equals("creatinine")) {
                item.setObjectValue(medicalResult.getSerumCreatanine());
                item.setDate(medicalResult.getCreatanineDate());
            } else if (code.equals("weight")) {
                item.setObjectValue(medicalResult.getWeight());
                item.setDate(medicalResult.getWeightDate());
            } else if (code.equals("height")) {
                item.setObjectValue(medicalResult.getHeight());
                item.setDate(medicalResult.getHeightDate());
            } else if (code.equals("BPsys")) {
                item.setObjectValue(medicalResult.getBpSystolic());
                item.setDate(medicalResult.getBpDate());
            } else if (code.equals("BPdia")) {
                item.setObjectValue(medicalResult.getBpDiastolic());
                item.setDate(medicalResult.getBpDate());
            } else if (code.equals("antihypertensive")) {
                if (medicalResult.getAntihypertensiveDrugs() != null) {
                    item.setObjectValue(medicalResult.getAntihypertensiveDrugs().getId());
                    item.setDate(medicalResult.getBpDate());
                }

            }

            if (item.getDate() != null) {
                medicalResultItems.add(item);
            }
        }

        return medicalResultItems;
    }

    /**
     * represents a row - since a single medical result object is stored as many rows
     */
    private class MedicalResultItem {
        String testcode;
        Date date;
        String value;
        Object objectValue;
        String diseaseGroupId;

        public String getTestcode() {
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
