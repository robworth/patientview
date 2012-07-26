package com.worthsoln.service.impl;

import com.worthsoln.service.SecurityUserManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service(value = "securityUserManager")
public class SecurityUserManagerImpl implements SecurityUserManager {

    @Override
    public String getLoggedInUsername() {

        User securityUser = getSecurityUser();

        return securityUser != null ? securityUser.getUsername() : null;
    }

    @Override
    public boolean isLoggedIn() {
        return getLoggedInUsername() != null;
    }

    @Override
    public boolean isRolePresent(String... roles) {

        User securityUser = getSecurityUser();

        if (securityUser != null) {
            Collection<GrantedAuthority> authorities = securityUser.getAuthorities();
            String loggedInRole = null;

            // users can only have one role
            if (authorities != null && authorities.size() > 0) {
                loggedInRole = authorities.toArray(new GrantedAuthority[authorities.size()])[0].getAuthority();
            }

            if (roles != null) {
                for (String role : roles) {
                    // convert to spring security convention
                    role = ("ROLE_" + role).toUpperCase();

                    if (role.equals(loggedInRole)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private User getSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            }
        }

        return null;
    }
}
