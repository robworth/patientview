package org.patientview.radar.test;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Ignore;
import org.patientview.common.test.BaseTestPvDbSchema;
import org.patientview.model.Centre;
import org.patientview.model.Country;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.Hospitalisation;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.patientview.radar.model.sequenced.Pathology;
import org.patientview.radar.model.sequenced.Therapy;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Ignore
public class TestPvDbSchema extends BaseTestPvDbSchema {

    @Inject
    private DataSource dataSource;

    private SimpleJdbcInsert clinPresInsert;

    private SimpleJdbcInsert clinicalDataInsert;

    private SimpleJdbcInsert consultantsInsert;

    private SimpleJdbcInsert countryInsert;

    private SimpleJdbcInsert unitInsert;

    private SimpleJdbcInsert diagCodeInsert;

    private SimpleJdbcInsert hospitalisationInsert;

    private SimpleJdbcInsert pathologyInsert;

    private SimpleJdbcInsert therapyInsert;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void testDbCreate() throws Exception {
        super.testDbCreate();

        // populate database with xml
        //populateData();
    }

    private void populateData() throws Exception {

        // skip db stuff of
        if (!isLocalTestEnvironment()) {
            // Once we've got the tables created populate them with data - clean insert will delete all data first
            DatabaseDataSourceConnection databaseDataSourceConnection = new DatabaseDataSourceConnection(dataSource);

            // Set the database factory as in http://www.dbunit.org/faq.html#DefaultDataTypeFactory
            DatabaseConfig config = databaseDataSourceConnection.getConfig();

            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "`?`");

            // Construct dataset
            XmlDataSet dataSet = new XmlDataSet(readFileFromClasspath("dataset.xml"));

            // Insert, cleanly (remove everything first)
            DatabaseOperation.CLEAN_INSERT.execute(databaseDataSourceConnection, dataSet);

            // Have to close the database connection
            databaseDataSourceConnection.close();
        }
    }

    protected String getTestNhsNo() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i<=9; i++) {
            stringBuffer.append((int)(Math.random() * 10));
        }
        return stringBuffer.toString();
    }

    public void createClinPres(final Long id, final String pres) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("cID", id);
                put("CLIN_PRES", pres);
            }
        };
        clinPresInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Clin_Pres");
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
        clinicalDataInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_clinicalData");
        clinicalDataInsert.execute(clinicalDataMap);
    }

    public void createClinicalTestData() throws Exception {
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
        clinicalData.setPartialLipodystrophy(false);
        clinicalData.setPartialLipodystrophy(false);
        clinicalData.setPossibleImmunisationTrigger(false);
        clinicalData.setSequenceNumber(1);
        createClinicalData(clinicalData);

    }

    public void saveConsultant(final Consultant consultant) throws Exception {
        Map<String, Object> consultantMap = new HashMap<String, Object>() {
            {
                put("cSNAME", consultant.getSurname());
                put("cFNAME", consultant.getForename());
                put("cCentre", consultant.getCentre() == null ? null : consultant.getCentre().getId());
                put("cID", consultant.getId());
            }
        };
        consultantsInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Consultants");
        consultantsInsert.execute(consultantMap);
    }

    public void createConsultant() throws Exception {
        Centre centre = new Centre();
        centre.setId(1l);
        Consultant consultant = new Consultant();
        consultant.setCentre(centre);
        consultant.setSurname("ADALAT");
        consultant.setForename("Dr Shazia");
        consultant.setId(1l);
        saveConsultant(consultant);

        consultant = new Consultant();
        consultant.setSurname("ANBU");
        consultant.setForename("Dr A Theodore");
        consultant.setId(4l);
        saveConsultant(consultant);

        consultant = new Consultant();
        consultant.setCentre(centre);
        consultant.setSurname("ARNEIL");
        consultant.setForename("Professor Gavin");
        consultant.setId(5l);
        saveConsultant(consultant);

        consultant = new Consultant();
        consultant.setCentre(centre);
        consultant.setSurname("AMELI");
        consultant.setForename("Sonbol");
        consultant.setId(134l);
        saveConsultant(consultant);

        centre.setId(4l);
        consultant = new Consultant();
        consultant.setCentre(centre);
        consultant.setSurname("RAMAGE");
        consultant.setForename("Dr Ian");
        consultant.setId(92l);
        saveConsultant(consultant);

        consultant = new Consultant();
        consultant.setCentre(centre);
        consultant.setSurname("SMITH");
        consultant.setForename("Dr Graham");
        consultant.setId(109l);
        saveConsultant(consultant);

        consultant = new Consultant();
        consultant.setCentre(centre);
        consultant.setSurname("Van der VOORT");
        consultant.setForename("Dr Judith");
        consultant.setId(121l);
        saveConsultant(consultant);
    }

    public void saveCountry(final Country country) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("cID", country.getId());
                put("cName", country.getName());
            }
        };
        countryInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Country");
        countryInsert.execute(map);

    }

    public void createCountryData() throws Exception {
        Country country = new Country();
        country.setName("GB and Ireland");
        country.setId(1l);
        saveCountry(country);

        country = new Country();
        country.setName("Outside GB and Ireland");
        country.setId(2l);
        saveCountry(country);

    }

    public void saveUnit(final long id, final String unitCode, final String name,final String shortName, final String sourceType) throws Exception {
        Map<String, Object> consultantMap = new HashMap<String, Object>() {
            {
                put("id", id);
                put("unitcode", unitCode);
                put("name", name);
                put("shortname", shortName);
                put("sourceType", sourceType);
                put("specialty_id", 1);
                put("country", 1);
            }
        };
        unitInsert = new SimpleJdbcInsert(dataSource).withTableName("unit");
        unitInsert.execute(consultantMap);
    }

    public void createUnit() throws Exception {
        saveUnit(1, "1", "group1", "group1", "radargroup");
        saveUnit(2, "2", "group2", "group2", "radargroup");
        saveUnit(3, "58", "group58", "group58", "radargroup");
        saveUnit(4, "RWM51", "Cardiff,  Children's Hospital for Wales", "Cardiff", "renalunit");
        saveUnit(5, "5", "group5", "group5", "renalunit");
    }

    public void saveDiagCode(final long id, final String dcDesc, final String dcAbbr) throws Exception {
        Map<String, Object> consultantMap = new HashMap<String, Object>() {
            {
                put("dcID", id);
                put("dcDesc", dcDesc);
                put("dcAbbr", dcAbbr);
            }
        };
        diagCodeInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_DiagCode");
        diagCodeInsert.execute(consultantMap);
    }

    public void createDiagCode() throws Exception {
        saveDiagCode(1, "Steroid Resistant Nephrotic Syndrome", "SRNS");
        saveDiagCode(2, "Mesangiocappillary Glomerulonephritis", "MPGN/DDD");
    }

    public void saveHospitalisation(final Hospitalisation hospitalisation) throws Exception {
        Map<String, Object> consultantMap = new HashMap<String, Object>() {
            {
                put("hID", hospitalisation.getId());
                put("RADAR_NO", hospitalisation.getRadarNumber());
                put("DATE_ADMIT", hospitalisation.getDateOfAdmission());
                put("DATE_DISCHARGE", hospitalisation.getDateOfDischarge());
                put("REASON_ADMIT", hospitalisation.getReason());
                put("COMMENT", hospitalisation.getComments());
            }
        };
        hospitalisationInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Hospitalisation");
        hospitalisationInsert.execute(consultantMap);
    }

    public void createHospitalisation() throws Exception {
        Hospitalisation hospitalisation = new Hospitalisation();
        hospitalisation.setId(1l);
        hospitalisation.setRadarNumber(250l);
        hospitalisation.setDateOfAdmission(sdf.parse("2010-01-04 00:00:00"));
        hospitalisation.setDateOfDischarge(sdf.parse("2010-01-04 00:00:00"));
        hospitalisation.setReason("Gout");
        hospitalisation.setComments("very ill");
        saveHospitalisation(hospitalisation);

        hospitalisation = new Hospitalisation();
        hospitalisation.setId(2l);
        hospitalisation.setRadarNumber(246l);
        hospitalisation.setDateOfAdmission(sdf.parse("2010-01-12 00:00:00"));
        hospitalisation.setReason("test");
        hospitalisation.setComments("test");
        saveHospitalisation(hospitalisation);

        hospitalisation = new Hospitalisation();
        hospitalisation.setId(3l);
        hospitalisation.setRadarNumber(244l);
        hospitalisation.setDateOfAdmission(sdf.parse("2010-05-04 00:00:00"));
        hospitalisation.setDateOfDischarge(sdf.parse("2010-05-13 00:00:00"));
        hospitalisation.setReason("Broken Heart");
        hospitalisation.setComments("Fell in love with a married lady, husband was jealous, unrequieted " +
                "love - result broken heart Treatment conservative & distraction");
        saveHospitalisation(hospitalisation);

    }

    public void savePathology(final Pathology pathology) {
        Map<String, Object> pathologyMap = new HashMap<String, Object>() {
            {
                put("pID", pathology.getId());
                put("RADAR_NO", pathology.getRadarNumber());
            }
        };

        pathologyInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Pathology");
        pathologyInsert.execute(pathologyMap);
    }

    public void createPathology() throws Exception {
        Pathology pathology = new Pathology();
        pathology.setId(2l);
        pathology.setRadarNumber(238l);
        savePathology(pathology);
    }

    public void saveTherapy(final Therapy therapy) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", therapy.getRadarNumber());
                put("tID", therapy.getId());
            }
        };
        therapyInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Therapy");
        therapyInsert.execute(therapMap);
    }

    public void createTherapy() throws Exception {
        Therapy therapy = new Therapy();
        therapy.setId(6l);
        therapy.setRadarNumber(6l);
        saveTherapy(therapy);
    }

}
