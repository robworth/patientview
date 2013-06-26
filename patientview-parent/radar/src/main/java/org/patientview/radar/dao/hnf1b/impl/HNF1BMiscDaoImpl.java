package org.patientview.radar.dao.hnf1b.impl;

import org.patientview.radar.dao.hnf1b.HNF1BMiscDao;
import org.patientview.radar.dao.impl.BaseDaoImpl;
import org.patientview.radar.model.enums.YesNo;
import org.patientview.radar.model.hnf1b.HNF1BMisc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HNF1BMiscDaoImpl extends BaseDaoImpl implements HNF1BMiscDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HNF1BMiscDaoImpl.class);

    private static final String TABLE_NAME = "rdr_hnf1b_misc";
    private static final String ID_FIELD_NAME = "id";
    private static final String RADAR_NO_FIELD_NAME = "radar_no";
    private static final String RENAL_CYSTS_FIELD_NAME = "renalCysts";
    private static final String SINGLE_KIDNEY_FIELD_NAME = "singleKidney";
    private static final String OTHER_RENAL_MALFORMATIONS_FIELD_NAME = "otherRenalMalformations";
    private static final String OTHER_RENAL_MALFORMATIONS_DETAILS_FIELD_NAME = "otherRenalMalformationsDetails";
    private static final String DIABETES_FIELD_NAME = "diabetes";
    private static final String AGE_AT_DIABETES_DIAGNOSIS_FIELD_NAME = "ageAtDiabetesDiagnosis";
    private static final String GOUT_FIELD_NAME = "gout";
    private static final String AGE_AT_GOUT_DIAGNOSIS_FIELD_NAME = "ageAtGoutDiagnosis";
    private static final String GENITAL_MALFORMATION_FIELD_NAME = "genitalMalformation";
    private static final String GENITAL_MALFORMATION_DETAILS_FIELD_NAME = "genitalMalformationDetails";

    private SimpleJdbcInsert hnf1BMiscInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        hnf1BMiscInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_FIELD_NAME)
                .usingColumns(RADAR_NO_FIELD_NAME, RENAL_CYSTS_FIELD_NAME,
                        SINGLE_KIDNEY_FIELD_NAME, OTHER_RENAL_MALFORMATIONS_FIELD_NAME,
                        OTHER_RENAL_MALFORMATIONS_DETAILS_FIELD_NAME, DIABETES_FIELD_NAME,
                        AGE_AT_DIABETES_DIAGNOSIS_FIELD_NAME, GOUT_FIELD_NAME, AGE_AT_GOUT_DIAGNOSIS_FIELD_NAME,
                        GENITAL_MALFORMATION_FIELD_NAME, GENITAL_MALFORMATION_DETAILS_FIELD_NAME);
    }

    public void save(HNF1BMisc hnf1BMisc) {
        Map<String, Object> geneticsMap = new HashMap<String, Object>();
        geneticsMap.put(ID_FIELD_NAME, hnf1BMisc.getId());
        geneticsMap.put(RADAR_NO_FIELD_NAME, hnf1BMisc.getRadarNo());

        if (hnf1BMisc.getRenalCysts() != null) {
            geneticsMap.put(RENAL_CYSTS_FIELD_NAME, hnf1BMisc.getRenalCysts().getId());
        }

        if (hnf1BMisc.getSingleKidney() != null) {
            geneticsMap.put(SINGLE_KIDNEY_FIELD_NAME, hnf1BMisc.getSingleKidney().getId());
        }

        if (hnf1BMisc.getOtherRenalMalformations() != null) {
            geneticsMap.put(OTHER_RENAL_MALFORMATIONS_FIELD_NAME, hnf1BMisc.getOtherRenalMalformations().getId());
        }

        geneticsMap.put(OTHER_RENAL_MALFORMATIONS_DETAILS_FIELD_NAME, hnf1BMisc.getOtherRenalMalformationsDetails());

        if (hnf1BMisc.getDiabetes() != null) {
            geneticsMap.put(DIABETES_FIELD_NAME, hnf1BMisc.getDiabetes().getId());
        }

        geneticsMap.put(AGE_AT_DIABETES_DIAGNOSIS_FIELD_NAME, hnf1BMisc.getAgeAtDiabetesDiagnosis());

        if (hnf1BMisc.getGout() != null) {
            geneticsMap.put(GOUT_FIELD_NAME, hnf1BMisc.getGout().getId());
        }

        geneticsMap.put(AGE_AT_GOUT_DIAGNOSIS_FIELD_NAME, hnf1BMisc.getAgeAtGoutDiagnosis());

        if (hnf1BMisc.getGenitalMalformation() != null) {
            geneticsMap.put(GENITAL_MALFORMATION_FIELD_NAME, hnf1BMisc.getGenitalMalformation().getId());
        }

        geneticsMap.put(GENITAL_MALFORMATION_DETAILS_FIELD_NAME, hnf1BMisc.getGenitalMalformationDetails());

        if (hnf1BMisc.hasValidId()) {
            namedParameterJdbcTemplate.update(buildUpdateQuery(TABLE_NAME, ID_FIELD_NAME, geneticsMap), geneticsMap);
        } else {
            Number id = hnf1BMiscInsert.executeAndReturnKey(geneticsMap);
            hnf1BMisc.setId(id.longValue());
        }
    }

    public HNF1BMisc get(Long radarNo) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE " + RADAR_NO_FIELD_NAME + " = ?",
                    new Object[]{radarNo}, new HNF1BMiscRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not find row in table " + TABLE_NAME + " with " + RADAR_NO_FIELD_NAME + " {}", radarNo);
        }
        return null;
    }

    private class HNF1BMiscRowMapper implements RowMapper<HNF1BMisc> {
        public HNF1BMisc mapRow(ResultSet rs, int rowNum) throws SQLException {
            HNF1BMisc hnf1BMisc = new HNF1BMisc();

            hnf1BMisc.setId(rs.getLong(ID_FIELD_NAME));
            hnf1BMisc.setRadarNo(rs.getLong(RADAR_NO_FIELD_NAME));
            hnf1BMisc.setRenalCysts(YesNo.getYesNo(rs.getInt(RENAL_CYSTS_FIELD_NAME)));
            hnf1BMisc.setSingleKidney(YesNo.getYesNo(rs.getInt(SINGLE_KIDNEY_FIELD_NAME)));
            hnf1BMisc.setOtherRenalMalformations(YesNo.getYesNo(rs.getInt(OTHER_RENAL_MALFORMATIONS_FIELD_NAME)));
            hnf1BMisc.setOtherRenalMalformationsDetails(rs.getString(OTHER_RENAL_MALFORMATIONS_DETAILS_FIELD_NAME));
            hnf1BMisc.setDiabetes(YesNo.getYesNo(rs.getInt(DIABETES_FIELD_NAME)));
            hnf1BMisc.setAgeAtDiabetesDiagnosis(rs.getInt(AGE_AT_DIABETES_DIAGNOSIS_FIELD_NAME));
            hnf1BMisc.setGout(YesNo.getYesNo(rs.getInt(GOUT_FIELD_NAME)));
            hnf1BMisc.setAgeAtGoutDiagnosis(rs.getInt(AGE_AT_GOUT_DIAGNOSIS_FIELD_NAME));
            hnf1BMisc.setGenitalMalformation(YesNo.getYesNo(rs.getInt(GENITAL_MALFORMATION_FIELD_NAME)));
            hnf1BMisc.setGenitalMalformationDetails(rs.getString(GENITAL_MALFORMATION_DETAILS_FIELD_NAME));

            return hnf1BMisc;
        }
    }
}
