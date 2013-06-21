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

package com.worthsoln.test.security;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.User;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.test.helpers.SecurityHelpers;
import com.worthsoln.test.helpers.ServiceHelpers;
import com.worthsoln.test.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.inject.Inject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 *  Test the core functions of the security user manager and spring security context.
 *
 *  Note: this operates as per the service tests transaction-wise
 */
public class SecurityTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private UserManager userManager;

    // Test suite wide references
    private User user;
    Specialty specialty1, specialty2, specialty3;

    @Before
    public void setupSpecialties() {
        user = serviceHelpers.createUser("Username", "username@test.com", "pass", "Test User");

        specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        specialty2 = serviceHelpers.createSpecialty("Specialty 2", "Specialty2", "Test description 2");
        specialty3 = serviceHelpers.createSpecialty("Specialty 3", "Specialty3", "Test description 3");   // no access

        serviceHelpers.createSpecialtyUserRole(specialty1, user, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty2, user, "admin");
    }


    @Test
    public void testGetSecurityUserFromUsername() {

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        assertTrue("Security User is null", userDetails != null);
        assertEquals("Username is incorrect", user.getUsername(), userDetails.getUsername());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // NOTE: there is an extra role in here called ROLE_ANY_USER, hence 3 rather than 2
        assertEquals("Incorrect number of roles", 3, authorities.size());

        Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>(authorities);
        GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority("ROLE_SPECIALTY2_ADMIN");
        GrantedAuthority grantedAuthority2 = new SimpleGrantedAuthority("ROLE_SPECIALTY1_PATIENT");

        assertTrue("Incorrect authority", authoritySet.contains(grantedAuthority1));
        assertTrue("Incorrect authority", authoritySet.contains(grantedAuthority2));
    }

    @Test
    public void testIsRolePresentForSecurityUser() {

        loginAsUser(user.getUsername(), specialty1);

        assertTrue("Patient should be present", securityUserManager.isRolePresent("patient"));

        assertFalse("Admin should not be present", securityUserManager.isRolePresent("admin"));

        logout();

        loginAsUser(user.getUsername(), specialty2);

        assertFalse("Patient should not be present", securityUserManager.isRolePresent("patient"));

        assertTrue("Admin should be present", securityUserManager.isRolePresent("admin"));
    }

    @Test
    public void testGetLoggedInSpecialty() {
        loginAsUser(user.getUsername(), specialty1);

        assertEquals("Incorrect logged in specialty", specialty1, securityUserManager.getLoggedInSpecialty());
    }

    @Test
    public void testSetLoggedInSpecialty() throws Exception {

        // first login without setting the specialty
        loginAsUser(user.getUsername());

        assertNull("Specialty found in session", securityUserManager.getLoggedInSpecialty());

        securityUserManager.setLoggedInSpecialty(specialty1.getId());

        assertEquals("Incorrect logged in specialty", specialty1, securityUserManager.getLoggedInSpecialty());
    }

    @Test(expected = IllegalStateException.class)
    public void testCannotSetLoggedInSpecialtyWhenNoAccess() throws Exception {

        // first login without setting the specialty
        loginAsUser(user.getUsername());

        assertNull("Specialty found in session", securityUserManager.getLoggedInSpecialty());

        securityUserManager.setLoggedInSpecialty(specialty3.getId());
    }

    @Test
    public void testUserGetsAnyUserRole() {

        loginAsUser(user.getUsername());

        assertTrue("User does not have ANY_USER role", securityUserManager.isRolePresent("any_user"));
    }

    @Test
    public void testIsSpecialtyPresent() {
        loginAsUser(user.getUsername(), specialty1);

        assertTrue("Specialty is not present", securityUserManager.isSpecialtyPresent(specialty1.getContext()));
    }

    @Test
    public void testGetLoggedInUserRole() {
        loginAsUser(user.getUsername(), specialty2);

        assertEquals("Incorrect logged in user role", "admin", userManager.getLoggedInUserRole());
    }

    @Test
    public void testGetCurrentSpecialtyRole() {
        loginAsUser(user.getUsername(), specialty2);

        assertEquals("Incorrect logged in user role", "admin", userManager.getCurrentSpecialtyRole(user));
    }

    private void loginAsUser(String username, Specialty specialty) {
        securityHelpers.loginAsUser(username, specialty);
    }

    private void loginAsUser(String username) {
        securityHelpers.loginAsUser(username);
    }

    private void logout() {
        securityHelpers.logout();
    }
}
