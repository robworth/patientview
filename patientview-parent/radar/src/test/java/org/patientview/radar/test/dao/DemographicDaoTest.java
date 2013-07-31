package org.patientview.radar.test.dao;

import org.patientview.model.Centre;
import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.model.enums.NhsNumberType;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DemographicDaoTest extends BaseDaoTest {

    @Autowired
    private DemographicsDao demographicDao;

    @Autowired
    private DiagnosisDao diagnosisDao;

    @Test
    public void testSaveDemographics() {
        createDemographics("Test", "User");
    }

    @Test
    public void testGetDemographic() {
        Patient patient = createDemographics("Test", "User");
        Patient check = demographicDao.getDemographicsByRadarNumber(patient.getId());

        // Check it's not null
        assertNotNull("Demographics was null", check);

        // Check radar number is correct
        assertEquals("Wrong radar number", patient.getId(), check.getId());
    }

    @Test
    public void testGetDemographics() throws Exception {
        createDemographics("Test", "User");
        createDemographics("Test2", "User2");
        List<Patient> demographics = demographicDao.getDemographics(new DemographicsFilter(), -1, -1);
        assertNotNull("List was null", demographics);
        assertEquals(2, demographics.size());
    }

    @Test
    public void testGetDemographicsPage1() {
        createDemographics("Test", "User");
        createDemographics("Test2", "User2");
        List<Patient> demographics = demographicDao.getDemographics(new DemographicsFilter(), 1, 1);
        assertNotNull(demographics);
        assertTrue(demographics.size() == 1);
    }

    @Test
    public void testGetDemographicsByNhs() throws Exception {

        // Note: this only works if you use uppercase nhs numbers

        createDemographics("Test", "User", "NHS123");
        createDemographics("Test2", "User2", "NHS789");
        DemographicsFilter demographicsFilter = new DemographicsFilter();
        demographicsFilter.addSearchCriteria(DemographicsFilter.UserField.NHSNO.toString(),
                "NHS123");
        List<Patient> demographics = demographicDao.getDemographics(demographicsFilter, -1, -1);
        assertNotNull("List was null", demographics);
        assertEquals(1, demographics.size());
    }

    @Test
    public void testSearchDemographics() {
        addDiagnosisForDemographic(createDemographics("Test", "User"), DiagnosisCode.SRNS_ID);
        addDiagnosisForDemographic(createDemographics("Test2", "User2"), DiagnosisCode.MPGN_ID);
        DemographicsFilter demographicsFilter = new DemographicsFilter();
        demographicsFilter.addSearchCriteria(DemographicsFilter.UserField.DIAGNOSIS.getDatabaseFieldName(), "srns");
        List<Patient> demographics = demographicDao.getDemographics(demographicsFilter, -1, -1);
        assertNotNull(demographics);
        assertTrue(demographics.size() == 1);
    }

    @Test
    public void testGetDemographicsByCentre() throws Exception {
        // Construct centres
        Centre centre = new Centre();
        centre.setId(2L);

        Centre centre2 = new Centre();
        centre2.setId(3L);

        createDemographics("Test", "User", centre, null);
        createDemographics("Test2", "User2", centre, null);
        createDemographics("Test3", "User3", centre2, null);

        // Call DAO
        List<Patient> demographics = demographicDao.getDemographicsByRenalUnit(centre);
        assertNotNull("List was null", demographics);
        assertEquals("Wrong size", 2, demographics.size());
        for (Patient de : demographics) {
            assertTrue("Wrong centre", de.getRenalUnit().getId().equals(2L));
        }
    }

    @Test
    public void testGetSexUnknown() throws Exception {
        Sex sex = demographicDao.getSex(23232L);
        assertNull("Sex not null for unknown", sex);
    }

    @Test
    public void testGetSexes() throws Exception {
        List<Sex> sexes = demographicDao.getSexes();
        assertNotNull("Sexes was null", sexes);
        assertEquals("Wrong size", 3, sexes.size());
    }

    @Test
    public void testGetStatusUnknown() throws Exception {
        Status status = demographicDao.getStatus(23232L);
        assertNull("Status not null for unknown", status);
    }

    @Test
    public void testGetStatuses() throws Exception {
        List<Status> statuses = demographicDao.getStatuses();
        assertNotNull("Statuses was null", statuses);
        assertEquals("Wrong size", 6, statuses.size());
    }

    private void addDiagnosisForDemographic(Patient patient, Long diagnosisCodeId) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setText("Testing");
        diagnosis.setDiagnosisCode(diagnosisDao.getDiagnosisCode(diagnosisCodeId));
        diagnosis.setRadarNumber(patient.getId());
        diagnosisDao.saveDiagnosis(diagnosis);
    }

    private Patient createDemographics(String forename, String surname, Centre centre, String nhsno) {
        Patient patient = new Patient();
        patient.setForename(forename);
        patient.setSurname(surname);
        patient.setNhsNumberType(NhsNumberType.NHS_NUMBER);
        patient.setNhsno(nhsno);
        patient.setRenalUnit(centre);
        demographicDao.saveDemographics(patient);
        assertNotNull(patient.getId());
        return patient;
    }

    private Patient createDemographics(String forename, String surname) {
        return createDemographics(forename, surname, null, null);
    }

    private Patient createDemographics(String forename, String surname, String nhsno) {
        return createDemographics(forename, surname, null, nhsno);
    }
}
