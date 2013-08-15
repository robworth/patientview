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

import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.SpecialtyUserRole;
import org.patientview.patientview.model.User;
import org.patientview.security.model.SecurityUser;
import org.patientview.service.SecurityUserManager;
import org.patientview.service.UserManager;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Hook login/auth success to implement what was in the LockOutRealm - failed login lockouts
 */
public class PatientViewAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Inject
    private UserManager userManager;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        LegacySpringUtils.getSecurityUserManager().resetFailedLoginsForUser(securityUser.getUsername());

        // remove the account locked token from the session, we don't get any incorrect login error messages
        request.getSession().setAttribute(PatientViewAuthenticationFailureHandler.ACCOUNT_LOCKED_SESSION_TOKEN, null);

        User user = securityUserManager.get(securityUser.getUsername());

        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(user);

        // todo implement routing direct to target URL?
        // todo this should only take effect if spring it not in the middle of redirecting to a secured page
        // currently we push all logins through the logged_in action to ensure correct routing to admin pages

        if (specialtyUserRoles.size() > 1) {
            // if this user has multiple specialties then route to the launchpad page
            response.sendRedirect("/launchpad.do");

        } else if (specialtyUserRoles.size() == 1) {
            // you cannot get here if you don't have at least one specialty

            // set the users specialty session
            Specialty specialty = specialtyUserRoles.get(0).getSpecialty();
            securityUser.setSpecialty(specialty);

            // if this user has only a single specialty route to the home page : /<specialty-context>/logged_in.do
            response.sendRedirect("/" + specialty.getContext() + "/logged_in.do");
        }
    }
}
