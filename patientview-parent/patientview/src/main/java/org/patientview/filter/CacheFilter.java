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

package org.patientview.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *      Handles the response headers with no cache
 *
 *      NOTE: this filter has no responsibility for security - that is ALL handled by spring security.
 */
public class CacheFilter implements Filter {

    private FilterConfig filterConfig = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
