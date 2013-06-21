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

package com.worthsoln.test.service;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.test.helpers.SecurityHelpers;
import com.worthsoln.test.helpers.ServiceHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 *      These tests require an admin adminUser to be logged into a specialty
 */
public class UserManagerTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    private Unit unitRm301;
    private Specialty specialty1;
    private UnitAdmin unitAdmin;

    @Before
    public void setupSystem() {

        // create an admin adminUser and specialty and log them in
        User adminUser = serviceHelpers.createUser("Username", "username@test.com", "pass", "Test User");
        specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "unitadmin");

        unitRm301 = new Unit();
        unitRm301.setUnitcode("RM301");
        unitRm301.setName("RM301: RUNNING MAN TEST UNIT");
        unitRm301.setShortname("RM301");
        unitRm301.setRenaladminemail("renaladmin@mailinator.com");
        unitRm301.setSpecialty(specialty1);
        unitManager.save(unitRm301);

        securityHelpers.loginAsUser(adminUser.getUsername(), specialty1);

        // a new unit staff user to create
        unitAdmin = new UnitAdmin("unitstaff-username1", "pass", "Unit Staff Name",
                "unitstaff-username1@patientview.org", false, "unitstaff", true);
        unitAdmin.setIsrecipient(false);
        unitAdmin.setIsclinician(true);
    }

    @Test
    public void testSaveUserFromUnitAdmin() {

        // create a new unit staff and their roles
        User newUser = userManager.saveUserFromUnitAdmin(unitAdmin, unitRm301.getUnitcode());

        assertTrue("Invalid id", newUser.getId() > 0);

        // check new User's specialty etc
        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(newUser);

        assertEquals("Incorrect number of roles", 1, specialtyUserRoles.size());
        assertEquals("Incorrect specialty", specialty1, specialtyUserRoles.get(0).getSpecialty());
        assertEquals("Incorrect specialty role", "unitstaff", specialtyUserRoles.get(0).getRole());

        // check user has some user mappings
        List<UserMapping> userMappings = userManager.getUserMappings(newUser.getUsername());
        assertTrue("Incorrect user mappings created", userMappings != null && userMappings.size() == 1);

        assertTrue("User does not exist in Radar", userManager.userExistsInRadar(newUser.getId()));
        assertTrue("Incorrect user mappings created", userMappings != null && userMappings.size() == 1);
        assertFalse("Incorrect isRecipient created", newUser.isIsrecipient());
        assertTrue("Incorrect isClinician created", newUser.isIsclinician());
    }

    @Test
    public void testSaveUpdateUserFromUnitAdmin() {

        User newUser = userManager.saveUserFromUnitAdmin(unitAdmin, unitRm301.getUnitcode());

        // update and save
        unitAdmin.setName("Updated name");
        User updatedUser = userManager.saveUserFromUnitAdmin(unitAdmin, unitRm301.getUnitcode());

        // check the update has been made
        assertEquals("Incorrect updated name", "Updated name", updatedUser.getName());

        // check new User's specialty etc still is OK
        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(updatedUser);

        assertEquals("Incorrect number of roles", 1, specialtyUserRoles.size());
        assertEquals("Incorrect specialty", specialty1, specialtyUserRoles.get(0).getSpecialty());
        assertEquals("Incorrect specialty role", "unitstaff", specialtyUserRoles.get(0).getRole());
        assertFalse("Incorrect isRecipient created", newUser.isIsrecipient());
        assertTrue("Incorrect isClinician created", newUser.isIsclinician());
    }

    @Test
    public void testDeleteUserFromUnitAdmin() {

        // create a new unit staff and their roles
        User newUser = userManager.saveUserFromUnitAdmin(unitAdmin, unitRm301.getUnitcode());

        assertTrue("User does not exist in Radar", userManager.userExistsInRadar(newUser.getId()));

        userManager.delete(newUser.getUsername(), unitRm301.getUnitcode());

        User checkUser = userManager.get(newUser.getUsername());
        assertNull("User still found", checkUser);

        List<UserMapping> userMappings = userManager.getUserMappings(newUser.getUsername());
        assertTrue("User still has patientview mappings", userMappings != null && userMappings.size() == 0);

        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(newUser);
        assertTrue("User still has specialtyUserRoles", specialtyUserRoles != null && specialtyUserRoles.size() == 0);

        assertFalse("User still exists in Radar", userManager.userExistsInRadar(newUser.getId()));
    }

    @Test
    public void testGetUsers() {

        User adminUser = serviceHelpers.createUserWithMapping("adminuser", "test@admin.com", "p", "Admin", "UNITA", "nhs1", specialty1);
        User user1 = serviceHelpers.createUserWithMapping("testname1", "test1@admin.com", "p", "test1", "UNITA", "nhstest1", specialty1);
        User user2 = serviceHelpers.createUserWithMapping("testname2", "test2@admin.com", "p", "test2", "UNITA", "nhstest2", specialty1);
        User user3 = serviceHelpers.createUserWithMapping("testname3-GP", "test3@admin.com", "p", "test3", "UNITA", "nhstest3", specialty1);
        User user4 = serviceHelpers.createUserWithMapping("testname4", "test4@admin.com", "p", "test4", "unitB", "nhstest4", specialty1);

        // Add SpecialtyUserRole
        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "unitadmin");
        serviceHelpers.createSpecialtyUserRole(specialty1, user1, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user2, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user3, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user4, "patient");

        Unit unitRm301 = new Unit();
        unitRm301.setUnitcode("unitA");
        unitRm301.setName("RM301: RUNNING MAN TEST UNIT");
        unitRm301.setShortname("RM301");
        unitRm301.setRenaladminemail("renaladmin@mailinator.com");
        unitRm301.setSpecialty(specialty1);
        unitManager.save(unitRm301);

        List<User> checkUserList = userManager.getUsers(adminUser, specialty1, "patient", unitRm301);

        assertEquals("Wrong number of users", checkUserList.size(), 2);
        assertFalse("User 3 found in users", checkUserList.contains(user3));
        assertFalse("User 4 found in users", checkUserList.contains(user4));
        assertTrue("User 1 not found in users", checkUserList.contains(user1));
        assertTrue("User 2 not found in users", checkUserList.contains(user2));
    }
}
