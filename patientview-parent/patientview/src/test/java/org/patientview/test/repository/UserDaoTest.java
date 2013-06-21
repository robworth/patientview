/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.test.repository;

import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.model.User;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.repository.UnitDao;
import org.patientview.repository.UserDao;
import org.patientview.repository.UserMappingDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 *      Test UserMappingDao and UserDao
 */
public class UserDaoTest extends BaseDaoTest {

    @Inject
    private UserDao userDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Inject
    private UnitDao unitDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Before
    public void createTestMappings() {

        specialty = repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty");

        UserMapping userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno1");
        userMapping.setUnitcode("unitcode1");
        userMapping.setUsername("username1");

        userMappingDao.save(userMapping);

        userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno2");
        userMapping.setUnitcode("unitcode2");
        userMapping.setUsername("username2");

        userMappingDao.save(userMapping);

        // seond nhs and unitcode for user 2
        userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno3");
        userMapping.setUnitcode("unitcode3");
        userMapping.setUsername("username2");

        userMappingDao.save(userMapping);
    }

    @Test
    public void testAddGetUser() {

        User user = new User();
        user.setEmail("test@worthsolns.com");
        user.setName("Firstname Lastname");
        user.setPassword("password");
        user.setUsername("test");

        userDao.save(user);

        assertTrue("Invalid id for new user", user.getId() > 0);

        User checkUser = userDao.get(user.getId());

        assertEquals("Name not persisted", user.getName(), checkUser.getName());

        checkUser = userDao.get(user.getUsername());

        assertTrue("User not found via username", checkUser != null && checkUser.equals(user));
    }

    @Test
    public void testAddGetUserBooleanAttributes() {

        User user = new User();
        user.setEmail("test@worthsolns.com");
        user.setName("Firstname Lastname");
        user.setPassword("password");
        user.setUsername("test");
        user.setAccountlocked(true);
        user.setIsclinician(true);
        user.setIsrecipient(false);

        userDao.save(user);

        assertTrue("Invalid id for new user", user.getId() > 0);

        User checkUser = userDao.get(user.getId());

        assertEquals("Name not persisted", user.getName(), checkUser.getName());

        checkUser = userDao.get(user.getUsername());

        assertTrue("User not found via username", checkUser != null && checkUser.equals(user));

        assertTrue("Account locked not persisted", checkUser.isAccountlocked());
        assertFalse("IsRecipient not persisted", checkUser.isIsrecipient());
        assertTrue("IsClinician not persisted", checkUser.isIsclinician());
    }

    @Test
    public void testGetAllDelete() {
        User user = new User();
        user.setEmail("test@worthsolns.com");
        user.setName("Firstname Lastname");
        user.setPassword("password");
        user.setUsername("test");
        userDao.save(user);

        user = new User();
        user.setEmail("test2@worthsolns.com");
        user.setName("Firstname Lastname2");
        user.setPassword("password2");
        user.setUsername("test2");
        userDao.save(user);

        user = new User();
        user.setEmail("test3@worthsolns.com");
        user.setName("Firstname Lastname3");
        user.setPassword("password3");
        user.setUsername("test3");
        userDao.save(user);

        assertEquals("Incorrect number of users", 3, userDao.getAll().size());

        userDao.delete(user);

        assertEquals("Incorrect number of users after delete", 2, userDao.getAll().size());
    }

    @Test
    public void testAddGetUserMapping() {

        UserMapping userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno1");
        userMapping.setUnitcode("unicode1");
        userMapping.setUsername("username1");

        userMappingDao.save(userMapping);

        assertTrue("Invalid id for new usermapping", userMapping.getId() > 0);

        UserMapping checkUserMapping = userMappingDao.get(userMapping.getId());

        assertTrue("User not found via id", checkUserMapping != null && checkUserMapping.equals(userMapping));
    }

    @Test
    public void testGetAll() {

        // ignore unitcode2
        List<UserMapping> userMappings = userMappingDao.getAll("username2", specialty);

        assertEquals("incorrect number of mappings found for username2", 2, userMappings.size());
    }

    @Test
    public void testGetAllExcludeUnitcode() {

        List<UserMapping> userMappings = userMappingDao.getAllExcludeUnitcode("username2",  "unitcode2", specialty);

        assertEquals("incorrect number of mappings found for username2, excluding unitcode2", 1, userMappings.size());
    }

    @Test
    public void testGetAllWithUnitCode() {
        List<UserMapping> userMappings = userMappingDao.getAll("username2", "unitcode2", specialty);

        assertEquals("Incorrect number of mappings found", 1, userMappings.size());
        assertEquals("Incorrect mapping found", "unitcode2", userMappings.get(0).getUnitcode());
    }

    @Test
    public void testGetAllForNhsNo() {

        List<UserMapping> userMappings = userMappingDao.getAllForNhsNo("nhsno2", specialty);

        assertEquals("Incorrect number of mappings found", 1, userMappings.size());
    }

    @Test
    public void testUsersRealUnitcodeBestGuess() {

        // there will be 2 results, we pick the first
        String unitCode = userMappingDao.getUsersRealUnitcodeBestGuess("username2", specialty);

        assertEquals("incorrect unitcode found with best guess", "unitcode2", unitCode);
    }

    @Test
    public void testUsersRealNhsNoBestGuess() {

        // there will be 2 results, we pick the first
        String unitCode = userMappingDao.getUsersRealNhsNoBestGuess("username2", specialty);

        assertEquals("incorrect nhsno found with best guess", "nhsno2", unitCode);
    }

