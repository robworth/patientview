package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.ClinicalDataDao;
import org.patientview.radar.model.Phenotype;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicalDataDaoImpl extends BaseDaoImpl implements ClinicalDataDao {

    private SimpleJdbcInsert clinicalDataInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalDataDaoImpl.class);

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        clinicalDataInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_clinicalData")
                .usingGeneratedKeyColumns("cID").usingColumns("RADAR_NO", "DATE_CLIN_PIC", "HEIGHT", "WEIGHT",
                        "COURSE_DIS", "SYS_BP", "DIA_BP", "MAP_BP", "DIALYSIS_REQ", "DATE_BX", "OEDEMA", "ANAEMIA",
                        "HYPOVAL", "FEVER", "INFECTION", "INFECTION_DETAIL", "INFECTION_TYPE", "THROMBOSIS",
                        "THROMBOSIS_DETAIL", "COMP_THROMBOSIS", "COMP_THROMBOSIS_DETAIL", "PERITONITIS", "PUL_OED",
                        "HTH_REQ_TMT", "PREC_INF", "PREC_INF_DETAIL", "CLIN_EV_CHR_INF", "CLIN_EV_CHR_INF_DETAIL",
                        "DIABETES", "URTICARIA", "RASH", "RASH_DETAIL", "PART_LIPODYS", "OPTHALM", "OPTHALM_DETAIL",
                        "IMMUNIS_TRIGGER", "COMMENTS", "PHENOTYPE1", "PHENOTYPE2", "PHENOTYPE3", "PHENOTYPE4",
                        "SIG_DIAG1", "SIG_DIAG2", "TX_LISTED", "CKD_STAGE", "SEQ_NO");
    }

    public void saveClinicalDate(final ClinicalData clinicalData) {

        Map<String, Object> clinicalDataMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", clinicalData.getRadarNumber());
                put("DATE_CLIN_PIC", clinicalData.getClinicalPictureDate());
                put("HEIGHT", clinicalData.getHeight());
                put("WEIGHT", clinicalData.getWeight());
                put("COURSE_DIS", clinicalData.getCourseOfDisease() != null ?
                        clinicalData.getCourseOfDisease().getId() : null);
                put("SYS_BP", clinicalData.getSystolicBloodPressure());
                put("DIA_BP", clinicalData.getDiastolicBloodPressure());
                put("MAP_BP", clinicalData.getMeanArterialPressure());
                put("DIALYSIS_REQ", null); // not used according to Dave
                put("DATE_BX", null); // not sued according to Dave
                put("OEDEMA", clinicalData.getOedema());
                put("ANAEMIA", clinicalData.getAnaemia());
                put("HYPOVAL", clinicalData.getHypovalaemia());
                put("FEVER", clinicalData.getFever());
                put("INFECTION", clinicalData.getInfectionNecessitatingHospitalisation());
                put("INFECTION_DETAIL", clinicalData.getInfectionDetail());
                put("INFECTION_TYPE", null); // not used according to David
                put("THROMBOSIS", clinicalData.getThrombosis());
                put("THROMBOSIS_DETAIL", null); // not used according to David
                put("COMP_THROMBOSIS", clinicalData.getComplicationThrombosis());
                put("COMP_THROMBOSIS_DETAIL", clinicalData.getComplicationThrombosisDetail());
                put("PERITONITIS", clinicalData.getPeritonitis());
                put("PUL_OED", clinicalData.getPulmonaryOedema());
                put("HTH_REQ_TMT", clinicalData.getHypertension());
                put("PREC_INF", clinicalData.getPreceedingInfection());
                put("PREC_INF_DETAIL", clinicalData.getPreceedingInfectionDetail());
                put("CLIN_EV_CHR_INF", clinicalData.getChronicInfection());
                put("CLIN_EV_CHR_INF_DETAIL", clinicalData.getChronicInfectionDetail());
                put("DIABETES", clinicalData.getDiabetesType() != null ? clinicalData.getDiabetesType().getId() : null);
                put("URTICARIA", clinicalData.getUrticaria());
                put("RASH", clinicalData.getRash());
                put("RASH_DETAIL", clinicalData.getRashDetail());
                put("PART_LIPODYS", clinicalData.getPartialLipodystrophy());
                put("OPTHALM", clinicalData.getOphthalmoscopy());
                put("OPTHALM_DETAIL", clinicalData.getOphthalmoscopyDetail());
                put("IMMUNIS_TRIGGER", clinicalData.getPossibleImmunisationTrigger());
                put("COMMENTS", clinicalData.getComments());
                put("PHENOTYPE1", clinicalData.getPhenotype1() != null ? clinicalData.getPhenotype1().getId() : null);
                put("PHENOTYPE2", clinicalData.getPhenotype2() != null ? clinicalData.getPhenotype2().getId() : null);
                put("PHENOTYPE3", clinicalData.getPhenotype3() != null ? clinicalData.getPhenotype3().getId() : null);
                put("PHENOTYPE4", clinicalData.getPhenotype4() != null ? clinicalData.getPhenotype4().getId() : null);
                put("SIG_DIAG1", clinicalData.getSignificantDiagnosis1());
                put("SIG_DIAG2", clinicalData.getSignificantDiagnosis2());
                put("TX_LISTED", clinicalData.getListedForTransplant());
                put("CKD_STAGE", clinicalData.getCkdStage() != null ? clinicalData.getCkdStage().getId() : null);
                put("SEQ_NO", clinicalData.getSequenceNumber());
            }
        };

        if (clinicalData.hasValidId()) {
            clinicalDataMap.put("cID", clinicalData.getId());

            namedParameterJdbcTemplate.update("UPDATE tbl_clinicalData " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "DATE_CLIN_PIC = :DATE_CLIN_PIC, " +
                    "HEIGHT = :HEIGHT,  " +
                    "WEIGHT = :WEIGHT, " +
                    "COURSE_DIS = :COURSE_DIS, " +
                    "SYS_BP = :SYS_BP, " +
                    "DIA_BP = :DIA_BP, " +
                    "MAP_BP = :MAP_BP, " +
                    "DIALYSIS_REQ = :DIALYSIS_REQ, " +
                    "DATE_BX = :DATE_BX, " +
                    "OEDEMA = :OEDEMA, " +
                    "ANAEMIA = :ANAEMIA, " +
                    "HYPOVAL = :HYPOVAL, " +
                    "FEVER = :FEVER, " +
                    "INFECTION = :INFECTION, " +
                    "INFECTION_DETAIL = :INFECTION_DETAIL, " +
                    "INFECTION_TYPE = :INFECTION_TYPE, " +
                    "THROMBOSIS = :THROMBOSIS, " +
                    "THROMBOSIS_DETAIL = :THROMBOSIS_DETAIL, " +
                    "COMP_THROMBOSIS = :COMP_THROMBOSIS, " +
                    "COMP_THROMBOSIS_DETAIL = :COMP_THROMBOSIS_DETAIL, " +
                    "PERITONITIS = :PERITONITIS, " +
                    "PUL_OED = :PUL_OED, " +
                    "HTH_REQ_TMT = :HTH_REQ_TMT, " +
                    "PREC_INF = :PREC_INF, " +
                    "PREC_INF_DETAIL = :PREC_INF_DETAIL, " +
                    "CLIN_EV_CHR_INF = :CLIN_EV_CHR_INF, " +
                    "CLIN_EV_CHR_INF_DETAIL = :CLIN_EV_CHR_INF_DETAIL, " +
                    "DIABETES = :DIABETES, " +
                    "URTICARIA = :URTICARIA, " +
                    "RASH = :RASH, " +
                    "RASH_DETAIL = :RASH_DETAIL, " +
                    "PART_LIPODYS = :PART_LIPODYS, " +
                    "OPTHALM = :OPTHALM, " +
                    "OPTHALM_DETAIL = :OPTHALM_DETAIL, " +
                    "IMMUNIS_TRIGGER = :IMMUNIS_TRIGGER, " +
                    "COMMENTS = :COMMENTS, " +
                    "PHENOTYPE1 = :PHENOTYPE1, " +
                    "PHENOTYPE2 = :PHENOTYPE2, " +
                    "PHENOTYPE3 = :PHENOTYPE3, " +
                    "PHENOTYPE4 = :PHENOTYPE4, " +
                    "SIG_DIAG1 = :SIG_DIAG1, " +
                    "SIG_DIAG2 = :SIG_DIAG2, " +
                    "TX_LISTED = :TX_LISTED, " +
                    "CKD_STAGE = :CKD_STAGE, " +
                    "SEQ_NO = :SEQ_NO " +
                    "WHERE cID = :cID;", clinicalDataMap);
        } else {
            Number id = clinicalDataInsert.executeAndReturnKey(clinicalDataMap);
            clinicalData.setId(id.longValue());
        }
    }

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
        return jdbcTemplate.query("SELECT * FROM tbl_ClinicalData WHERE RADAR_NO = ?",
                new Object[]{radarNumber}, new ClinicalDataRowMapper());
    }

    public ClinicalData getFirstClinicalDataByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_ClinicalData WHERE RADAR_NO = ? AND SEQ_NO = 1",
                    new Object[]{radarNumber}, new ClinicalDataRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

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

            clinicalData.setClinicalPictureDate(resultSet.getDate("DATE_CLIN_PIC"));

            clinicalData.setHeight(getDoubleWithNullCheck("HEIGHT", resultSet));
            clinicalData.setWeight(getDoubleWithNullCheck("WEIGHT", resultSet));

            clinicalData.setCourseOfDisease(BaseDaoImpl.<ClinicalData.CourseOfDisease>getEnumValue(
                    ClinicalData.CourseOfDisease.class, resultSet.getInt("COURSE_DIS")));

            clinicalData.setSystolicBloodPressure(getIntegerWithNullCheck("SYS_BP", resultSet));
            clinicalData.setDiastolicBloodPressure(getIntegerWithNullCheck("DIA_BP", resultSet));
            // There is a map BP (MAP_BP) row but I think we can just use the calculated value all the time?

            // DIALYSIS_REQ - all commented in legacy code
            // DATE_BX - not used in legacy code

            clinicalData.setOedema(getBooleanWithNullCheck("OEDEMA", resultSet));
            clinicalData.setAnaemia(getBooleanWithNullCheck("ANAEMIA", resultSet));
            clinicalData.setHypovalaemia(getBooleanWithNullCheck("HYPOVAL", resultSet));
            clinicalData.setFever(getBooleanWithNullCheck("FEVER", resultSet));
            clinicalData.setInfectionNecessitatingHospitalisation(getBooleanWithNullCheck("INFECTION", resultSet));
            clinicalData.setInfectionDetail(resultSet.getString("INFECTION_DETAIL"));
            // INFECTION_TYPE - not used within legacy code

            clinicalData.setThrombosis(getBooleanWithNullCheck("THROMBOSIS", resultSet));
            // THROMBOSIS_DETAIL - not used within legacy code

            clinicalData.setComplicationThrombosis(getBooleanWithNullCheck("COMP_THROMBOSIS", resultSet));
            clinicalData.setComplicationThrombosisDetail(resultSet.getString("COMP_THROMBOSIS_DETAIL"));
            clinicalData.setPeritonitis(getBooleanWithNullCheck("PERITONITIS", resultSet));

            clinicalData.setPulmonaryOedema(getBooleanWithNullCheck("PUL_OED", resultSet));
            clinicalData.setHypertension(resultSet.getBoolean("HTH_REQ_TMT"));

            clinicalData.setPreceedingInfection(getBooleanWithNullCheck("PREC_INF", resultSet));
            clinicalData.setPreceedingInfectionDetail(resultSet.getString("PREC_INF_DETAIL"));

            clinicalData.setCourseOfDisease(BaseDaoImpl
                    .<ClinicalData.CourseOfDisease>getEnumValue(ClinicalData.CourseOfDisease.class,
                            resultSet.getInt("COURSE_DIS")));

            clinicalData.setDiabetesType(BaseDaoImpl
                    .<ClinicalData.DiabetesType>getEnumValue(ClinicalData.DiabetesType.class,
                            resultSet.getInt("DIABETES")));

            clinicalData.setRash(getBooleanWithNullCheck("RASH", resultSet));
            clinicalData.setRashDetail(resultSet.getString("RASH_DETAIL"));
            clinicalData.setPartialLipodystrophy(getBooleanWithNullCheck("PART_LIPODYS", resultSet));
            clinicalData.setOphthalmoscopy(getBooleanWithNullCheck("OPTHALM", resultSet));
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

            clinicalData.setChronicInfection(getBooleanWithNullCheck("CLIN_EV_CHR_INF", resultSet));
            clinicalData.setChronicInfectionDetail(resultSet.getString("CLIN_EV_CHR_INF_DETAIL"));

            clinicalData.setUrticaria(getBooleanWithNullCheck("URTICARIA", resultSet));

            clinicalData.setPossibleImmunisationTrigger(getBooleanWithNullCheck("IMMUNIS_TRIGGER", resultSet));
            clinicalData.setListedForTransplant(getBooleanWithNullCheck("TX_LISTED", resultSet));

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
