package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;
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
        return jdbcTemplate.query("SELECT * FROM tbl_Clin_Pres WHERE cID = ?", new ClinicalPresentationRowMapper());
    }

    private class DiagnosisRowMapper implements RowMapper<Diagnosis> {
        public Diagnosis mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a diagnosis object and populate the fields
            Diagnosis diagnosis = new Diagnosis();

            diagnosis.setId(resultSet.getLong("dID"));
            diagnosis.setRadarNumber(resultSet.getLong("RADAR_NO"));

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

            /*
           
CREATE TABLE tbl_Diagnosis (
   dID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (dID),
   
    DATE_DIAG TIMESTAMP,
   DIAG int,
   DIAG_TXT varchar(100),
   
   BX_PROVEN_DIAG varchar(1),
   PREPUB_DIAG bit,
   
   GENE_MUT varchar(50),
   GENE_MUT_TEXT varchar(100),
   KARYOTYPE varchar(50),
   KARYOTYPE_OTHER varchar(100),
   DATE_ONSET_RENALDIS TIMESTAMP,
   CONSANGUINITY int,
   FAM_HIST int,

   STEROID_RESIST int,
   DATE_ESRF TIMESTAMP,
);
            */

            return diagnosis;
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

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
