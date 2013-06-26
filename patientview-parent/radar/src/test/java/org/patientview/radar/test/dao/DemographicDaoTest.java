package org.patientview.radar.test.dao;

import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.Status;
import org.patientview.radar.model.enums.NhsNumberType;
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
        Demographics demographics = createDemographics("Test", "User");
        Demographics check = demographicDao.getDemographicsByRadarNumber(demographics.getId());

        // Check it's not null
        assertNotNull("Demographics was null", check);

        // Check radar number is correct
        assertEquals("Wrong radar number", demographics.getId(), check.getId());
    }

    @Test
    public void testGetDemographics() throws Exception {
        createDemographics("Test", "User");
        createDemographics("Test2", "User2");
        List<Demographics> demographics = demographicDao.getDemographics(new DemographicsFilter(), -1, -1);
        assertNotNull("List was null", demographics);
        assertEquals(2, demographics.size());
    }

    @Test
    public void testGetDemographicsPage1() {
        createDemographics("Test", "User");
        createDemographics("Test2", "User2");
        List<Demographics> demographics = demographicDao.getDemographics(new DemographicsFilter(), 1, 1);
        assertNotNull(demographics);
        assertTrue(demographics.size() == 1);
    }

    @Test
    public void testGetDemographicsByNhs() throws Exception {

        // Note: this only works if you use uppercase nhs numbers

        createDemographics("Test", "User", "NHS123");
        createDemographics("Test2", "User2", "NHS789");
        DemographicsFilter demographicsFilter = new DemographicsFilter();
        demographicsFilter.addSearchCriteria(DemographicsFilter.UserField.NHS_NO.toString(),
                "NHS123");
        List<Demographics> demographics = demographicDao.getDemographics(demographicsFilter, -1, -1);
        assertNotNull("List was null", demographics);
        assertEquals(1, demographics.size());
    }

    @Test
    public void testSearchDemographics() {
        addDiagnosisForDemographic(createDemographics("Test", "User"), DiagnosisCode.SRNS_ID);
        addDiagnosisForDemographic(createDemographics("Test2", "User2"), DiagnosisCode.MPGN_ID);
        DemographicsFilter demographicsFilter = new DemographicsFilter();
        demographicsFilter.addSearchCriteria(DemographicsFilter.UserField.DIAGNOSIS.getDatabaseFieldName(), "srns");
        List<Demographics> demographics = demographicDao.getDemographics(demographicsFilter, -1, -1);
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
        List<Demographics> demographics = demographicDao.getDemographicsByRenalUnit(centre);
        assertNotNull("List was null", demographics);
        assertEquals("Wrong size", 2, demographics.size());
        for (Demographics de : demographics) {
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

    private void addDiagnosisForDemographic(Demographics demographics, Long diagnosisCodeId) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setText("Testing");
        diagnosis.setDiagnosisCode(diagnosisDao.getDiagnosisCode(diagnosisCodeId));
        diagnosis.setRadarNumber(demographics.getId());
        diagnosisDao.saveDiagnosis(diagnosis);
    }

    private Demographics createDemographics(String forename, String surname, Centre centre, String nhsno) {
        Demographics demographics = new Demographics();
        demographics.setForename(forename);
        demographics.setSurname(surname);
        demographics.setNhsNumberType(NhsNumberType.NHS_NUMBER);
        demographics.setNhsNumber(nhsno);
        demographics.setRenalUnit(centre);
        demographicDao.saveDemographics(demographics);
        assertNotNull(demographics.getId());
        return demographics;
    }

    private Demographics createDemographics(String forename, String surname) {
        return createDemographics(forename, surname, null, null);
    }

    private Demographics createDemographics(String forename, String surname, String nhsno) {
        return createDemographics(forename, surname, null, nhsno);
    }
}
