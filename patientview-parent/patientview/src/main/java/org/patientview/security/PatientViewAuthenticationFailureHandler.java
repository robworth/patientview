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

package org.patientview.security;

import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handle the account locking
 */
public class PatientViewAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

    public static final String ACCOUNT_LOCKED_SESSION_TOKEN = "lockedOut";

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientViewAuthenticationFailureHandler.class);

    private int allowedfailedlogons;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        LOGGER.debug("authentication failed!");

        // bah this is deprecated, too deep in to worry about this now
        Authentication authentication = exception.getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null
                && authentication.getPrincipal() instanceof String) {

            // check if we need to lock this user account
            String username = (String) authentication.getPrincipal();

            if (exception instanceof LockedException) {

                // the account is already locked - do nothing
                LOGGER.info("Login failed {}, username: {}", exception.getMessage(), username);
                addAccountLockedTokenToSession(request);

            } else {

                // assume a failed login that contributes to an account lockout
                LegacySpringUtils.getSecurityUserManager().incrementFailedLogins(username);

                if (LegacySpringUtils.getSecurityUserManager().getFailedLogins(username) >= allowedfailedlogons) {
                    LegacySpringUtils.getSecurityUserManager().lockUserAccount(username);
                    addAccountLockedTokenToSession(request);
                    LOGGER.info("User locked out, username: {}", username);
                }
            }
        }

        super.onAuthenticationFailure(request, response, exception);
    }

    private void addAccountLockedTokenToSession(HttpServletRequest request) {
        // supply a token to indicate to the struts actions that the account is locked
        request.getSession().setAttribute(ACCOUNT_LOCKED_SESSION_TOKEN, ACCOUNT_LOCKED_SESSION_TOKEN);
    }

    public int getAllowedfailedlogons() {
        return allowedfailedlogons;
    }

    public void setAllowedfailedlogons(int allowedfailedlogons) {
        this.allowedfailedlogons = allowedfailedlogons;
    }
}