    @Test
    public void testGetUserMappingPatientEntered() {

        // not really sure why this is how it is

        // there will be no "patient" mapping so expect a new one to be returned
        User user = new User();
        user.setUsername("username1");
        UserMapping userMapping = userMappingDao.getUserMappingPatientEntered(user, specialty);

        assertEquals("incorrect mapping unitcode found", UnitUtils.PATIENT_ENTERS_UNITCODE, userMapping.getUnitcode());
        assertEquals("incorrect mapping nhsno found", "nhsno1", userMapping.getNhsno());
        assertEquals("incorrect mapping username found", "username1", userMapping.getUsername());
    }

    @Test
    public void testGetUsersSiblings() {
        // add a couple of siblings for username1
        UserMapping userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno1-GP");
        userMapping.setUnitcode("unitcode1");
        userMapping.setUsername("username1-GP");

        userMappingDao.save(userMapping);

        userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno1");
        userMapping.setUnitcode("PATIENT");
        userMapping.setUsername("username1");

        userMappingDao.save(userMapping);

        List<UserMapping> userMappings = userMappingDao.getUsersSiblings("username1", "unitcode1", specialty);

        // should get back 3 out of the total of 5
        assertEquals("incorrect number of mappings found for username1", 3, userMappings.size());
    }

    @Test
    public void testGetDuplicateUsers() {

        // add 2 duplicate users for nhsno1

        // this would should not show because of username %-GP
        UserMapping userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno1");
        userMapping.setUnitcode("unitcodeA");
        userMapping.setUsername("usernameDuplicate1-GP");

        userMappingDao.save(userMapping);

        // this one will show
        userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno("nhsno1");
        userMapping.setUnitcode("unitcodeA");
        userMapping.setUsername("usernameDuplicate1");

        userMappingDao.save(userMapping);

        List<UserMapping> userMappings = userMappingDao.getDuplicateUsers("nhsno1", "username1", specialty);

        // should get back 1 result
        assertEquals("incorrect number of duplicates found for nhsno1", 1, userMappings.size());

        assertEquals("incorrect duplicate found for nhsno1", "usernameDuplicate1", userMappings.get(0).getUsername());
    }

    @Test
    public void testGetUsers() {
        // Add usermapping
        UserMapping userMapping1 = new UserMapping();
        userMapping1.setSpecialty(specialty);
        userMapping1.setNhsno("nhsno1");
        userMapping1.setUnitcode("UNITCODE1");
        userMapping1.setUsername("testname1");

        userMappingDao.save(userMapping1);

        UserMapping userMapping2 = new UserMapping();
        userMapping2.setSpecialty(specialty);
        userMapping2.setNhsno("nhsno1");
        userMapping2.setUnitcode("UNITCODE1");
        userMapping2.setUsername("testname2");

        userMappingDao.save(userMapping2);

        UserMapping userMapping3 = new UserMapping();
        userMapping3.setSpecialty(specialty);
        userMapping3.setNhsno("nhsno1");
        userMapping3.setUnitcode("UNITCODE1");
        userMapping3.setUsername("testname3-GP");

        userMappingDao.save(userMapping3);

        // Add user
        User user = new User();
        user.setEmail("test@worthsolns.com");
        user.setName("Firstname Lastname");
        user.setPassword("password");
        user.setUsername("username1");
        userDao.save(user);

        User user1 = new User();
        user1.setEmail("test1@worthsolns.com");
        user1.setName("Firstname Lastname1");
        user1.setPassword("password1");
        user1.setUsername("testname1");
        userDao.save(user1);

        User user2 = new User();
        user2.setEmail("test2@worthsolns.com");
        user2.setName("Firstname Lastname2");
        user2.setPassword("password2");
        user2.setUsername("testname2");
        userDao.save(user2);

        User user3 = new User();
        user3.setEmail("test3@worthsolns.com");
        user3.setName("Firstname Lastname3");
        user3.setPassword("password3");
        user3.setUsername("testname3-GP");
        userDao.save(user3);

        User user4 = new User();
        user4.setEmail("test2@worthsolns.com");
        user4.setName("Firstname Lastname2");
        user4.setPassword("password2");
        user4.setUsername("username2");
        userDao.save(user4);

        // Add SpecialtyUserRole
        repositoryHelpers.createSpecialtyUserRole(specialty, user, "unitadmin");
        repositoryHelpers.createSpecialtyUserRole(specialty, user1, "patient");
        repositoryHelpers.createSpecialtyUserRole(specialty, user2, "patient");
        repositoryHelpers.createSpecialtyUserRole(specialty, user3, "patient");
        repositoryHelpers.createSpecialtyUserRole(specialty, user4, "patient");

        Unit unitRm301 = new Unit();
        unitRm301.setUnitcode("UNITCODE1");
        unitRm301.setName("RM301: RUNNING MAN TEST UNIT");
        unitRm301.setShortname("RM301");
        unitRm301.setRenaladminemail("renaladmin@mailinator.com");
        unitRm301.setSpecialty(specialty);
        //unitDao.save(unitRm301);

        List<User> checkUserList = userDao.get(user, specialty, "patient", unitRm301); System.out.println("unitcode = " +unitRm301.getUnitcode());

        assertEquals("Wrong number of users", checkUserList.size(), 2);
        assertFalse("User 3 found in users", checkUserList.contains(user3));
        assertFalse("User 4 found in users", checkUserList.contains(user4));
        assertTrue("User 1 not found in users", checkUserList.contains(user1));
        assertTrue("User 2 not found in users", checkUserList.contains(user2));
    }
}
