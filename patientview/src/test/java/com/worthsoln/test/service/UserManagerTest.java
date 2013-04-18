package com.worthsoln.test.service;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
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
    private UserManager userManager;

    private Specialty specialty1;
    private UnitAdmin unitAdmin;

    @Before
    public void setupSystem() {
        // create an admin adminUser and specialty and log them in
        User adminUser = serviceHelpers.createUser("Username", "username@test.com", "pass", "Test User");
        specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "unitadmin");

        securityHelpers.loginAsUser(adminUser.getUsername(), specialty1);

        // a new unit staff user to create
        unitAdmin = new UnitAdmin("unitstaff-username1", "pass", "Unit Staff Name",
                "unitstaff-username1@patientview.org", false, "unitstaff", true);
    }

    @Test
    public void testSaveUserFromUnitAdmin() {
        // create a new unit staff and their roles
        User newUser = userManager.saveUserFromUnitAdmin(unitAdmin);

        assertTrue("Invalid id", newUser.getId() > 0);

        // check new User's specialty etc
        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(newUser);

        assertEquals("Incorrect number of roles", 1, specialtyUserRoles.size());
        assertEquals("Incorrect specialty", specialty1, specialtyUserRoles.get(0).getSpecialty());
        assertEquals("Incorrect specialty role", "unitstaff", specialtyUserRoles.get(0).getRole());
    }

    @Test
    public void testSaveUpdateUserFromUnitAdmin() {

        User newUser = userManager.saveUserFromUnitAdmin(unitAdmin);

        // update and save
        unitAdmin.setName("Updated name");
        User updatedUser = userManager.saveUserFromUnitAdmin(unitAdmin);

        // check the update has been made
        assertEquals("Incorrect updated name", "Updated name", updatedUser.getName());

        // check new User's specialty etc still is OK
        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(updatedUser);

        assertEquals("Incorrect number of roles", 1, specialtyUserRoles.size());
        assertEquals("Incorrect specialty", specialty1, specialtyUserRoles.get(0).getSpecialty());
        assertEquals("Incorrect specialty role", "unitstaff", specialtyUserRoles.get(0).getRole());
    }
}
