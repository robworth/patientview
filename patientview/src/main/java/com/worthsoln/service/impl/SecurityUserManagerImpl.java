package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.security.model.SecurityUser;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UserManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service(value = "securityUserManager")
public class SecurityUserManagerImpl implements SecurityUserManager {

    @Inject
    private UserManager userManager;

    @Override
    public String getLoggedInUsername() {

        User securityUser = getSecurityUser();

        return securityUser != null ? securityUser.getUsername() : null;
    }

    @Override
    public Tenancy getLoggedInTenancy() {

        SecurityUser securityUser = getSecurityUser();

        return securityUser != null ? securityUser.getTenancy() : null;
    }

    @Override
    public boolean isLoggedIn() {
        return getLoggedInUsername() != null;
    }

    @Override
    public boolean isRolePresent(String... roles) {

        SecurityUser securityUser = getSecurityUser();

        if (securityUser != null) {
            Collection<GrantedAuthority> authorities = securityUser.getAuthorities();

            // users can have one role per tenancy
            for (GrantedAuthority grantedAuthority : authorities) {

                String userRole = grantedAuthority.getAuthority();

                if (roles != null) {
                    for (String role : roles) {
                        // convert to spring security convention
                        role = ("ROLE_" + securityUser.getTenancy().getContext() + "_" +  role).toUpperCase();

                        if (role.equals(userRole)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private SecurityUser getSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SecurityUser) {
                return (SecurityUser) principal;
            }
        }

        return null;
    }
}
