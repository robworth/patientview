package com.worthsoln.forum;

import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;
import net.jforum.util.legacy.clickstream.ClickstreamFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Check for screen name of user and redirect if one isn't set
 */
public class RpvForumFilter extends ClickstreamFilter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String screenName = null;
        // Check whether screen name is set
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();

        if (user != null) {
            screenName = user.getScreenname();
        }

        if (screenName != null && screenName.trim().length() > 0) {
            super.doFilter(request, response, chain);
        } else {
            request.getRequestDispatcher("/setscreenname.do").forward(request, response);
        }
    }
}
