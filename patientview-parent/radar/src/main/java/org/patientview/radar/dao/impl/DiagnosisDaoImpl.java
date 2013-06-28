package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.ClinicalPresentation;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Karotype;
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

public class DiagnosisDaoImpl extends BaseDaoImpl implements DiagnosisDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosisDaoImpl.class);

    private UtilityDao utilityDao;
    private SimpleJdbcInsert diagnosisInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        diagnosisInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_diagnosis")
                .usingGeneratedKeyColumns("dID").usingColumns("RADAR_NO", "DATE_DIAG", "DIAG", "DIAG_TXT",
                        "AGE_AT_DIAG", "HEIGHT_FIRST_VISIT",
                        "BX_PROVEN_DIAG", "PREPUB_DIAG", "CLIN_PRES", "CLIN_PRES_B", "GENE_MUT", "GENE_MUT_TEXT",
                        "KARYOTYPE", "KARYOTYPE_OTHER", "DATE_ONSET_RENALDIS", "CONSANGUINITY", "FAM_HIST",
                        "REL1", "REL1_RADAR", "REL2", "REL2_RADAR", "REL3", "REL3_RADAR", "REL4", "REL4_RADAR",
                        "REL5", "REL5_RADAR", "REL6", "REL6_RADAR", "SIG_DIAG1", "SIG_DIAG2", "STEROID_RESIST",
                        "DATE_ESRF", "MUTATION_1", "MUTATION_1S", "MUTATION_2", "MUTATION_2S", "MUTATION_3",
                        "MUTATION_3S", "MUTATION_4", "MUTATION_4S", "MUTATION_5", "MUTATION_5S", "MUTATION_6",
                        "MUTATION_6S", "MUTATION_7", "MUTATION_7S", "MUTATION_8", "MUTATION_8S", "MUTATION_9",
                        "MUTATION_9S");
    }


    public void saveDiagnosis(final Diagnosis diagnosis) {
        Map<String, Object> diagnosisMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", diagnosis.getRadarNumber());
                put("DATE_DIAG", diagnosis.getBiopsyDate());
                put("DIAG", diagnosis.getDiagnosisCode() != null ? diagnosis.getDiagnosisCode().getId() : null);
                put("DIAG_TXT", diagnosis.getText());
                put("AGE_AT_DIAG", diagnosis.getAgeAtDiagnosis());
                put("HEIGHT_FIRST_VISIT", diagnosis.getHeightAtDiagnosis());
                put("BX_PROVEN_DIAG", diagnosis.getBiopsyProvenDiagnosis() != null ?
                        diagnosis.getBiopsyProvenDiagnosis().getId() : null);
                put("PREPUB_DIAG", diagnosis.isPrepubertalAtDiagnosis());
                put("CLIN_PRES", diagnosis.getClinicalPresentationA() != null ?
                        diagnosis.getClinicalPresentationA().getId() : null);
                put("CLIN_PRES_B", diagnosis.getClinicalPresentationB() != null ?
                        diagnosis.getClinicalPresentationB().getId() :
                        null);
                put("GENE_MUT", null); // this is no longer used according to DavidH
                put("GENE_MUT_TEXT", diagnosis.getOtherGeneMutation());
                put("KARYOTYPE", diagnosis.getKarotype() != null ? diagnosis.getKarotype().getId() : null);
                put("KARYOTYPE_OTHER", diagnosis.getKaroTypeOtherText());
                put("DATE_ONSET_RENALDIS", diagnosis.getOnsetSymptomsDate());
                put("CONSANGUINITY", diagnosis.getParentalConsanguinity() != null ?
                        diagnosis.getParentalConsanguinity().getId() : null);
                put("FAM_HIST", diagnosis.getFamilyHistory() != null ? diagnosis.getFamilyHistory().getId()
                        : null);
                put("REL1", diagnosis.getRelativeWithDisease1() != null ?
                        diagnosis.getRelativeWithDisease1().getId() : null);
                put("REL1_RADAR", diagnosis.getRelativeWithDiseaseRadarNumber1());
                put("REL2", diagnosis.getRelativeWithDisease2() != null ?
                        diagnosis.getRelativeWithDisease2().getId() : null);
                put("REL2_RADAR", diagnosis.getRelativeWithDiseaseRadarNumber2());
                put("REL3", diagnosis.getRelativeWithDisease3() != null ?
                        diagnosis.getRelativeWithDisease3().getId() : null);
                put("REL3_RADAR", diagnosis.getRelativeWithDiseaseRadarNumber3());
                put("REL4", diagnosis.getRelativeWithDisease4() != null ?
                        diagnosis.getRelativeWithDisease4().getId() : null);
                put("REL4_RADAR", diagnosis.getRelativeWithDiseaseRadarNumber4());
                put("REL5", diagnosis.getRelativeWithDisease5() != null ?
                        diagnosis.getRelativeWithDisease5().getId() : null);
                put("REL5_RADAR", diagnosis.getRelativeWithDiseaseRadarNumber5());
                put("REL6", diagnosis.getRelativeWithDisease6() != null ?
                        diagnosis.getRelativeWithDisease6().getId() : null);
                put("REL6_RADAR", diagnosis.getRelativeWithDiseaseRadarNumber6());
                put("SIG_DIAG1", diagnosis.getSignificantDiagnosis1());
                put("SIG_DIAG2", diagnosis.getSignificantDiagnosis2());
                put("STEROID_RESIST", diagnosis.getSteroidResistance() != null ?
                        diagnosis.getSteroidResistance().getId() : null);
                put("DATE_ESRF", diagnosis.getEsrfDate());
                put("MUTATION_1", diagnosis.getMutationYorN1() != null ? diagnosis.getMutationYorN1().getId()
                        : null);
                put("MUTATION_1S", diagnosis.getMutationSorSN1() != null ? diagnosis.getMutationSorSN1().getId()
                        : null);
                put("MUTATION_2", diagnosis.getMutationYorN2() != null ? diagnosis.getMutationYorN2().getId() : null);
                put("MUTATION_2S", diagnosis.getMutationSorSN2() != null ? diagnosis.getMutationSorSN2().getId()
                        : null);
                put("MUTATION_3", diagnosis.getMutationYorN3() != null ? diagnosis.getMutationYorN3().getId() : null);
                put("MUTATION_3S", diagnosis.getMutationSorSN3() != null ? diagnosis.getMutationSorSN3().getId()
                        : null);
                put("MUTATION_4", diagnosis.getMutationYorN4() != null ? diagnosis.getMutationYorN4().getId() : null);
                put("MUTATION_4S", diagnosis.getMutationSorSN4() != null ? diagnosis.getMutationSorSN4().getId()
                        : null);
                put("MUTATION_5", diagnosis.getMutationYorN5() != null ? diagnosis.getMutationYorN5().getId() : null);
                put("MUTATION_5S", diagnosis.getMutationSorSN5() != null ? diagnosis.getMutationSorSN5().getId()
                        : null);
                put("MUTATION_6", diagnosis.getMutationYorN6() != null ? diagnosis.getMutationYorN6().getId() : null);
                put("MUTATION_6S", diagnosis.getMutationSorSN6() != null ? diagnosis.getMutationSorSN6().getId()
                        : null);
                put("MUTATION_7", diagnosis.getMutationYorN7() != null ? diagnosis.getMutationYorN7().getId() : null);
                put("MUTATION_7S", diagnosis.getMutationSorSN7() != null ? diagnosis.getMutationSorSN7().getId()
                        : null);
                put("MUTATION_8", diagnosis.getMutationYorN8() != null ? diagnosis.getMutationYorN8().getId() : null);
                put("MUTATION_8S", diagnosis.getMutationSorSN8() != null ? diagnosis.getMutationSorSN8().getId()
                        : null);
                put("MUTATION_9", diagnosis.getMutationYorN9() != null ? diagnosis.getMutationYorN9().getId() : null);
                put("MUTATION_9S", diagnosis.getMutationSorSN9() != null ? diagnosis.getMutationSorSN9().getId()
                        : null);
            }
        };


        if (diagnosis.hasValidId()) {
            diagnosisMap.put("dID", diagnosis.getId()) ;

            namedParameterJdbcTemplate.update("UPDATE tbl_diagnosis " +
                    "SET RADAR_NO = :RADAR_NO," +
                    " DATE_DIAG = :DATE_DIAG," +
                    " DIAG = :DIAG," +
                    " DIAG_TXT = :DIAG_TXT," +
                    " AGE_AT_DIAG = :AGE_AT_DIAG," +
                    " HEIGHT_FIRST_VISIT = :HEIGHT_FIRST_VISIT," +
                    " BX_PROVEN_DIAG = :BX_PROVEN_DIAG," +
                    " PREPUB_DIAG = :PREPUB_DIAG," +
                    " CLIN_PRES = :CLIN_PRES," +
                    " CLIN_PRES_B = :CLIN_PRES_B," +
                    " GENE_MUT = :GENE_MUT," +
                    " GENE_MUT_TEXT = :GENE_MUT_TEXT," +
                    " KARYOTYPE = :KARYOTYPE," +
                    " KARYOTYPE_OTHER = :KARYOTYPE_OTHER," +
                    " DATE_ONSET_RENALDIS = :DATE_ONSET_RENALDIS," +
                    " CONSANGUINITY = :CONSANGUINITY," +
                    " FAM_HIST = :FAM_HIST," +
                    " REL1 = :REL1," +
                    " REL1_RADAR = :REL1_RADAR," +
                    " REL2 = :REL2," +
                    " REL2_RADAR = :REL2_RADAR," +
                    " REL3 = :REL3," +
                    " REL3_RADAR = :REL3_RADAR," +
                    " REL4 = :REL4," +
                    " REL4_RADAR = :REL4_RADAR," +
                    " REL5 = :REL5," +
                    " REL5_RADAR = :REL5_RADAR," +
                    " REL6 = :REL6," +
                    " REL6_RADAR = :REL6_RADAR," +
                    " SIG_DIAG1 = :SIG_DIAG1," +
                    " SIG_DIAG2 = :SIG_DIAG2," +
                    " STEROID_RESIST = :STEROID_RESIST," +
                    " DATE_ESRF = :DATE_ESRF," +
                    " MUTATION_1 = :MUTATION_1," +
                    " MUTATION_1S = :MUTATION_1S," +
                    " MUTATION_2 = :MUTATION_2," +
                    " MUTATION_2S = :MUTATION_2S," +
                    " MUTATION_3 = :MUTATION_3," +
                    " MUTATION_3S = :MUTATION_3S," +
                    " MUTATION_4 = :MUTATION_4," +
                    " MUTATION_4S = :MUTATION_4S," +
                    " MUTATION_5 = :MUTATION_5," +
                    " MUTATION_5S = :MUTATION_5S," +
                    " MUTATION_6 = :MUTATION_6," +
                    " MUTATION_6S = :MUTATION_6S," +
                    " MUTATION_7 = :MUTATION_7," +
                    " MUTATION_7S = :MUTATION_7S," +
                    " MUTATION_8 = :MUTATION_8," +
                    " MUTATION_8S = :MUTATION_8S," +
                    " MUTATION_9 = :MUTATION_9," +
                    " MUTATION_9S = :MUTATION_9S " +
                    "WHERE dID = :dID;", diagnosisMap);

        } else {
            Number id = diagnosisInsert.executeAndReturnKey(diagnosisMap);
            diagnosis.setId(id.longValue());
        }
    }

    public Diagnosis getDiagnosis(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Diagnosis WHERE dID = ?", new Object[]{id},
                    new DiagnosisRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get diagnosis result for ID {}", id);
            return null;
        }
    }

    public Diagnosis getDiagnosisByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate
                    .queryForObject("SELECT * FROM tbl_Diagnosis WHERE RADAR_NO = ?", new Object[]{radarNumber},
                            new DiagnosisRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get diagnosis result for radar number {}", radarNumber);
            return null;
        }
    }

    public DiagnosisCode getDiagnosisCode(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_DiagCode WHERE dcID = ?", new Object[]{id},
                    new DiagnosisCodeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get diagnosis result for ID {}", id);
            return null;
        }
    }

    public List<DiagnosisCode> getDiagnosisCodes() {
        return jdbcTemplate.query("SELECT * FROM tbl_DiagCode", new DiagnosisCodeRowMapper());
    }

    public ClinicalPresentation getClinicalPresentation(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Clin_Pres WHERE cID = ?", new Object[]{id},
                    new ClinicalPresentationRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not find clinical presentation with ID {}", id);
            return null;
        }
    }

    public List<ClinicalPresentation> getClinicalPresentations() {
        return jdbcTemplate.query("SELECT * FROM tbl_Clin_Pres", new ClinicalPresentationRowMapper());
    }

    public Karotype getKarotype(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Karyotype WHERE kID = ?", new Object[]{id},
                    new KarotypeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No record for Karyotype with ID {}", id);
            return null;
        }
    }

    public List<Karotype> getKarotypes() {
        return jdbcTemplate.query("SELECT * FROM tbl_Karyotype", new KarotypeRowMapper());
    }

    private class DiagnosisRowMapper implements RowMapper<Diagnosis> {
        public Diagnosis mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a diagnosis object and populate the fields
            Diagnosis diagnosis = new Diagnosis();

            diagnosis.setId(resultSet.getLong("dID"));
            diagnosis.setRadarNumber(resultSet.getLong("RADAR_NO"));

            // Set the diagnosis code
            diagnosis.setDiagnosisCode(getDiagnosisCode(resultSet.getLong("DIAG")));
            diagnosis.setText(resultSet.getString("DIAG_TXT"));

            diagnosis.setBiopsyDate(resultSet.getDate("DATE_DIAG"));

            int age = resultSet.getInt("AGE_AT_DIAG");
            if (!resultSet.wasNull()) {
              diagnosis.setAgeAtDiagnosis(age);
            }

            double height = resultSet.getDouble("HEIGHT_FIRST_VISIT");
            if (! resultSet.wasNull()) {
                 diagnosis.setHeightAtDiagnosis(height);
            }

            // Null check
            long clinicalPresentationA = resultSet.getLong("CLIN_PRES");
            if (clinicalPresentationA > 0) {
                diagnosis.setClinicalPresentationA(getClinicalPresentation(clinicalPresentationA));
            }

            // Null check the values
            long clinicalPresentationB = resultSet.getLong("CLIN_PRES_B");
            if (clinicalPresentationB > 0) {
                diagnosis.setClinicalPresentationB(getClinicalPresentation(clinicalPresentationB));
            }

            // Relatives etc
            diagnosis.setRelativeWithDisease1(utilityDao.getRelative(resultSet.getLong("REL1")));
            diagnosis.setRelativeWithDiseaseRadarNumber1(resultSet.getInt("REL1_RADAR"));
            diagnosis.setRelativeWithDisease2(utilityDao.getRelative(resultSet.getLong("REL2")));
            diagnosis.setRelativeWithDiseaseRadarNumber2(resultSet.getInt("REL2_RADAR"));
            diagnosis.setRelativeWithDisease3(utilityDao.getRelative(resultSet.getLong("REL3")));
            diagnosis.setRelativeWithDiseaseRadarNumber3(resultSet.getInt("REL3_RADAR"));
            diagnosis.setRelativeWithDisease4(utilityDao.getRelative(resultSet.getLong("REL4")));
            diagnosis.setRelativeWithDiseaseRadarNumber4(resultSet.getInt("REL4_RADAR"));
            diagnosis.setRelativeWithDisease5(utilityDao.getRelative(resultSet.getLong("REL5")));
            diagnosis.setRelativeWithDiseaseRadarNumber5(resultSet.getInt("REL5_RADAR"));
            diagnosis.setRelativeWithDisease6(utilityDao.getRelative(resultSet.getLong("REL6")));
            diagnosis.setRelativeWithDiseaseRadarNumber6(resultSet.getInt("REL6_RADAR"));

            // Mutation stuff
            diagnosis.setMutationYorN1(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_1")));
            diagnosis.setMutationYorN2(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_2")));
            diagnosis.setMutationYorN3(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_3")));
            diagnosis.setMutationYorN4(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_4")));
            diagnosis.setMutationYorN5(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_5")));
            diagnosis.setMutationYorN6(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_6")));
            diagnosis.setMutationYorN7(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_7")));
            diagnosis.setMutationYorN8(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_8")));
            diagnosis.setMutationYorN9(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationYorN.class, resultSet.getInt("MUTATION_9")));

            // Mutation S
            diagnosis.setMutationSorSN1(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_1S")));
            diagnosis.setMutationSorSN2(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_2S")));
            diagnosis.setMutationSorSN3(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_3S")));
            diagnosis.setMutationSorSN4(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_4S")));
            diagnosis.setMutationSorSN5(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_5S")));
            diagnosis.setMutationSorSN6(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_6S")));
            diagnosis.setMutationSorSN7(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_7S")));
            diagnosis.setMutationSorSN8(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_8S")));
            diagnosis.setMutationSorSN9(
                    BaseDaoImpl.getEnumValue(Diagnosis.MutationSorSN.class, resultSet.getInt("MUTATION_9S")));

            diagnosis.setSignificantDiagnosis1(resultSet.getString("SIG_DIAG1"));
            diagnosis.setSignificantDiagnosis2(resultSet.getString("SIG_DIAG2"));

            // This is a bit in DB, lets hope it works
            diagnosis.setBiopsyProvenDiagnosis(
                    BaseDaoImpl.getEnumValue(Diagnosis.BiopsyDiagnosis.class, getIntegerWithNullCheck("BX_PROVEN_DIAG"
                            , resultSet)));
            diagnosis.setPrepubertalAtDiagnosis(resultSet.getBoolean("PREPUB_DIAG"));

            // From what I can tell this GENE_MUT varchar(50)
            // Are all commented in current code and used to be used instead of diagnosis code

            // Set extra gene mutation text
            diagnosis.setOtherGeneMutation(resultSet.getString("GENE_MUT_TEXT"));

            // As per usual we get a long but this is a varchar, links to other table though
            long karotypeId = resultSet.getLong("KARYOTYPE");
            if (karotypeId > 0) {
                diagnosis.setKarotype(getKarotype(karotypeId));
            }
            diagnosis.setKaroTypeOtherText(resultSet.getString("KARYOTYPE_OTHER"));

            diagnosis.setOnsetSymptomsDate(resultSet.getDate("DATE_ONSET_RENALDIS"));

            diagnosis.setParentalConsanguinity(
                    BaseDaoImpl.getEnumValue(Diagnosis.YesNo.class, resultSet.getInt("CONSANGUINITY")));
            diagnosis.setFamilyHistory(BaseDaoImpl.getEnumValue(Diagnosis.YesNo.class, resultSet.getInt("FAM_HIST")));

            diagnosis.setSteroidResistance(
                    BaseDaoImpl.getEnumValue(Diagnosis.SteroidResistance.class, resultSet.getInt("STEROID_RESIST")));

            diagnosis.setEsrfDate(resultSet.getDate("DATE_ESRF"));

            return diagnosis;
        }
    }

    private class DiagnosisCodeRowMapper implements RowMapper<DiagnosisCode> {
        public DiagnosisCode mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct object and set values
            DiagnosisCode diagnosisCode = new DiagnosisCode();
            diagnosisCode.setId(resultSet.getLong("dcID"));
            diagnosisCode.setDescription(resultSet.getString("dcDesc"));
            diagnosisCode.setAbbreviation(resultSet.getString("dcAbbr"));
            return diagnosisCode;
        }
    }

    private class ClinicalPresentationRowMapper implements RowMapper<ClinicalPresentation> {
        public ClinicalPresentation mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct Clinical presentation object and set fields
            ClinicalPresentation clinicalPresentation = new ClinicalPresentation();
            clinicalPresentation.setId(resultSet.getLong("cID"));
            clinicalPresentation.setName(resultSet.getString("CLIN_PRES"));
            return clinicalPresentation;
        }
    }

    private class KarotypeRowMapper implements RowMapper<Karotype> {
        public Karotype mapRow(ResultSet resultSet, int i) throws SQLException {
            // This is an easy one
            Karotype karotype = new Karotype();
            karotype.setId(resultSet.getLong("kID"));
            karotype.setDescription(resultSet.getString("KARYOTYPE"));
            return karotype;
        }
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
