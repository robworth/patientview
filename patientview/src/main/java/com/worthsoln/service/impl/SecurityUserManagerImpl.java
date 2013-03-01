package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.TenancyDao;
import com.worthsoln.security.model.SecurityUser;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UserManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

@Service(value = "securityUserManager")
public class SecurityUserManagerImpl implements SecurityUserManager {

    @Inject
    private TenancyDao tenancyDao;

    @Inject
    private UserManager userManager;

    @Override
    public String getLoggedInUsername() {

        SecurityUser securityUser = getSecurityUser();

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
    public boolean isLoggedInToTenancy() {
        return getLoggedInTenancy() != null;
    }

    @Override
    public boolean isTenancyPresent(String context) {

        Tenancy tenancy = getLoggedInTenancy();

        return tenancy != null && tenancy.getContext().equalsIgnoreCase(context);
    }

    @Override
    public boolean isRolePresent(String... roles) {

        SecurityUser securityUser = getSecurityUser();

        // special case for all users
        if (securityUser != null && roles != null && roles.length == 1 && roles[0].equals("any_user")) {
            return true;
        }

        if (securityUser != null && securityUser.getTenancy() != null) {
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

    @Override
    public void setLoggedInTenancy(Long tenancyId) throws Exception {

        if (tenancyId == null) {
            throw new IllegalArgumentException("TenancyId required");
        }

        Tenancy tenancy = tenancyDao.get(tenancyId);

        if (tenancy == null) {
            throw new IllegalArgumentException("Invalid tenancy id");
        }

        SecurityUser securityUser = getSecurityUser();
        User user = userManager.getLoggedInUser();

        if (securityUser == null || user == null) {
            throw new IllegalStateException("No logged in user");
        }

        if (hasAccessToTenancy(user, tenancy)) {
            // set the tenancy in the session for the user:
            securityUser.setTenancy(tenancy);
        } else {
            throw new IllegalStateException("Attempting to set tenancy for which user has no access");
        }
    }

    @Override
    public boolean hasAccessToTenancy(User user, Tenancy tenancy) {

        List<TenancyUserRole> tenancyUserRoles = userManager.getTenancyUserRoles(user);

        for (TenancyUserRole tenancyUserRole : tenancyUserRoles) {
            if (tenancyUserRole.getTenancy().equals(tenancy)) {
                // the user has access to this tenancy
                return true;
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
