package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.Karotype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DiagnosisDaoImpl extends BaseDaoImpl implements DiagnosisDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosisDaoImpl.class);

    private UtilityDao utilityDao;

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

            diagnosis.setAgeAtDiagnosis(resultSet.getInt("AGE_AT_DIAG"));
            diagnosis.setHeightAtDiagnosis(resultSet.getDouble("HEIGHT_FIRST_VISIT"));

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
            diagnosis.setRelativeWithDisease1(utilityDao.getRelative(resultSet.getLong("REL2")));
            diagnosis.setRelativeWithDiseaseRadarNumber1(resultSet.getInt("REL2_RADAR"));
            diagnosis.setRelativeWithDisease1(utilityDao.getRelative(resultSet.getLong("REL3")));
            diagnosis.setRelativeWithDiseaseRadarNumber1(resultSet.getInt("REL3_RADAR"));
            diagnosis.setRelativeWithDisease1(utilityDao.getRelative(resultSet.getLong("REL4")));
            diagnosis.setRelativeWithDiseaseRadarNumber1(resultSet.getInt("REL4_RADAR"));
            diagnosis.setRelativeWithDisease1(utilityDao.getRelative(resultSet.getLong("REL5")));
            diagnosis.setRelativeWithDiseaseRadarNumber1(resultSet.getInt("REL5_RADAR"));
            diagnosis.setRelativeWithDisease1(utilityDao.getRelative(resultSet.getLong("REL6")));
            diagnosis.setRelativeWithDiseaseRadarNumber1(resultSet.getInt("REL6_RADAR"));

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
                    BaseDaoImpl.getEnumValue(Diagnosis.YesNo.class, resultSet.getInt("BX_PROVEN_DIAG")));
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
            // There is a KARYOTYPE_OTHER varchar(100) but it isn't referenced in legacy code

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
