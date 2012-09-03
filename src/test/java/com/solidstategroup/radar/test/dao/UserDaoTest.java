package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.filter.PatientUserFilter;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.util.RadarUtility;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserDaoTest extends BaseDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private DemographicsDao demographicsDao;

    @Autowired
    private UtilityDao utilityDao;

    @Test
    public void testAddGetAdminUser() throws Exception {
        AdminUser adminUser = new AdminUser();
        adminUser.setEmail("admin@radar101.com");
        adminUser.setUsername("admin@radar101.com");
        adminUser.setName("Admin");
        adminUser.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));

        userDao.saveAdminUser(adminUser);

        AdminUser checkAdminUser = userDao.getAdminUser(adminUser.getEmail());

        assertNotNull(checkAdminUser);
        assertTrue("Does not have a valid ID", checkAdminUser.hasValidId());
        assertEquals("Email not persisted", checkAdminUser.getEmail(), adminUser.getEmail());
        assertEquals("Username not persisted", checkAdminUser.getUsername(), adminUser.getUsername());
        assertEquals("Name not persisted", checkAdminUser.getName(), adminUser.getName());
        assertEquals("Password not persisted", checkAdminUser.getPassword(), adminUser.getPassword());
    }

    @Test
    public void testAddGetProfessionalUser() throws Exception {
        ProfessionalUser professionalUser = new ProfessionalUser();
        professionalUser.setEmail("professional@radar101.com");
        professionalUser.setUsername("professional@radar101.com");
        professionalUser.setForename("Eddard");
        professionalUser.setSurname("Stark");
        professionalUser.setRole("Centre role");
        professionalUser.setGmc("testGmc");
        professionalUser.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser);

        ProfessionalUser checkProfessionalUser = userDao.getProfessionalUser(professionalUser.getEmail());
        assertNotNull(checkProfessionalUser);
        assertTrue("Does not have a valid radar ID", checkProfessionalUser.hasValidId());
        assertTrue("Does not have a valid user ID", checkProfessionalUser.hasValidUserId());
        assertEquals("Email not persisted", checkProfessionalUser.getEmail(), professionalUser.getEmail());
        assertEquals("Username not persisted", checkProfessionalUser.getUsername(), professionalUser.getUsername());
        assertEquals("Forename not persisted", checkProfessionalUser.getForename(), professionalUser.getForename());
        assertEquals("Surname not persisted", checkProfessionalUser.getSurname(), professionalUser.getSurname());
        assertEquals("Password not persisted", checkProfessionalUser.getPassword(), professionalUser.getPassword());
        assertEquals("Role not persisted", checkProfessionalUser.getRole(), professionalUser.getRole());
        assertEquals("GMC not persisted", checkProfessionalUser.getGmc(), professionalUser.getGmc());
    }

    @Test
    public void testGetProfessionalUserById() throws Exception {
        ProfessionalUser professionalUser = new ProfessionalUser();
        professionalUser.setEmail("professional@radar101.com");
        professionalUser.setUsername("professional@radar101.com");
        professionalUser.setForename("Eddard");
        professionalUser.setSurname("Stark");
        professionalUser.setRole("Centre role");
        professionalUser.setGmc("testGmc");
        professionalUser.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser);

        ProfessionalUser checkProfessionalUser = userDao.getProfessionalUser(professionalUser.getId());
        assertNotNull(checkProfessionalUser);
    }

    @Test
    public void testDeleteProfessionalUser() throws Exception {
        ProfessionalUser professionalUser = new ProfessionalUser();
        professionalUser.setEmail("professional@radar101.com");
        professionalUser.setUsername("professional@radar101.com");
        professionalUser.setForename("Eddard");
        professionalUser.setSurname("Stark");
        professionalUser.setRole("Centre role");
        professionalUser.setGmc("testGmc");
        professionalUser.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser);

        // delete the user and try to pull back
        userDao.deleteProfessionalUser(professionalUser);

        ProfessionalUser checkProfessionalUser = userDao.getProfessionalUser(professionalUser.getEmail());
        assertNull(checkProfessionalUser);
    }

    @Test
    public void testGetProfessionalUsersInOrder() throws Exception {
        ProfessionalUser professionalUser1 = new ProfessionalUser();
        professionalUser1.setEmail("professional1@radar101.com");
        professionalUser1.setUsername("professional1@radar101.com");
        professionalUser1.setForename("Eddard");
        professionalUser1.setSurname("Stark");
        professionalUser1.setCentre(utilityDao.getCentre(1));
        professionalUser1.setRole("Centre role");
        professionalUser1.setGmc("testGmc");
        professionalUser1.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser1);

        ProfessionalUser professionalUser2 = new ProfessionalUser();
        professionalUser2.setEmail("professional2@radar101.com");
        professionalUser2.setUsername("professional2@radar101.com");
        professionalUser2.setForename("Tyrion");
        professionalUser2.setSurname("Lannister");
        professionalUser2.setCentre(utilityDao.getCentre(1));
        professionalUser2.setRole("Centre role");
        professionalUser2.setGmc("testGmc");
        professionalUser2.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser2);

        List<ProfessionalUser> checkProfessionalUsers = userDao.getProfessionalUsers(new ProfessionalUserFilter(),
                -1, -1);

        assertTrue("No professional users found", !checkProfessionalUsers.isEmpty()
                && checkProfessionalUsers.size() > 0);
        assertTrue("To many professional users found", checkProfessionalUsers.size() == 2);

        // first one should Tyrion
        assertEquals("First user in list is not correct", checkProfessionalUsers.get(0).getId(),
                professionalUser2.getId());
    }

    @Test
    public void testSearchProfessionalUsers() throws Exception {
        ProfessionalUser professionalUser1 = new ProfessionalUser();
        professionalUser1.setEmail("professional1@radar101.com");
        professionalUser1.setUsername("professional1@radar101.com");
        professionalUser1.setForename("Eddard");
        professionalUser1.setSurname("Stark");
        professionalUser1.setRole("Centre role");
        professionalUser1.setGmc("testGmc");
        professionalUser1.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser1);

        ProfessionalUser professionalUser2 = new ProfessionalUser();
        professionalUser2.setEmail("professional2@radar101.com");
        professionalUser2.setUsername("professional2@radar101.com");
        professionalUser2.setForename("Tyrion");
        professionalUser2.setSurname("Lannister");
        professionalUser2.setRole("Centre role");
        professionalUser2.setGmc("testGmc");
        professionalUser2.setPassword(RadarUtility.generateNewPassword());

        userDao.saveProfessionalUser(professionalUser2);

        ProfessionalUserFilter professionalUserFilter = new ProfessionalUserFilter();
        professionalUserFilter.addSearchCriteria(ProfessionalUserFilter.UserField.EMAIL.getDatabaseFieldName(),
                professionalUser2.getEmail());

        List<ProfessionalUser> checkProfessionalUsers = userDao.getProfessionalUsers(professionalUserFilter, -1, -1);

        assertTrue("No professional users found", !checkProfessionalUsers.isEmpty()
                && checkProfessionalUsers.size() > 0);
        assertTrue("To many professional users found", checkProfessionalUsers.size() == 1);

        // first one should Tyrion
        assertEquals("First user in list is not correct", checkProfessionalUsers.get(0).getId(),
                professionalUser2.getId());
    }

    @Test
    public void testAddGetPatientUser() throws Exception {
        PatientUser patientUser = new PatientUser();
        patientUser.setRadarNumber(1);
        patientUser.setEmail("patient@radar101.com");
        patientUser.setUsername("patient@radar101.com");
        patientUser.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser.setDateOfBirth(new Date());
        patientUser.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser);

        PatientUser checkPatientUser = userDao.getPatientUser(patientUser.getEmail());

        assertNotNull(checkPatientUser);
        assertTrue("Does not have a valid radar ID", checkPatientUser.hasValidId());
        assertTrue("Does not have a valid user ID", checkPatientUser.hasValidUserId());
        assertEquals("Email not persisted", checkPatientUser.getEmail(), patientUser.getEmail());
        assertEquals("Radar no not persisted", checkPatientUser.getRadarNumber(), patientUser.getRadarNumber());
        assertEquals("Username not persisted", checkPatientUser.getUsername(), patientUser.getUsername());
        assertEquals("Password not persisted", checkPatientUser.getPassword(), patientUser.getPassword());
    }

    @Test
    public void testGetPatientUserById() throws Exception {
        PatientUser patientUser = new PatientUser();
        patientUser.setRadarNumber(1);
        patientUser.setEmail("patient@radar101.com");
        patientUser.setUsername("patient@radar101.com");
        patientUser.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser.setDateOfBirth(new Date());
        patientUser.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser);

        PatientUser checkPatientUser = userDao.getPatientUser(patientUser.getId());

        assertNotNull(checkPatientUser);
    }

    @Test
    public void testDeletePatientUser() throws Exception {
        PatientUser patientUser = new PatientUser();
        patientUser.setRadarNumber(1);
        patientUser.setEmail("patient@radar101.com");
        patientUser.setUsername("patient@radar101.com");
        patientUser.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser.setDateOfBirth(new Date());
        patientUser.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser);

        // delete the user and try to pull back
        userDao.deletePatientUser(patientUser);

        PatientUser checkPatientUser = userDao.getPatientUser(patientUser.getEmail());
        assertNull(checkPatientUser);
    }

    @Test
    public void testGetPatientUsersInOrder() throws Exception {
        PatientUser patientUser1 = new PatientUser();
        patientUser1.setRadarNumber(1);
        patientUser1.setEmail("patient1@radar101.com");
        patientUser1.setUsername("patient1@radar101.com");
        patientUser1.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser1.setDateOfBirth(new Date());
        patientUser1.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser1);

        PatientUser patientUser2 = new PatientUser();
        patientUser2.setRadarNumber(2);
        patientUser2.setEmail("patient2@radar101.com");
        patientUser2.setUsername("patient2@radar101.com");
        patientUser2.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser2.setDateOfBirth(new Date());
        patientUser2.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser2);

        PatientUserFilter patientUserFilter = new PatientUserFilter();
        patientUserFilter.setReverse(false);

        List<PatientUser> checkPatientUsers = userDao.getPatientUsers(patientUserFilter, -1, -1);

        assertTrue("No patient users found", !checkPatientUsers.isEmpty()
                && checkPatientUsers.size() > 0);
        assertTrue("To many patient users found", checkPatientUsers.size() == 2);

        // first one should patient 2
        assertEquals("First user in list is not correct", checkPatientUsers.get(0).getId(),
                patientUser2.getId());
    }

    @Test
    public void testSearchPatientUsers() throws Exception {
        PatientUser patientUser1 = new PatientUser();
        patientUser1.setRadarNumber(1);
        patientUser1.setEmail("patient1@radar101.com");
        patientUser1.setUsername("patient1@radar101.com");
        patientUser1.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser1.setDateOfBirth(new Date());
        patientUser1.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser1);

        PatientUser patientUser2 = new PatientUser();
        patientUser2.setRadarNumber(2);
        patientUser2.setEmail("patient2@radar101.com");
        patientUser2.setUsername("patient2@radar101.com");
        patientUser2.setPassword(User.getPasswordHash(RadarUtility.generateNewPassword()));
        patientUser2.setDateOfBirth(new Date());
        patientUser2.setDateRegistered(new Date());

        userDao.savePatientUser(patientUser2);

        PatientUserFilter patientUserFilter = new PatientUserFilter();
        patientUserFilter.addSearchCriteria(PatientUserFilter.UserField.USERNAME.getDatabaseFieldName(),
                patientUser2.getUsername());

        List<PatientUser> checkPatientUsers = userDao.getPatientUsers(patientUserFilter, -1, -1);

        assertTrue("No patient users found", !checkPatientUsers.isEmpty()
                && checkPatientUsers.size() > 0);
        assertTrue("To many patient users found", checkPatientUsers.size() == 1);

        // first one should patient 2
        assertEquals("First user in list is not correct", checkPatientUsers.get(0).getId(),
                patientUser2.getId());
    }

    @Test
    public void outputLoginDetails() {

        LOGGER.info("Login details for test db only");

        // super user
        String email = "hugh.mccarthy@UHBristol.nhs.uk";
        ProfessionalUser professionalUser = userDao.getProfessionalUser(email);
        try {
            //String password = TripleDes.decrypt(professionalUser.getPasswordHash());
            //LOGGER.info("super user | email: " + email + " | password: " + password);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }

}
