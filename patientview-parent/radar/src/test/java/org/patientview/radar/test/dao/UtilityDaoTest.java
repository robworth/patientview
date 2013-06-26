package org.patientview.radar.test.dao;

import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.Country;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Ethnicity;
import org.patientview.radar.model.Relative;
import org.patientview.radar.model.enums.NhsNumberType;
import org.patientview.radar.model.filter.ConsultantFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UtilityDaoTest extends BaseDaoTest {

    @Autowired
    private UtilityDao utilityDao;

    @Autowired
    private DiagnosisDao diagnosisDao;

    @Autowired
    private DemographicsDao demographicDao;

    @Test
    public void testGetCentre() {
        Centre centre = utilityDao.getCentre(4L);
        assertNotNull("Centre is null", centre);
        assertEquals("Centre ID is wrong", new Long(4), centre.getId());
        assertEquals("Name is wrong", "Cardiff,  Children's Hospital for Wales", centre.getName());
        assertEquals("Abbreviation is wrong", "Cardiff", centre.getAbbreviation());

        // Check country
        assertNotNull("Country is null", centre.getCountry());
        assertEquals("Country name is wrong", "GB and Ireland", centre.getCountry().getName());
    }

    @Test
    public void testGetCentres() {
        List<Centre> centres = utilityDao.getCentres();
        assertNotNull("Centres list is null", centres);
    }

    @Test
    public void testGetConsultant() {
        Consultant consultant = utilityDao.getConsultant(4L);
        assertNotNull("Consultant is null");
        assertEquals("Surname is wrong", "ANBU", consultant.getSurname());
        assertEquals("Forename is wrong", "Dr A Theodore", consultant.getForename());
        assertNull("Centre is not null", consultant.getCentre());
    }

    @Test
    public void testGetConsultants() {
        List<Consultant> consultants = utilityDao.getConsultants(new ConsultantFilter(), -1, -1);
        assertNotNull("Consultants list is null", consultants);
    }

    @Test
    public void testGetConsultantsByCentre() throws Exception {
        Centre centre = new Centre();
        centre.setId(4L);
        List<Consultant> consultants = utilityDao.getConsultantsByCentre(centre);
        assertEquals(consultants.size(), 3);
    }

    @Test
    public void testGetConsultantsPage1() {
        List<Consultant> consultants = utilityDao.getConsultants(new ConsultantFilter(), 1, 1);
        assertNotNull(consultants);
        assertTrue(consultants.size() == 1);
    }

    @Test
    public void testSearchConsultants() {
        ConsultantFilter consultantFlter = new ConsultantFilter();
        consultantFlter.addSearchCriteria(ConsultantFilter.UserField.FORENAME.getDatabaseFieldName(), "Sonbol");
        List<Consultant> consultants = utilityDao.getConsultants(consultantFlter, -1, -1);
        assertNotNull(consultants);
        assertTrue(consultants.size() > 0);
    }

    @Test
    public void testSaveNewConsultant() throws Exception {
        Consultant consultant = new Consultant();
        consultant.setSurname("test_surname");
        consultant.setForename("test_forename");

        Centre centre = new Centre();
        centre.setId((long) 2);
        consultant.setCentre(centre);

        utilityDao.saveConsultant(consultant);

        assertTrue("Saved consultant doesn't have an ID", consultant.getId() > 0);

        consultant = utilityDao.getConsultant(consultant.getId());
        assertNotNull("Saved consultant was null on getting from DAO", consultant);
    }

    @Test
    public void testSaveExistingConsultant() throws Exception {
        // have to make a user first
        Consultant consultant = utilityDao.getConsultant(1);
        consultant.setSurname("test_surname");

        utilityDao.saveConsultant(consultant);

        consultant = utilityDao.getConsultant(consultant.getId());
        assertTrue("Consultant surname has not been updated", consultant.getSurname().equals("test_surname"));
    }

    @Test
    public void deleteConsultant() throws Exception {
        utilityDao.deleteConsultant(utilityDao.getConsultant(1));

        Consultant consultant;
        try {
            consultant = utilityDao.getConsultant(1);
        } catch (Exception e) {
            consultant = null;
        }

        assertNull("Consultant was not deleted", consultant);
    }

    @Test
    public void testGetConsultantWithCentre() {
        Consultant consultant = utilityDao.getConsultant(5L);
        assertNotNull("Consultant is null");
        assertEquals("Surname is wrong", "ARNEIL", consultant.getSurname());
        assertEquals("Forename is wrong", "Professor Gavin", consultant.getForename());
        assertNotNull("Centre is null", consultant.getCentre());
        assertEquals("Centre is wrong", "group1", consultant.getCentre().getAbbreviation());
    }

    @Test
    public void testGetCountries() {
        List<Country> countries = utilityDao.getCountries();
        assertNotNull("Countries list is null", countries);
    }

    @Test
    public void testGetEthnicities() {
        List<Ethnicity> ethnicities = utilityDao.getEthnicities();
        assertNotNull("Ethnicities list is null", ethnicities);
    }

    @Test
    public void testGetEthnicityUnknown() throws Exception {
        Ethnicity ethnicity = utilityDao.getEthnicityByCode("asasda");
        assertNull("Ethnicity not null", ethnicity);
    }

    @Test
    public void testGetRelatives() {
        List<Relative> relatives = utilityDao.getRelatives();
        assertNotNull("Relatives list is null", relatives);
    }

    @Test
    public void testGetPatientCountPerUnitByDiagnosisCode() throws Exception {
        Centre centre = new Centre();
        centre.setId(3L);

        addDiagnosisForDemographic(createDemographics("Test", "User", centre), DiagnosisCode.SRNS_ID);
        addDiagnosisForDemographic(createDemographics("Test2", "User2", centre), DiagnosisCode.MPGN_ID);
        addDiagnosisForDemographic(createDemographics("Test3", "User3", centre), DiagnosisCode.SRNS_ID);
        addDiagnosisForDemographic(createDemographics("Test4", "User4", centre), DiagnosisCode.MPGN_ID);

        DiagnosisCode diagnosisCode = diagnosisDao.getDiagnosisCode(1L);
        Map<Long, Integer> patientCountMap = utilityDao.getPatientCountPerUnitByDiagnosisCode(diagnosisCode);
        assertTrue(patientCountMap.get(3L).equals(2));
    }

    @Test
    public void testGetPatientCountByUnit() throws Exception {
        Centre centre = new Centre();
        centre.setId(3L);

        createDemographics("Test1", "User", centre);
        createDemographics("Test2", "User", centre);
        createDemographics("Test3", "User", centre);
        createDemographics("Test4", "User", centre);

        int count = utilityDao.getPatientCountByUnit(centre);
        assertEquals(4, count);
    }

    private Demographics createDemographics(String forename, String surname, Centre centre) {
        Demographics demographics = new Demographics();
        demographics.setForename(forename);
        demographics.setSurname(surname);
        demographics.setNhsNumberType(NhsNumberType.NHS_NUMBER);
        demographics.setRenalUnit(centre);
        demographicDao.saveDemographics(demographics);
        assertNotNull(demographics.getId());
        return demographics;
    }

    private void addDiagnosisForDemographic(Demographics demographics, Long diagnosisCodeId) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setText("Testing");
        diagnosis.setDiagnosisCode(diagnosisDao.getDiagnosisCode(diagnosisCodeId));
        diagnosis.setRadarNumber(demographics.getId());
        diagnosisDao.saveDiagnosis(diagnosis);
    }
}
