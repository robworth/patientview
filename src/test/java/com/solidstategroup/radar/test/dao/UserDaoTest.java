package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAdminUser() {
        AdminUser adminUser = userDao.getAdminUser("ihaynes@data-insite.co.uk");
        assertNotNull(adminUser);
    }
    
    @Test
    public void testGetPatientUser() {
        // Get a user
        PatientUser patientUser = userDao.getPatientUser("ihaynes@data-insite.co.uk");

        // This patient should exist in our test dataset
        assertNotNull(patientUser);
    }

    @Test
    public void testGetUnknownPatientUser() {
        // Get a user that doesn't exist
        PatientUser patientUser = userDao.getPatientUser("doesnt@exist.com");
        assertNull(patientUser);
    }

    @Test
    public void testSavePatientUser() throws Exception {
        // Construct the user
        PatientUser patientUser = new PatientUser();
        patientUser.setUsername("test_user");
        patientUser.setRadarNumber(123);
        patientUser.setDateOfBirth(new Date());
        patientUser.setPasswordHash(User.getPasswordHash("password12"));

        // Save
        userDao.savePatientUser(patientUser);

        // Make sure we have an ID and a date registered
        assertTrue("Saved user doesn't have an ID", patientUser.getId() > 0);
        // Make sure it has a date registered
        assertNotNull("No date registered", patientUser.getDateRegistered());

        // Try and get the patient user - should get our new user
        patientUser = userDao.getPatientUser("test_user");
        assertNotNull("Saved user was null on getting frmo DAO", patientUser);
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

    @Test
    public void testGetUnknownProfessionalUser() {
        // Get an unknown user
        ProfessionalUser professionalUser = userDao.getProfessionalUser("no@no.com");
        assertNull("Unknown user isn't null", professionalUser);
    }
    
    @Test
    public void testGetProfessionalUsers() {
        List<ProfessionalUser> professionalUsers = userDao.getProfessionalUsers();
        assertNotNull(professionalUsers);
        assertTrue(professionalUsers.size() > 0);
    }

    @Test
    public void testGetProfessionalUsersPage1() {
        List<ProfessionalUser> professionalUsers = userDao.getProfessionalUsers(null, 2, 1);
        assertNotNull(professionalUsers);
        assertTrue(professionalUsers.size() == 1);
    }
    
    @Test
    public void testSearchProfessionalUsers() {
        ProfessionalUserFilter userFilter = new ProfessionalUserFilter();
        userFilter.addSearchCriteria(ProfessionalUserFilter.UserField.FORENAME, "fiona");
        List<ProfessionalUser> professionalUsers = userDao.getProfessionalUsers(userFilter);
        assertNotNull(professionalUsers);
        assertTrue(professionalUsers.size() > 0);
    }
}
