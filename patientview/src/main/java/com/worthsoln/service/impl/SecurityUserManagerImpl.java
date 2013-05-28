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

package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SpecialtyDao;
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
    private SpecialtyDao specialtyDao;

    @Inject
    private UserManager userManager;

    @Override
    public String getLoggedInUsername() {

        SecurityUser securityUser = getSecurityUser();

        return securityUser != null ? securityUser.getUsername() : null;
    }

    @Override
    public String getLoggedInEmailAddress() {

        SecurityUser securityUser = getSecurityUser();

        User user = userManager.get(securityUser.getUsername());

        return user != null ? user.getEmail() : null;
    }

    @Override
    public boolean isFirstLogon() {
        SecurityUser securityUser = getSecurityUser();

        User user = userManager.get(securityUser.getUsername());

        return user != null && user.isFirstlogon();
    }

    @Override
    public boolean isEmailVerified() {
        SecurityUser securityUser = getSecurityUser();

        User user = userManager.get(securityUser.getUsername());

        return user != null && user.isEmailverified();
    }

    @Override
    public Specialty getLoggedInSpecialty() {

        SecurityUser securityUser = getSecurityUser();

        return securityUser != null ? securityUser.getSpecialty() : null;
    }

    @Override
    public boolean isLoggedIn() {
        return getLoggedInUsername() != null;
    }

    @Override
    public boolean isLoggedInToSpecialty() {
        return getLoggedInSpecialty() != null;
    }

    @Override
    public boolean isSpecialtyPresent(String context) {

        Specialty specialty = getLoggedInSpecialty();

        return specialty != null && specialty.getContext().equalsIgnoreCase(context);
    }

    @Override
    public boolean isRolePresent(String... roles) {

        SecurityUser securityUser = getSecurityUser();

        // special case for all users
        if (securityUser != null && roles != null && roles.length == 1 && roles[0].equals("any_user")) {
            return true;
        }

        if (securityUser != null && securityUser.getSpecialty() != null) {
            Collection<GrantedAuthority> authorities = securityUser.getAuthorities();

            // users can have one role per Specialty
            for (GrantedAuthority grantedAuthority : authorities) {

                String userRole = grantedAuthority.getAuthority();

                if (roles != null) {
                    for (String role : roles) {
                        // convert to spring security convention
                        role = ("ROLE_" + securityUser.getSpecialty().getContext() + "_" + role).toUpperCase();

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
    public void setLoggedInSpecialty(Long specialtyId) throws Exception {

        if (specialtyId == null) {
            throw new IllegalArgumentException("SpecialtyId required");
        }

        Specialty specialty = specialtyDao.get(specialtyId);

        if (specialty == null) {
            throw new IllegalArgumentException("Invalid specialty id");
        }

        SecurityUser securityUser = getSecurityUser();
        User user = userManager.getLoggedInUser();

        if (securityUser == null || user == null) {
            throw new IllegalStateException("No logged in user");
        }

        if (hasAccessToSpecialty(user, specialty)) {
            // set the specialty in the session for the user:
            securityUser.setSpecialty(specialty);
        } else {
            throw new IllegalStateException("Attempting to set specialty for which user has no access");
        }
    }

    @Override
    public boolean hasAccessToSpecialty(User user, Specialty specialty) {

        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(user);

        for (SpecialtyUserRole specialtyUserRole : specialtyUserRoles) {
            if (specialtyUserRole.getSpecialty().equals(specialty)) {
                // the user has access to this specialty
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
