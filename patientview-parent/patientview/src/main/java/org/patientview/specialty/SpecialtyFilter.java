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

package org.patientview.specialty;

import org.patientview.patientview.model.Specialty;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *      Handles the Specialty context.
 *
 *      NOTE: this filter has no responsibility for security - that is ALL handled by spring security.
 */
public class SpecialtyFilter implements Filter {

    private FilterConfig filterConfig = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialtyFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // if there is no Specialty then skip
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty()) {

            // if there is a specialty then check for the specialty context and strip off
            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            String specialtyContext = "/" + specialty.getContext();

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String uri = request.getRequestURI();

            if (uri.startsWith(specialtyContext)) {
                // remove and forward
                String forwardUri = uri.substring(specialtyContext.length());

                filterConfig.getServletContext().getRequestDispatcher(forwardUri).forward(servletRequest,
                        servletResponse);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
