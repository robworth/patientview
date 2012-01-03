package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetPatientUser() {
        // Get a user
        PatientUser patientUser = userDao.getPatientUser("ihaynes@data-insite.co.uk");

        // This patient should exist in our test dataset
        assertNotNull(patientUser);
    }

    @Test
    public void testGetProfessionalUser() {
        // Get a user
        ProfessionalUser professionalUser = userDao.getProfessionalUser("marklittle@nhs.net");

        // This professional user should exist in our test database
        assertNotNull(professionalUser);
    }

}
