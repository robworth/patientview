package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
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

        // Check the various strings
        assertEquals("Wrong surname", "Little", professionalUser.getSurname());
        assertEquals("Wrong forename", "Mark", professionalUser.getForename());
        assertEquals("Wrong title", "Dr", professionalUser.getTitle());
        assertEquals("Wrong role", "Senior Lecturer", professionalUser.getRole());

        assertEquals("Wrong email", "marklittle@nhs.net", professionalUser.getEmail());

        // Username should have been set
        assertNotNull("Username is null", professionalUser.getUsername());
        assertEquals("Username is wrong", "marklittle@nhs.net", professionalUser.getUsername());

        // Password should have been set
        assertNotNull("Password hash is null", professionalUser.getPasswordHash());
    }

}
