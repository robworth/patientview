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

package org.patientview.radar.web;

import org.patientview.radar.model.user.User;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class RadarSecuredSession extends AuthenticatedWebSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarSecuredSession.class);
    private User user;

    public RadarSecuredSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            Authentication authentication = getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            LOGGER.warn("User '{}' failed to login. Reason: {}", username, e.getMessage());
        }
        return false;

    }

    @Override
    public Roles getRoles() {
        /* get the user stored in session from login page. Did not use spring authentication.getPrincipal as the user
          set in the spring authentication is not always correct if we have two users with same credentials  e.g. for
          admin and professional with same credentials */
        Roles roles = new Roles();
        if (isSignedIn() && user != null) {
            roles.add(user.getSecurityRole());

        }
        return roles;
    }

    private ProviderManager getAuthenticationManager() {
        return (ProviderManager) WebApplicationContextUtils.getWebApplicationContext(
                WebApplication.get().getServletContext()).getBean("authenticationManager");
    }

    public User getUser() {
        /* get the user stored in session from login page. Did not use spring authentication.getPrincipal as the user
        set in the spring authentication is not always correct if we have two users with same credentials e.g. for
        admin and professional with same credentials */
        if (isSignedIn() && user != null) {
            return user;
        }
        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static RadarSecuredSession get() {
        return (RadarSecuredSession) Session.get();
    }
}
