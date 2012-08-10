package com.worthsoln.test.security;

import com.worthsoln.patientview.model.Tenancy;
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
    Tenancy tenancy1, tenancy2, tenancy3;

    @Before
    public void setupTenancies() {
        user = serviceHelpers.createUser("Username", "username@test.com", "pass", "Test User", "Testy");

        tenancy1 = serviceHelpers.createTenancy("Tenant 1", "ten1", "Test description");
        tenancy2 = serviceHelpers.createTenancy("Tenant 2", "ten2", "Test description 2");
        tenancy3 = serviceHelpers.createTenancy("Tenant 3", "ten3", "Test description 3");   // no access

        serviceHelpers.createTenancyUserRole(tenancy1, user, "patient");
        serviceHelpers.createTenancyUserRole(tenancy2, user, "admin");
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
        GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority("ROLE_TEN2_ADMIN");
        GrantedAuthority grantedAuthority2 = new SimpleGrantedAuthority("ROLE_TEN1_PATIENT");

        assertTrue("Incorrect authority", authoritySet.contains(grantedAuthority1));
        assertTrue("Incorrect authority", authoritySet.contains(grantedAuthority2));
    }

    @Test
    public void testIsRolePresentForSecurityUser() {

        loginAsUser(user.getUsername(), tenancy1);

        assertTrue("Patient should be present", securityUserManager.isRolePresent("patient"));

        assertFalse("Admin should not be present", securityUserManager.isRolePresent("admin"));

        logout();

        loginAsUser(user.getUsername(), tenancy2);

        assertFalse("Patient should not be present", securityUserManager.isRolePresent("patient"));

        assertTrue("Admin should be present", securityUserManager.isRolePresent("admin"));
    }

    @Test
    public void testGetLoggedInTenancy() {
        loginAsUser(user.getUsername(), tenancy1);

        assertEquals("Incorrect logged in tenancy", tenancy1, securityUserManager.getLoggedInTenancy());
    }

    @Test
    public void testSetLoggedInTenancy() throws Exception {

        // first login without setting the tenancy
        loginAsUser(user.getUsername());

        assertNull("Tenancy found in session", securityUserManager.getLoggedInTenancy());

        securityUserManager.setLoggedInTenancy(tenancy1.getId());

        assertEquals("Incorrect logged in tenancy", tenancy1, securityUserManager.getLoggedInTenancy());
    }

    @Test(expected = IllegalStateException.class)
    public void testCannotSetLoggedInTenancyWhenNoAccess() throws Exception {

        // first login without setting the tenancy
        loginAsUser(user.getUsername());

        assertNull("Tenancy found in session", securityUserManager.getLoggedInTenancy());

        securityUserManager.setLoggedInTenancy(tenancy3.getId());
    }

    @Test
    public void testUserGetsAnyUserRole() {

        loginAsUser(user.getUsername());

        assertTrue("User does not have ANY_USER role", securityUserManager.isRolePresent("any_user"));
    }

    @Test
    public void testIsTenancyPresent() {
        loginAsUser(user.getUsername(), tenancy1);

        assertTrue("Tenancy is not present", securityUserManager.isTenancyPresent(tenancy1.getContext()));
    }

    @Test
    public void testGetLoggedInUserRole() {
        loginAsUser(user.getUsername(), tenancy2);

        assertEquals("Incorrect logged in user role", "admin", userManager.getLoggedInUserRole());
    }

    @Test
    public void testGetCurrentTenancyRole() {
        loginAsUser(user.getUsername(), tenancy2);

        assertEquals("Incorrect logged in user role", "admin", userManager.getCurrentTenancyRole(user));
    }

    private void loginAsUser(String username, Tenancy tenancy) {
        securityHelpers.loginAsUser(username, tenancy);
    }

    private void loginAsUser(String username) {
        securityHelpers.loginAsUser(username);
    }

    private void logout() {
        securityHelpers.logout();
    }
}
