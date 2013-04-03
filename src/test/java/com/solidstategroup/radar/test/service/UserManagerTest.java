package com.solidstategroup.radar.test.service;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.enums.NhsNumberType;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.DemographicsManager;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.test.TestPvDbSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public class UserManagerTest extends TestPvDbSchema {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserManager userManager;

    @Autowired
    private DemographicsManager demographicsManager;

    @Test
    public void testPatientUserRegistration() throws Exception {

        // create a user row as per patient view
        userDao.createRawUser("testusername", "passwordhash", "my user", "test@test.com", "unitcode1", "NHS123");

        // create a demographic
        Date dob = new Date();
        Demographics demographics = createDemographics("Test", "User", null, "NHS123", "test@test.com", dob);

        userManager.registerPatient(demographics);

        // Try and register - will throw an exception as no matching radar number
        PatientUser patientUser = userManager.getPatientUser(demographics.getEmailAddress());

        assertNotNull("registered user is null", patientUser);
        assertNotNull("no password generated", patientUser.getPassword());
        assertEquals("Email not set on user", demographics.getEmailAddress(), patientUser.getEmail());
        assertNotNull("Dob not set on user", patientUser.getDateOfBirth());
    }

    private Demographics createDemographics(String forename, String surname, Centre centre, String nhsno,
                                            String email, Date dateOfBirth) {
        Demographics demographics = new Demographics();
        demographics.setForename(forename);
        demographics.setSurname(surname);
        demographics.setNhsNumberType(NhsNumberType.NHS_NUMBER);
        demographics.setNhsNumber(nhsno);
        demographics.setRenalUnit(centre);
        demographics.setEmailAddress(email);
        demographics.setDateOfBirth(dateOfBirth);
        demographicsManager.saveDemographics(demographics);
        assertNotNull(demographics.getId());
        return demographics;
    }
}
