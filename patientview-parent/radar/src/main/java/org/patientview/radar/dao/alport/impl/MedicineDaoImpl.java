package org.patientview.radar.dao.alport.impl;

import org.patientview.radar.dao.alport.MedicineDao;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.impl.BaseDaoImpl;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineDaoImpl extends BaseDaoImpl implements MedicineDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineDaoImpl.class);

    private static final String TABLE_NAME = "medicine";
    private static final String ID_FIELD_NAME = "id";
    private static final String NHS_NO_FIELD_NAME = "nhsno";
    private static final String UNIT_CODE_FIELD_NAME = "unitcode";
    private static final String NAME_FIELD_NAME = "name";
    private static final String DOSE_FIELD_NAME = "dose";
    private static final String START_DATE_FIELD_NAME = "startdate";
    private static final String END_DATE_FIELD_NAME = "enddate";

    private SimpleJdbcInsert medicineInsert;

    private DiseaseGroupDao diseaseGroupDao;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        medicineInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(NHS_NO_FIELD_NAME, UNIT_CODE_FIELD_NAME, NAME_FIELD_NAME,
                        DOSE_FIELD_NAME, START_DATE_FIELD_NAME, END_DATE_FIELD_NAME);
    }

    public void save(Medicine medicine) {
        Map<String, Object> geneticsMap = new HashMap<String, Object>();
        geneticsMap.put(ID_FIELD_NAME, medicine.getId());
        geneticsMap.put(NHS_NO_FIELD_NAME, medicine.getNhsNo());
        geneticsMap.put(UNIT_CODE_FIELD_NAME, medicine.getDiseaseGroup().getId());
        geneticsMap.put(NAME_FIELD_NAME, medicine.getName());
        geneticsMap.put(DOSE_FIELD_NAME, medicine.getDose());
        geneticsMap.put(START_DATE_FIELD_NAME, medicine.getStartDate());
        geneticsMap.put(END_DATE_FIELD_NAME, medicine.getEndDate());

        if (medicine.hasValidId()) {
            namedParameterJdbcTemplate.update(buildUpdateQuery(TABLE_NAME, ID_FIELD_NAME, geneticsMap), geneticsMap);
        } else {
            Number id = medicineInsert.executeAndReturnKey(geneticsMap);
            medicine.setId(id.longValue());
        }
    }

    public void delete(Medicine medicine) {
        Map<String, Object> geneticsMap = new HashMap<String, Object>();
        geneticsMap.put(ID_FIELD_NAME, medicine.getId());

        namedParameterJdbcTemplate.update("DELETE FROM " + TABLE_NAME + " WHERE " + ID_FIELD_NAME + " = :"
                + ID_FIELD_NAME, geneticsMap);
    }

    public Medicine get(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_FIELD_NAME + " = ?",
                    new Object[]{id}, new MedicineRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not find row in table " + TABLE_NAME + " with " + ID_FIELD_NAME + " {}", id);
        }
        return null;
    }

    public List<Medicine> getMedicinesByNhsNo(String nhsNo) {
        try {
            return jdbcTemplate.query("SELECT * FROM " + TABLE_NAME + " WHERE " + NHS_NO_FIELD_NAME + " = ?",
                    new Object[]{nhsNo}, new MedicineRowMapper());
        } catch (DataAccessException e) {
            LOGGER.debug("Could not find rows in table " + TABLE_NAME + " with " + NHS_NO_FIELD_NAME + " {}", nhsNo);
        }

        return Collections.emptyList();
    }

    public List<Medicine> getMedicinesByNhsNoAndDiseaseGroup(String nhsNo, DiseaseGroup diseaseGroup) {
        try {
            return jdbcTemplate.query("SELECT * FROM " + TABLE_NAME + " WHERE " + NHS_NO_FIELD_NAME + " = ? AND "
                    + UNIT_CODE_FIELD_NAME + " = ?",
                    new Object[]{nhsNo, diseaseGroup.getId()}, new MedicineRowMapper());
        } catch (DataAccessException e) {
            LOGGER.debug("Could not find rows in table " + TABLE_NAME + " with " + NHS_NO_FIELD_NAME + " and "
                    + UNIT_CODE_FIELD_NAME + " {}", nhsNo, diseaseGroup.getId());
        }

        return Collections.emptyList();
    }

    public void setDiseaseGroupDao(DiseaseGroupDao diseaseGroupDao) {
        this.diseaseGroupDao = diseaseGroupDao;
    }

    private class MedicineRowMapper implements RowMapper<Medicine> {
        public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
            Medicine medicine = new Medicine();

            medicine.setId(rs.getLong(ID_FIELD_NAME));
            medicine.setNhsNo(rs.getString(NHS_NO_FIELD_NAME));
            medicine.setDiseaseGroup(diseaseGroupDao.getById(rs.getString(UNIT_CODE_FIELD_NAME)));
            medicine.setName(rs.getString(NAME_FIELD_NAME));
            medicine.setDose(rs.getString(DOSE_FIELD_NAME));
            medicine.setStartDate(rs.getDate(START_DATE_FIELD_NAME));
            medicine.setEndDate(rs.getDate(END_DATE_FIELD_NAME));

            return medicine;
        }
    }
}
