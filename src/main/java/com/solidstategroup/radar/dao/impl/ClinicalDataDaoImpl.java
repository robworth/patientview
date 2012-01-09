package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.ClinicalDataDao;
import com.solidstategroup.radar.model.Phenotype;
import com.solidstategroup.radar.model.sequenced.ClinicalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClinicalDataDaoImpl extends BaseDaoImpl implements ClinicalDataDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalDataDaoImpl.class);

    public ClinicalData getClinicalData(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_ClinicalData WHERE cID = ?", new Object[]{id},
                    new ClinicalDataRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No clinical data found for ID {}", id);
            return null;
        }
    }

    public List<ClinicalData> getClinicalDataByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_ClinicalData WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new ClinicalDataRowMapper());
    }

    public Phenotype getPhenotype(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_PHENOTYPES WHERE pID = ?", new Object[]{id},
                    new PhenotypeDataRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No phenotype found for ID {}", id);
            return null;
        }
    }

    public List<Phenotype> getPhenotypes() {
        return jdbcTemplate.query("SELECT * FROM tbl_PHENOTYPES", new PhenotypeDataRowMapper());
    }

    private class ClinicalDataRowMapper implements RowMapper<ClinicalData> {
        public ClinicalData mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct the clinical data object and set fields
            ClinicalData clinicalData = new ClinicalData();

            clinicalData.setId(resultSet.getLong("cID"));
            clinicalData.setRadarNumber(resultSet.getLong("RADAR_NO"));

            clinicalData.setHeight(resultSet.getDouble("HEIGHT"));
            clinicalData.setWeight(resultSet.getDouble("WEIGHT"));

            clinicalData.setCourseOfDisease(BaseDaoImpl.<ClinicalData.CourseOfDisease>getEnumValue(
                    ClinicalData.CourseOfDisease.class, resultSet.getInt("COURSE_DIS")));

            clinicalData.setSystolicBloodPressure(resultSet.getInt("SYS_BP"));
            clinicalData.setDiastolicBloodPressure(resultSet.getInt("DIA_BP"));
            // There is a map BP (MAP_BP) row but I think we can just use the calculated value all the time?

            // DIALYSIS_REQ - all commented in legacy code
            // DATE_BX - not used in legacy code

            clinicalData.setOedema(resultSet.getBoolean("OEDEMA"));
            clinicalData.setAnaemia(resultSet.getBoolean("ANAEMIA"));

            clinicalData.setHypovalaemia(resultSet.getBoolean("HYPOVAL"));
            clinicalData.setFever(resultSet.getBoolean("FEVER"));
            clinicalData.setInfectionNecessitatingHospitalisation(resultSet.getBoolean("INFECTION"));
            clinicalData.setInfectionDetail(resultSet.getString("INFECTION_DETAIL"));
            // INFECTION_TYPE - not used within legacy code

            clinicalData.setThrombosis(resultSet.getBoolean("THROMBOSIS"));
            // THROMBOSIS_DETAIL - not used within legacy code

            clinicalData.setComplicationThrombosis(resultSet.getBoolean("COMP_THROMBOSIS"));
            clinicalData.setComplicationThrombosisDetail(resultSet.getString("COMP_THROMBOSIS_DETAIL"));
            clinicalData.setPeritonitis(resultSet.getBoolean("PERITONITIS"));

            clinicalData.setPulmonaryOedema(resultSet.getBoolean("PUL_OED"));
            clinicalData.setHypertension(resultSet.getBoolean("HTH_REQ_TMT"));

            clinicalData.setPreceedingInfection(resultSet.getBoolean("PREC_INF"));
            clinicalData.setPreceedingInfectionDetail(resultSet.getString("PREC_INF_DETAIL"));

            clinicalData.setCourseOfDisease(BaseDaoImpl
                    .<ClinicalData.CourseOfDisease>getEnumValue(ClinicalData.CourseOfDisease.class,
                            resultSet.getInt("COURSE_DIS")));

            clinicalData.setDiabetesType(BaseDaoImpl
                    .<ClinicalData.DiabetesType>getEnumValue(ClinicalData.DiabetesType.class,
                            resultSet.getInt("DIABETES")));

            clinicalData.setRash(resultSet.getBoolean("RASH"));
            clinicalData.setRashDetail(resultSet.getString("RASH_DETAIL"));
            clinicalData.setPartialLipodystrophy(resultSet.getBoolean("PART_LIPODYS"));
            clinicalData.setOphthalmoscopy(resultSet.getBoolean("OPTHALM"));
            clinicalData.setOphthalmoscopyDetail(resultSet.getString("OPTHALM_DETAIL"));

            clinicalData.setComments(resultSet.getString("COMMENTS"));

            // Phenotypes
            clinicalData.setPhenotype1(getPhenotype(resultSet.getInt("PHENOTYPE1")));
            clinicalData.setPhenotype2(getPhenotype(resultSet.getInt("PHENOTYPE2")));
            clinicalData.setPhenotype3(getPhenotype(resultSet.getInt("PHENOTYPE3")));
            clinicalData.setPhenotype4(getPhenotype(resultSet.getInt("PHENOTYPE4")));

            // Significant diagnosis
            clinicalData.setSignificantDiagnosis1(resultSet.getString("SIG_DIAG1"));
            clinicalData.setSignificantDiagnosis2(resultSet.getString("SIG_DIAG2"));

            clinicalData.setCkdStage(BaseDaoImpl
                    .<ClinicalData.CkdStage>getEnumValue(ClinicalData.CkdStage.class, resultSet.getInt("CKD_STAGE")));

            clinicalData.setChronicInfection(resultSet.getBoolean("CLIN_EV_CHR_INF"));
            clinicalData.setChronicInfectionDetail(resultSet.getString("CLIN_EV_CHR_INF_DETAIL"));

            // URTICARIA - not used in legacy code

            clinicalData.setPossibleImmunisationTrigger(resultSet.getBoolean("IMMUNIS_TRIGGER"));
            clinicalData.setListedForTransplant(resultSet.getBoolean("TX_LISTED"));

            clinicalData.setSequenceNumber(resultSet.getInt("SEQ_NO"));

            return clinicalData;
        }
    }

    private class PhenotypeDataRowMapper implements RowMapper<Phenotype> {
        public Phenotype mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct phenotype and set ID and description
            Phenotype phenotype = new Phenotype();
            phenotype.setId(resultSet.getLong("pID"));
            phenotype.setDescription(resultSet.getString("pDesc"));
            return phenotype;
        }
    }
}
