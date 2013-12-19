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

package org.patientview.radar.service.impl;

import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UserManager;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    private UserManager userManager;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<User> users = new ArrayList<User>();
        // Get the user by their email, try admin, then professional, then patient - this is fine in terms of security
        // as the login pages have an extra check to see the user name exists for the the user type
        users.add(userManager.getSuperUserWithUsername(authentication.getName()));
        users.add(userManager.getAdminUserWithUsername(authentication.getName()));
        users.add(userManager.getProfessionalUserWithUsername(authentication.getName()));
        users.add(userManager.getPatientUserWithUsername(authentication.getName()));

        for (User user : users) {
            // If we've found a user...
            if (user != null) {
                // Get the password hash
                try {
                    // Didn't want to store plain text password in memory, even tho probably safe
                    // Instead password hash is set on user from DAO, then we compare the two hashes
                    String passwordHash = User.getPasswordHash((String) authentication.getCredentials());
                    if (user.getPassword().equals(passwordHash)) {
                        // Authenticated
                        List<GrantedAuthorityImpl> authorities =
                                Arrays.asList(new GrantedAuthorityImpl(user.getSecurityRole()));
                        authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
                        return authentication;
                    }
                } catch (Exception e) {
                    LoggerFactory.getLogger(AuthenticationProviderImpl.class)
                            .error("Could not log user {} in", authentication.getName(), e);
                    throw new AuthenticationServiceException("Could not log in user", e);
                }
            }
        }

        return null;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
