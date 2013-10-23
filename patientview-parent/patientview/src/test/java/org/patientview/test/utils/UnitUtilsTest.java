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

package org.patientview.test.utils;

import org.junit.Before;
import org.junit.Test;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.repository.UnitDao;
import org.patientview.repository.UserMappingDao;
import org.patientview.service.*;
import org.patientview.test.helpers.SecurityHelpers;
import org.patientview.test.helpers.ServiceHelpers;
import org.patientview.test.service.BaseServiceTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 *  Test the core functions of the security user manager and spring security context.
 *
 *  Note: this operates as per the service tests transaction-wise
 */
public class UnitUtilsTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private UnitDao unitDao;

    @Inject
    private UserMappingDao userMappingDao;

    // Test suite wide references
    private User unitadmin, superadmin,radaradmin,comboAdmin;
    private Specialty specialty1;

    @Before
    public void setupSpecialties() {
        unitadmin = serviceHelpers.createUser("unitadmin", "username@test.com", "pass", "Test User");
        superadmin = serviceHelpers.createUser("superadmin", "username@test.com", "pass", "Test User");
        radaradmin = serviceHelpers.createUser("radaradmin", "username@test.com", "pass", "Test User");
        comboAdmin = serviceHelpers.createUser("comboAdmin", "username@test.com", "pass", "Test User");


        specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");

        serviceHelpers.createSpecialtyUserRole(specialty1, superadmin, "superadmin");
        serviceHelpers.createSpecialtyUserRole(specialty1, unitadmin, "unitadmin");
        serviceHelpers.createSpecialtyUserRole(specialty1, comboAdmin, "unitadmin");
        serviceHelpers.createSpecialtyUserRole(specialty1, radaradmin, "radaradmin");

        UserMapping userMapping = new UserMapping();
        userMapping.setNhsno("123");
        userMapping.setUsername("radaradmin");
        userMapping.setSpecialty(specialty1);
        userMapping.setUnitcode("radar1");
        userMappingDao.save(userMapping);

        userMapping.setId(null);
        userMapping.setUsername("comboAdmin");
        userMapping.setUnitcode("radar1");
        userMappingDao.save(userMapping);
        userMapping.setId(null);
        userMapping.setUsername("comboAdmin");
        userMapping.setUnitcode("renal2");
        userMappingDao.save(userMapping);
        userMapping.setId(null);
        userMapping.setUsername("comboAdmin");
        userMapping.setUnitcode("renal1");
        userMappingDao.save(userMapping);

        userMapping.setId(null);
        userMapping.setUsername("unitadmin");
        userMapping.setUnitcode("renal2");
        userMappingDao.save(userMapping);

        Unit unit = new Unit();
        unit.setSpecialty(specialty1);
        // required fields
        unit.setUnitcode("radar1");
        unit.setName("z");
        unit.setShortname("nam1");
        unit.setSourceType("radargroup");
        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty1);
        // required fields
        unit.setUnitcode("radar2");
        unit.setName("y");
        unit.setShortname("nam2");
        unit.setSourceType("radargroup");
        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty1);
        // required fields
        unit.setUnitcode("renal1");
        unit.setName("x");
        unit.setShortname("nam3");
        unit.setSourceType("renalunit");
        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty1);
        // required fields
        unit.setUnitcode("renal2");
        unit.setName("w");
        unit.setShortname("nam4");
        unit.setSourceType("renalunit");
        unitDao.save(unit);
    }


    @Test
    public void testSetUserUnits() {

        loginAsUser(superadmin.getUsername(), specialty1);
        assertTrue("superadmin should be present", securityUserManager.isRolePresent("superadmin"));
        MockHttpServletRequest request = new MockHttpServletRequest();
        UnitUtils.setUserUnits(request);
        assertEquals("units size is wrong.", 4, ((List) request.getAttribute("units")).size());
        logout();

        loginAsUser(unitadmin.getUsername(), specialty1);
        assertTrue("unitadmin should be present", securityUserManager.isRolePresent("unitadmin"));
        request = new MockHttpServletRequest();
        UnitUtils.setUserUnits(request);
        assertEquals("units size is wrong.",1, ((List)request.getAttribute("units")).size());
        logout();

        loginAsUser(comboAdmin.getUsername(), specialty1);
        assertTrue("comboAdmin should be present", securityUserManager.isRolePresent("comboAdmin"));
        request = new MockHttpServletRequest();
        UnitUtils.setUserUnits(request);
        assertEquals("units size is wrong.",3, ((List)request.getAttribute("units")).size());
        logout();

        loginAsUser(radaradmin.getUsername(), specialty1);
        assertTrue("radaradmin should be present", securityUserManager.isRolePresent("radaradmin"));
        request = new MockHttpServletRequest();
        UnitUtils.setUserUnits(request);
        assertEquals("units size is wrong.",0, ((List)request.getAttribute("units")).size());
        logout();
    }

    @Test
    public void testSetUserRenalUnits() {

        loginAsUser(superadmin.getUsername(), specialty1);
        assertTrue("superadmin should be present", securityUserManager.isRolePresent("superadmin"));
        MockHttpServletRequest request = new MockHttpServletRequest();
        UnitUtils.setUserRenalUnits(request);
        assertEquals("units size is wrong.", 2, ((List) request.getAttribute("units")).size());
        logout();

        loginAsUser(unitadmin.getUsername(), specialty1);
        assertTrue("unitadmin should be present", securityUserManager.isRolePresent("unitadmin"));
        request = new MockHttpServletRequest();
        UnitUtils.setUserRenalUnits(request);
        assertEquals("units size is wrong.",1, ((List)request.getAttribute("units")).size());
        logout();

        loginAsUser(comboAdmin.getUsername(), specialty1);
        assertTrue("comboAdmin should be present", securityUserManager.isRolePresent("comboAdmin"));
        request = new MockHttpServletRequest();
        UnitUtils.setUserRenalUnits(request);
        assertEquals("units size is wrong.",2, ((List)request.getAttribute("units")).size());
        logout();

        loginAsUser(radaradmin.getUsername(), specialty1);
        assertTrue("radaradmin should be present", securityUserManager.isRolePresent("radaradmin"));
        request = new MockHttpServletRequest();
        UnitUtils.setUserRenalUnits(request);
        assertEquals("units size is wrong.",0, ((List)request.getAttribute("units")).size());
        logout();
    }



    private void loginAsUser(String username, Specialty specialty) {
        securityHelpers.loginAsUser(username, specialty);
    }

    private void logout() {
        securityHelpers.logout();
    }
}
