package com.worthsoln.tenancy;

import com.worthsoln.patientview.model.Tenancy;
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
 *      Handles the tenancy context.
 *
 *      NOTE: this filter has no responsibility for security - that is ALL handled by spring security.
 */
public class TenancyFilter implements Filter {

    private FilterConfig filterConfig = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(TenancyFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // if there is no tenancy then skip
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToTenancy()) {

            // if there is a tenancy then check for the tenancy context and strip off
            Tenancy tenancy = LegacySpringUtils.getSecurityUserManager().getLoggedInTenancy();

            String tenancyContext = "/" + tenancy.getContext();

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String uri = request.getRequestURI();

            if (uri.startsWith(tenancyContext)) {
                // remove and forward
                String forwardUri = uri.substring(tenancyContext.length());

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
