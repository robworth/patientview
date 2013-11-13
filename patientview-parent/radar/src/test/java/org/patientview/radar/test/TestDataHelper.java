package org.patientview.radar.test;

import org.patientview.model.Patient;
import org.patientview.radar.dao.ClinicalDataDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.patientview.radar.model.user.PatientUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: james@solidstategroup.com
 * Date: 04/11/13
 * Time: 10:16
 */
@Component
public class TestDataHelper {

    @Inject
    private static DataSource dataSource;

    protected JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private static SimpleJdbcInsert clinPresInsert;

    private static SimpleJdbcInsert clinicalDataInsert;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Inject
    private ClinicalDataDao clinicalDataDao;

    @Inject
    private UserDao userDao;

    @Inject
    private UtilityDao utilityDao;

    static {
        // Initialise a simple JDBC insert for tbl_Clin_Pres table.
        clinPresInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Clin_Pres");

        // Initialise a simple JDBC insert for tbl_Clin_Pres table.
        clinicalDataInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_clinicalData");

    }


    public void createClinPres(final Long id, final String pres) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("cID", id);
                put("DATE_LAB_RES", pres);
            }
        };
        clinPresInsert.execute(map);

    }

    public void createClinPresData() throws Exception {
        createClinPres(1l, "Nephrotic");
        createClinPres(2l, "Nephritic");
        createClinPres(3l, "Haematuria");
        createClinPres(4l, "Proteinuria");
        createClinPres(99l, "other");
    }


    public void createClinicalData(final ClinicalData clinicalData) throws Exception {
        Map<String, Object> clinicalDataMap = new HashMap<String, Object>() {
            {
                put("cID", clinicalData.getId());
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

        clinicalDataInsert.execute(clinicalDataMap);
    }

    public void createClinical() throws Exception {
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setId(135l);
        clinicalData.setRadarNumber(244l);
        clinicalData.setClinicalPictureDate(sdf.parse("2001-06-01 00:00:00"));
        clinicalData.setHeight(138.0);
        clinicalData.setWeight(38.00);
        clinicalData.setSystolicBloodPressure(128);
        clinicalData.setDiastolicBloodPressure(80);
        clinicalData.setOedema(true);
        clinicalData.setHypovalaemia(false);
        clinicalData.setFever(false);
        clinicalData.setHypertension(false);
        clinicalData.setPreceedingInfection(false);
        clinicalData.setChronicInfection(false);
        clinicalData.setRash(false);
        clinicalData.setRash(false);
    }


    public PatientUser createUserInPatientView(String nhsNo, String unitCode) throws Exception {
        // Creates a user in patient with a mapping!!
        PatientUser patientUser = new PatientUser();
        patientUser.setEmail(nhsNo + "@patientview.com");
        patientUser.setUsername("TestUser");
        patientUser.setName("Test User");

        userDao.createPatientViewUser(patientUser);
        return userDao.getPatientViewUser(nhsNo);

    }

    public void cleanTestData(List<Patient> patients, String unitCode) {
        // -- Clean up test (No Transaction Manager)
        for (Patient patient : patients) {
            utilityDao.deletePatientViewMapping(patient.getNhsno());
           // utilityDao.deletePatientViewUser(patientUser.getUsername());
        }

        utilityDao.deleteUnit(unitCode);
    }



}
