package com.worthsoln.security;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.DatabaseUpdateQuery;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.security.model.SecurityUser;
import com.worthsoln.service.UserManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  Hook login/auth success to implement what was in the LockOutRealm - failed login lockouts
 *
 *  todo move this sql to a proper dao managed by spring jdbc template
 */
public class PatientViewAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private DatabaseDAO dao = new DatabaseDAO("patientview");

    @Inject
    private UserManager userManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        resetFailedLoginsForUser(securityUser.getUsername());

        // remove the account locked token from the session, we don't get any incorrect login error messages
        request.getSession().setAttribute(PatientViewAuthenticationFailureHandler.ACCOUNT_LOCKED_SESSION_TOKEN, null);

        User user = userManager.get(securityUser.getUsername());

        List<TenancyUserRole> tenancyUserRoles = userManager.getTenancyUserRoles(user);

        // todo implement routing direct to target URL?
        // todo this should only take effect if spring it not in the middle of redirecting to a secured page
        // currently we push all logins through the logged_in action to ensure correct routing to admin pages

        if (tenancyUserRoles.size() > 1) {
            // if this user has multiple tenancies then route to the launchpad page
            response.sendRedirect("/launchpad.do");

        } else if (tenancyUserRoles.size() == 1) {
            // you cannot get here if you don't have at least one tenancy

            // set the users tenancy session
            Tenancy tenancy = tenancyUserRoles.get(0).getTenancy();
            securityUser.setTenancy(tenancy);

            // if this user has only a single tenancy route to the home page : /<tenancy-context>/logged_in.do
            response.sendRedirect("/" + tenancy.getContext() + "/logged_in.do");
        }
    }

    // todo work out if we need to close this connection manually?
    private void resetFailedLoginsForUser(String username) {

        String resetLoginsSql = "UPDATE user SET failedlogons = 0 WHERE username = ?";

        DatabaseUpdateQuery query = new DatabaseUpdateQuery(resetLoginsSql, new Object[]{username});
        dao.doExecute(query);
    }
}
