package com.worthsoln.specialty;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.utils.LegacySpringUtils;
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
