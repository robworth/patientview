package org.patientview.repository.radar.test.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Centre;
import org.patientview.model.Patient;
import org.patientview.model.enums.NhsNumberType;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.radar.user.PatientUser;
import org.patientview.repository.radar.dao.UserDao;
import org.patientview.repository.radar.service.DemographicsManager;
import org.patientview.repository.radar.service.UserManager;
import org.patientview.repository.radar.test.TestPvDbSchema;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context-service.xml"})
public class UserManagerTest extends TestPvDbSchema {

    @Inject
    @Named(value = "radarUserDao")
    private UserDao userDao;

    @Inject
    private UserManager userManager;

    @Inject
    private DemographicsManager demographicsManager;

    private DiseaseGroup diseaseGroup;

    private Centre centre;

    @Before
    public void setUp() {
        diseaseGroup = new DiseaseGroup();
        diseaseGroup.setId("1");
        diseaseGroup.setName("testGroup");
        diseaseGroup.setShortName("shortName");

        centre = new Centre();
        centre.setUnitCode("testCodeA");
    }

    @Test
    public void testPatientUserRegistration() throws Exception {

        // create a user row as per patient view
        userDao.createRawUser("testusername", "passwordhash", "my user", "test@test.com", "unitcode1", "NHS123");

        // create a demographic
        Date dob = new Date();
        Patient patient = createDemographics("Test", "User", centre, "NHS123", "test@test.com", dob);

        userManager.registerPatient(patient);

        // Try and register - will throw an exception as no matching radar number
        PatientUser patientUser = userManager.getPatientUser(patient.getEmailAddress());

        assertNotNull("registered user is null", patientUser);
        assertNotNull("no password generated", patientUser.getPassword());
        assertEquals("Email not set on user", patient.getEmailAddress(), patientUser.getEmail());
        assertNotNull("Dob not set on user", patientUser.getDateOfBirth());
    }

    private Patient createDemographics(String forename, String surname, Centre centre, String nhsno,
                                            String email, Date dateOfBirth) {
        Patient patient = new Patient();
        patient.setForename(forename);
        patient.setSurname(surname);
        patient.setNhsNumberType(NhsNumberType.NHS_NUMBER);
        patient.setNhsno(nhsno);
        patient.setRenalUnit(centre);
        patient.setEmailAddress(email);
        patient.setDob(dateOfBirth);
        patient.setDiseaseGroup(diseaseGroup);
        demographicsManager.saveDemographics(patient);
        assertNotNull(patient.getId());
        return patient;
    }

}
