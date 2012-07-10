package com.worthsoln.security;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.DatabaseUpdateQuery;
import org.apache.commons.dbutils.ResultSetHandler;
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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Handle the account locking
 */
public class PatientViewAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

    public static final String ACCOUNT_LOCKED_SESSION_TOKEN = "lockedOut";

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientViewAuthenticationFailureHandler.class);

    private DatabaseDAO dao = new DatabaseDAO("patientview");

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
                incrementFailedLogins(username);

                if (getFailedLogins(username) >= allowedfailedlogons) {

                    lockUserAccount(username);
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

    private void incrementFailedLogins(String username) {

        String incrementSql = "UPDATE user SET failedlogons = failedlogons + 1 WHERE username = ?";

        DatabaseUpdateQuery query = new DatabaseUpdateQuery(incrementSql, new Object[]{username});
        dao.doExecute(query);
    }

    private int getFailedLogins(String username) {

        String failedLoginsSql =  "SELECT failedlogons FROM user WHERE username = ?";
        DatabaseQuery query = new DatabaseQuery(failedLoginsSql, new Object[]{username},
                new ResultSetHandler() {
            @Override
            public Integer handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return resultSet.getInt("failedlogons");
                } else {
                    return 0;
                }
            }
        });

        return (Integer) dao.doExecuteQuery(query);
    }

    private void lockUserAccount(String username) {

        String resetLoginsSql = "UPDATE user SET accountlocked = 1 WHERE username = ?";

        DatabaseUpdateQuery query = new DatabaseUpdateQuery(resetLoginsSql, new Object[]{username});
        dao.doExecute(query);
    }
}
