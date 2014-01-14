/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.test;

import org.junit.Ignore;
import org.patientview.model.Centre;
import org.patientview.model.Country;
import org.patientview.model.enums.SourceType;
import org.patientview.radar.dao.IssueDao;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.Hospitalisation;
import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;
import org.patientview.radar.model.Issue;
import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.PlasmapheresisExchangeUnit;
import org.patientview.radar.model.enums.IssuePriority;
import org.patientview.radar.model.enums.IssueStatus;
import org.patientview.radar.model.enums.IssueType;
import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.enums.RemissionAchieved;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.patientview.radar.model.sequenced.Pathology;
import org.patientview.radar.model.sequenced.Relapse;
import org.patientview.radar.model.sequenced.Therapy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Ignore
public class TestDataHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataHelper.class);

    @Inject
    private DataSource dataSource;


    protected JdbcTemplate jdbcTemplate;

    @Inject
    private IssueDao issueDao;

    private SimpleJdbcInsert simpleJdbcInsert;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void createClinPres(final Long id, final String pres) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("cID", id);
                put("CLIN_PRES", pres);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Clin_Pres");
        simpleJdbcInsert.execute(map);

    }


    public void createClinPresData() {
        try {
            createClinPres(1l, "Nephrotic");
            createClinPres(2l, "Nephritic");
            createClinPres(3l, "Haematuria");
            createClinPres(4l, "Proteinuria");
            createClinPres(99l, "other");
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_Clin_Pres table : " + e.getMessage());
        }
    }


    public void saveClinicalData(final ClinicalData clinicalData) throws Exception {
        Map<String, Object> clinicalDataMap = new HashMap<String, Object>() {
            {
                put("cID", clinicalData.getId());
                put("RADAR_NO", clinicalData.getRadarNumber());
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_clinicalData");
        simpleJdbcInsert.execute(clinicalDataMap);
    }

    public void createClinicalData() {
        try  {
            ClinicalData clinicalData = new ClinicalData();
            clinicalData.setId(135l);
            clinicalData.setRadarNumber(244l);
            saveClinicalData(clinicalData);

            clinicalData = new ClinicalData();
            clinicalData.setId(136l);
            clinicalData.setRadarNumber(245l);
            saveClinicalData(clinicalData);
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_clinicalData table : " + e.getMessage());
        }
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
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Consultants");
        simpleJdbcInsert.execute(consultantMap);
    }

    public void createConsultant() {
        try {
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
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_Consultants table : " + e.getMessage());
        }
    }

    public void saveCountry(final Country country) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("cID", country.getId());
                put("cName", country.getName());
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Country");
        simpleJdbcInsert.execute(map);

    }

    public void createCountryData() {
        try {
            Country country = new Country();
            country.setName("GB and Ireland");
            country.setId(1l);
            saveCountry(country);

            country = new Country();
            country.setName("Outside GB and Ireland");
            country.setId(2l);
            saveCountry(country);
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_Country table : " + e.getMessage());
        }
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
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("unit");
        simpleJdbcInsert.execute(consultantMap);
    }

    public void createUnit() {
        try {
            saveUnit(1, "1", "group1", "group1", "radargroup");
            saveUnit(2, "2", "group2", "group2", "radargroup");
            saveUnit(3, "58", "group58", "group58", "radargroup");
            saveUnit(4, "RWM51", "Cardiff,  Children's Hospital for Wales", "Cardiff", "renalunit");
            saveUnit(5, "5", "group5", "group5", "renalunit");
        } catch (Exception e) {
            LOGGER.debug("error create test data to unit table : " + e.getMessage());
        }
    }

    public void saveDiagCode(final long id, final String dcDesc, final String dcAbbr) throws Exception {
        Map<String, Object> consultantMap = new HashMap<String, Object>() {
            {
                put("dcID", id);
                put("dcDesc", dcDesc);
                put("dcAbbr", dcAbbr);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_DiagCode");
        simpleJdbcInsert.execute(consultantMap);
    }

    public void createDiagCode() {
        try {
            saveDiagCode(1, "Steroid Resistant Nephrotic Syndrome ", "SRNS");
            saveDiagCode(2, "Mesangiocappillary Glomerulonephritis ", "MPGN/DDD");
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_DiagCode table : " + e.getMessage());
        }
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
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Hospitalisation");
        simpleJdbcInsert.execute(consultantMap);
    }

    public void createHospitalisation() {
        try {
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
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_Hospitalisation table : " + e.getMessage());
        }


    }

    public void savePathology(final Pathology pathology) {
        Map<String, Object> pathologyMap = new HashMap<String, Object>() {
            {
                put("pID", pathology.getId());
                put("RADAR_NO", pathology.getRadarNumber());
            }
        };

        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Pathology");
        simpleJdbcInsert.execute(pathologyMap);
    }

    public void createPathology() {
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
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Therapy");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createTherapy() {
        Therapy therapy = new Therapy();
        therapy.setId(6l);
        therapy.setRadarNumber(6l);
        saveTherapy(therapy);
    }

    public void savePrdCode(final String code, final String term) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("ERA_EDTA_PRD_code", code);
                put("ERA_EDTA_primaryRenalDiagnosisTerm", term);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("rdr_prd_code");
        simpleJdbcInsert.execute(map);
    }

    public void createPrdCode() {
        try {
            savePrdCode("code1", "code1");
            savePrdCode("code2", "code2");
            savePrdCode("code3", "code3");
            savePrdCode("code4", "code4");
            savePrdCode("code5", "code5");
            savePrdCode("code6", "code6");
        } catch (Exception e) {
            LOGGER.debug("error create test data to rdr_prd_code table : " + e.getMessage());
        }
    }

    public void saveDiagnosisMapping(final String group, final String code, final int ordering) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("workingGroup", group);
                put("PRDCode", code);
                put("ordering", ordering);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("rdr_diagnosis_mapping");
        simpleJdbcInsert.execute(map);
    }

    public void createDiagnosisMapping() {
        try {
            saveDiagnosisMapping("3", "code1", 1);
            saveDiagnosisMapping("3", "code2", 2);
            saveDiagnosisMapping("2", "code3", 3);
            saveDiagnosisMapping("2", "code4", 4);
            saveDiagnosisMapping("1", "code5", 6);
            saveDiagnosisMapping("1", "code6", 5);
        } catch (Exception e) {
            LOGGER.debug("error create test data to rdr_diagnosis_mapping table : " + e.getMessage());
        }
    }

    public void saveRelapse(final Relapse relapse) throws Exception {
        Map<String, Object> relapseMap = new HashMap<String, Object>() {
            {
                put("relID", relapse.getId());
                put("RADAR_NO", relapse.getRadarNumber());
                put("DATE_ONSET_RELAP", relapse.getDateOfRelapse());
                put("RELAP_TX_NAT", relapse.getTransplantedNative() != null ?
                        relapse.getTransplantedNative().getId() : null);
                put("TRIG_VIRAL", relapse.getViralTrigger());
                put("TRIG_IMMUN", relapse.getImmunisationTrigger());
                put("TRIG_OTHER", relapse.getOtherTrigger());
                put("RELAP_DRUG_1", relapse.getDrug1());
                put("RELAP_DRUG_2", relapse.getDrug2());
                put("RELAP_DRUG_3", relapse.getDrug3());
                put("REMISS_ACHIEVE", relapse.getRemissionAchieved() != null ?
                        relapse.getRemissionAchieved().getId() : null);
                put("DATE_REMISSION", relapse.getDateOfRemission());
                put("SEQ_NO", relapse.getSequenceNumber());
            }
        };

        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Relapse");
        simpleJdbcInsert.execute(relapseMap);
    }

    public void createRelapse() {
        try {
            Relapse relapse = new Relapse();
            relapse.setId(1l);
            relapse.setRadarNumber(237l);
            relapse.setDateOfRelapse(sdf.parse("2009-10-20 00:00:00"));
            relapse.setTransplantedNative(KidneyTransplantedNative.TRANSPLANTED);
            relapse.setViralTrigger("test");
            relapse.setImmunisationTrigger("test");
            relapse.setDrug1("test1");
            relapse.setDrug2("test2");
            relapse.setDrug3("test3");
            relapse.setRemissionAchieved(RemissionAchieved.COMPLETE);
            relapse.setDateOfRemission(sdf.parse("2009-10-30 00:00:00"));
            saveRelapse(relapse);

            relapse = new Relapse();
            relapse.setId(3l);
            relapse.setRadarNumber(237l);
            relapse.setDateOfRelapse(sdf.parse("2009-11-11 00:00:00"));
            relapse.setTransplantedNative(KidneyTransplantedNative.TRANSPLANTED);
            relapse.setViralTrigger("test");
            relapse.setDrug1("test");
            relapse.setRemissionAchieved(RemissionAchieved.COMPLETE);
            relapse.setDateOfRemission(sdf.parse("2009-10-30 00:00:00"));
            saveRelapse(relapse);

            relapse = new Relapse();
            relapse.setId(4l);
            relapse.setRadarNumber(237l);
            relapse.setDateOfRelapse(sdf.parse("2009-06-09 00:00:00"));
            relapse.setTransplantedNative(KidneyTransplantedNative.NATIVE);
            relapse.setViralTrigger("test");
            relapse.setImmunisationTrigger("test");
            relapse.setOtherTrigger("test");
            relapse.setDrug1("test1");
            relapse.setDrug2("test2");
            relapse.setDrug3("test3");
            relapse.setRemissionAchieved(RemissionAchieved.NONE);
            relapse.setDateOfRemission(sdf.parse("2009-06-10 00:00:00"));
            saveRelapse(relapse);
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_Relapse table : " + e.getMessage());
        }

    }

    public void savePlasmapheresis(final Plasmapheresis plasmapheresis) {
        Map<String, Object> plasmapheresisMap = new HashMap<String, Object>() {
            {
                put("plID", plasmapheresis.getId());
                put("RADAR_NO", plasmapheresis.getRadarNumber());
                put("DATE_START_PLASMAPH", plasmapheresis.getStartDate());
                put("DATE_STOP_PLASMAPH", plasmapheresis.getEndDate());
                put("NO_EXCH_PLASMAPH", plasmapheresis.getPlasmapheresisExchanges() != null ?
                        plasmapheresis.getPlasmapheresisExchanges().getId() : null);
                put("RESPONSE_TO_PLASMA", plasmapheresis.getResponse() != null ? plasmapheresis.getResponse().getId() :
                        null);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_RRT_PLASMA");
        simpleJdbcInsert.execute(plasmapheresisMap);
    }

    public void createPlasmapheresis() {
        try {
            PlasmapheresisExchangeUnit plasmapheresisExchangeUnit = new PlasmapheresisExchangeUnit();
            plasmapheresisExchangeUnit.setId(1l);
            PlasmapheresisExchangeUnit plasmapheresisExchangeUnit2 = new PlasmapheresisExchangeUnit();
            plasmapheresisExchangeUnit2.setId(2l);
            PlasmapheresisExchangeUnit plasmapheresisExchangeUnit3 = new PlasmapheresisExchangeUnit();
            plasmapheresisExchangeUnit3.setId(3l);

            Plasmapheresis plasmapheresis = new Plasmapheresis();
            plasmapheresis.setId(1l);
            plasmapheresis.setRadarNumber(218l);
            plasmapheresis.setStartDate(sdf.parse("2009-07-01 00:00:00"));
            plasmapheresis.setEndDate(sdf.parse("2009-07-08 00:00:00"));
            plasmapheresis.setPlasmapheresisExchanges(plasmapheresisExchangeUnit3);
            plasmapheresis.setResponse(RemissionAchieved.NONE);
            savePlasmapheresis(plasmapheresis);

            plasmapheresis = new Plasmapheresis();
            plasmapheresis.setId(2l);
            plasmapheresis.setRadarNumber(218l);
            plasmapheresis.setStartDate(sdf.parse("2009-08-02 00:00:00"));
            plasmapheresis.setEndDate(sdf.parse("2009-08-04 00:00:00"));
            plasmapheresis.setPlasmapheresisExchanges(plasmapheresisExchangeUnit2);
            plasmapheresis.setResponse(RemissionAchieved.PARTIAL);
            savePlasmapheresis(plasmapheresis);

            plasmapheresis = new Plasmapheresis();
            plasmapheresis.setId(8l);
            plasmapheresis.setRadarNumber(218l);
            plasmapheresis.setStartDate(sdf.parse("2009-08-06 00:00:00"));
            plasmapheresis.setEndDate(sdf.parse("2009-08-07 00:00:00"));
            plasmapheresis.setPlasmapheresisExchanges(plasmapheresisExchangeUnit3);
            plasmapheresis.setResponse(RemissionAchieved.NONE);
            savePlasmapheresis(plasmapheresis);

            plasmapheresis = new Plasmapheresis();
            plasmapheresis.setId(10l);
            plasmapheresis.setRadarNumber(218l);
            plasmapheresis.setStartDate(sdf.parse("2009-07-12 00:00:00"));
            plasmapheresis.setEndDate(sdf.parse("2009-07-23 00:00:00"));
            plasmapheresis.setPlasmapheresisExchanges(plasmapheresisExchangeUnit2);
            plasmapheresis.setResponse(RemissionAchieved.COMPLETE);
            savePlasmapheresis(plasmapheresis);

            plasmapheresis = new Plasmapheresis();
            plasmapheresis.setId(11l);
            plasmapheresis.setRadarNumber(218l);
            plasmapheresis.setStartDate(sdf.parse("2009-07-24 00:00:00"));
            plasmapheresis.setPlasmapheresisExchanges(plasmapheresisExchangeUnit);
            plasmapheresis.setResponse(RemissionAchieved.PARTIAL);
            savePlasmapheresis(plasmapheresis);
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_RRT_PLASMA table : " + e.getMessage());
        }
    }

    public void savePlasmaLu(final long id, final String desc) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("exID", id);
                put("exDesc", desc);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_RRT_PLASMA_LU");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createPlasmaLu() {
        savePlasmaLu(1l, "Daily");
        savePlasmaLu(2l, "x5/wk");
        savePlasmaLu(3l, "x4/wk");
        savePlasmaLu(4l, "x3/wk");
        savePlasmaLu(5l, "x2/wk");
        savePlasmaLu(6l, "x1/wk");
        savePlasmaLu(7l, "x1/2wks");
        savePlasmaLu(8l, "x1/4wks");
    }

    public void createIssue() {
        try {
            Issue issue = new Issue();
            issue.setType(IssueType.ADDITION);
            issue.setPage("Consultants page");
            issue.setDateLogged(sdf.parse("2012-02-16 00:00:00"));
            issue.setDescription("Its not working");
            issue.setComments("Make it work");
            issue.setPriority(IssuePriority.LOW);
            issue.setStatus(IssueStatus.OPEN);
            issueDao.saveIssue(issue);

            issue = new Issue();
            issue.setType(IssueType.ADDITION);
            issue.setPage("Admins page");
            issue.setDateLogged(sdf.parse("2012-02-16 00:00:00"));
            issue.setDescription("Its still not working");
            issue.setComments("Make it work");
            issue.setPriority(IssuePriority.LOW);
            issue.setStatus(IssueStatus.CLOSED);
            issueDao.saveIssue(issue);

        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_IssueTracker table : " + e.getMessage());
        }
    }

    public void saveKarotype(final long id, final String type) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("kID", id);
                put("KARYOTYPE", type);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Karyotype");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createKarotype() {
        saveKarotype(1l, "XX");
        saveKarotype(2l, "XY");
        saveKarotype(9l, "Not done");
        saveKarotype(8l, "Other");
    }

    public void saveDiagnosis(final long id, final long radarNo) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("dID", id);
                put("RADAR_NO", radarNo);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Diagnosis");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createDiagnosis() {
        saveDiagnosis(114l, 239l);
        saveDiagnosis(117l, 242l);
    }

    public void saveLabData(final long id, final long radarNo) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("labID", id);
                put("RADAR_NO", radarNo);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_LabData");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createLabData() {
        saveLabData(16l, 236l);
        saveLabData(125l, 236l);
    }

    public void saveRRTTreatment(final long id, final long radarNo) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("tID", id);
                put("RADAR_NO", radarNo);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_RRT_TREATMENT");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createRRTTreatment() {
        saveRRTTreatment(2l, 218l);
        saveRRTTreatment(76l, 237l);
        saveRRTTreatment(78l, 237l);
        saveRRTTreatment(10l, 237l);
    }

    public void createRRTModality() {

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO tbl_rrt_modality VALUES ");
        sb.append("(").append(1).append(",").append("'Haemodialysis'").append(",").append(7).append("),");
        sb.append("(").append(2).append(",").append("'Haemofiltration'").append(",").append(7).append("),");
        sb.append("(").append(3).append(",").append("'Haemodiafiltration'").append(",").append(7).append("),");
        sb.append("(").append(4).append(",").append("'Haemodialysis > 4 days per week / daily'").append(",").append(7).append("),");
        sb.append("(").append(5).append(",").append("'Ultrafiltration'").append(",").append(7).append("),");
        sb.append("(").append(9).append(",").append("'Haemodialysis – type unknown'").append(",").append(7).append("),");
        sb.append("(").append(10).append(",").append("'CAPD connect'").append(",").append(8).append("),");
        sb.append("(").append(11).append(",").append("'CAPD disconnect'").append(",").append(8).append("),");
        sb.append("(").append(12).append(",").append("'Cycling PD >= 6  nights/wk dry '").append(",").append(8).append("),");
        sb.append("(").append(13).append(",").append("'Cycling PD < 6 nights /wk dry'").append(",").append(8).append("),");
        sb.append("(").append(14).append(",").append("'Cycling PD >= 6  nights/wk wet (day dwell)'").append(",").append(8).append("),");
        sb.append("(").append(15).append(",").append("'Cycling PD < 6 nights /wk wet (day dwell)'").append(",").append(8).append("),");
        sb.append("(").append(16).append(",").append("'Assisted Cycling PD >=6  nights/wk dry'").append(",").append(8).append("),");
        sb.append("(").append(17).append(",").append("'Assisted Cycling PD >=6  nights/wk wet (day dwell)'").append(",").append(8).append("),");
        sb.append("(").append(19).append(",").append("'Peritoneal dialysis – type unknown'").append(",").append(8).append("),");
        sb.append("(").append(20).append(",").append("'Transplant ; Cadaver donor'").append(",").append(7).append(");");

        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(sb.toString());
    }

    public void saveTransplant(final long id, final long radarNo, final Date date) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("trID", id);
                put("RADAR_NO", radarNo);
                put("DATE_TRANSPLANT", date);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Transplant");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createTransplant() {
        try {
            saveTransplant(2l, 219l, sdf.parse("2009-09-21 00:00:00"));
            saveTransplant(4l, 219l, null);
        } catch (Exception e) {
            LOGGER.debug("error create test data to tbl_Transplant table : " + e.getMessage());
        }

    }

    public void saveTransplantModality(final long id, final String desc) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("trID", id);
                put("trDesc", desc);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_TRANSPLANT_MODALITY");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createTransplantModality() {
        saveTransplantModality(20l, "Tx : Cadaver donor");
        saveTransplantModality(21l, "Tx : Live related – sibling");
    }

    public void saveTransplantReject(final long id, final long trId) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("recID", id);
                put("trID", trId);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Transplant_Reject");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createTransplantReject() {
        saveTransplantReject(2l, 12l);
        saveTransplantReject(15l, 25l);
        saveTransplantReject(16l, 25l);
    }

    public void saveImmunsupTreatment(final ImmunosuppressionTreatment immunosuppressionTreatment) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("tID", immunosuppressionTreatment.getId());
                put("RADAR_NO", immunosuppressionTreatment.getRadarNumber());
                put("IMMUNSUP_DRUG", immunosuppressionTreatment.getImmunosuppression().getId());
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_IMMUNSUP_TREATMENT");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createImmunsupTreatment() {
        Immunosuppression immunosuppression = new Immunosuppression();
        immunosuppression.setId(8l);

        ImmunosuppressionTreatment immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setId(14l);
        immunosuppressionTreatment.setRadarNumber(218l);
        immunosuppressionTreatment.setImmunosuppression(immunosuppression);
        saveImmunsupTreatment(immunosuppressionTreatment);

        immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setId(1l);
        immunosuppressionTreatment.setRadarNumber(218l);
        immunosuppressionTreatment.setImmunosuppression(immunosuppression);
        saveImmunsupTreatment(immunosuppressionTreatment);

        immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setId(7l);
        immunosuppressionTreatment.setRadarNumber(218l);
        immunosuppressionTreatment.setImmunosuppression(immunosuppression);
        saveImmunsupTreatment(immunosuppressionTreatment);

        immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setId(16l);
        immunosuppressionTreatment.setRadarNumber(218l);
        immunosuppressionTreatment.setImmunosuppression(immunosuppression);
        saveImmunsupTreatment(immunosuppressionTreatment);

        immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setId(17l);
        immunosuppressionTreatment.setRadarNumber(218l);
        immunosuppressionTreatment.setImmunosuppression(immunosuppression);
        saveImmunsupTreatment(immunosuppressionTreatment);

    }

    public void createImmunoSupp() {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO tbl_ImmunoSupp VALUES ");
        sb.append("(").append(8).append(",").append("'Cyclophosphamide'").append(",").append(4).append("),");
        sb.append("(").append(10).append(",").append("'Rituximab'").append(",").append(5).append(");");

        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(sb.toString());
    }

    public void savePhenotypes(final long id, final String desc) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("pID", id);
                put("pDesc", desc);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_PHENOTYPES");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createPhenotypes() {
        savePhenotypes(1, "Auto-immune disease");
        savePhenotypes(4, "Blindness");
    }

    public void saveSpecialty(final long id, final String context, final String name, final String description) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("id", id);
                put("context", context);
                put("description", description);
                put("name", name);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("specialty");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createSpecialty() {
        saveSpecialty(1, "testcontext", "testcontext", "test");
    }

    public void saveSex(final long id, final String type) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("sID", id);
                put("sType", type);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Sex");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createSex() {
        saveSex(1, "Male");
        saveSex(2, "Female");
        saveSex(9, "Not specified");
    }

    public void saveStatus(final long id, final String desc, final String sAbbrev) {
        Map<String, Object> therapMap = new HashMap<String, Object>() {
            {
                put("sID", id);
                put("sDesc", desc);
                put("sAbbrev", sAbbrev);
            }
        };
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Status");
        simpleJdbcInsert.execute(therapMap);
    }

    public void createStatus() {
        saveStatus(1, "Current", "Curr");
        saveStatus(2, "Transferred to adult unit", "Adult");
        saveStatus(3, "Transferred to other Paediatric unit", "Paed other");
        saveStatus(4, "Discharged", "Disch");
        saveStatus(5, "Moved abroad", "Abroad");
        saveStatus(6, "Died", "Died");
    }

    public void setPatientSource(Long id, SourceType sourceType) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("UPDATE patient SET sourceType = '" + sourceType.getName() + "' WHERE id = " + id );
    }

}
