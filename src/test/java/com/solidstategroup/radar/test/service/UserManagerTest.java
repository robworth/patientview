package com.solidstategroup.radar.test.service;

import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.test.DatabaseBackedTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public class UserManagerTest extends DatabaseBackedTest {

    @Autowired
    private UserManager userManager;

    @Test(expected = RegistrationException.class)
    public void testPatientUserRegistration() throws RegistrationException {

        // Construct a patient user
        PatientUser patientUser = new PatientUser();
        patientUser.setRadarNumber(23232L);
        patientUser.setUsername("test_user");
        patientUser.setDateOfBirth(new Date());

        // Try and register - will throw an exception as no matching radar number
        userManager.registerPatient(patientUser);

    }

}
