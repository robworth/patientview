package com.worthsoln.security;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.DatabaseUpdateQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Hook login/auth success to implement what was in the LockOutRealm - failed login lockouts
 *
 *  todo move this sql to a proper dao managed by spring jdbc template
 */
public class PatientViewAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private DatabaseDAO dao = new DatabaseDAO("patientview");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        User user = (User) authentication.getPrincipal();
        resetFailedLoginsForUser(user.getUsername());

        // remove the account locked token from the session, we don't get any incorrect login error messages
        request.getSession().setAttribute(PatientViewAuthenticationFailureHandler.ACCOUNT_LOCKED_SESSION_TOKEN, null);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    // todo work out if we need to close this connection manually?
    private void resetFailedLoginsForUser(String username) {

        String resetLoginsSql = "UPDATE user SET failedlogons = 0 WHERE username = ?";

        DatabaseUpdateQuery query = new DatabaseUpdateQuery(resetLoginsSql, new Object[]{username});
        dao.doExecute(query);
    }
}
