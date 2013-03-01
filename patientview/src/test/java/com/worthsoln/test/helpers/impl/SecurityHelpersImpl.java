package com.worthsoln.test.helpers.impl;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.security.model.SecurityUser;
import com.worthsoln.test.helpers.SecurityHelpers;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 *
 */
@Component(value = "securityHelpers")
public class SecurityHelpersImpl implements SecurityHelpers {

    @Inject
    private UserDetailsService userDetailsService;

    @Override
    public void loginAsUser(String username, Tenancy tenancy) {
        SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(username);
        user.setTenancy(tenancy);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    @Override
    public void loginAsUser(String username) {
        SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(username);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
