package com.worthsoln.patientview.logon;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.LogEntry;
import com.worthsoln.patientview.news.NewsUtils;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggedInAction extends DatabaseAction {

    private final DateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String forward = "";
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        NewsUtils.putAppropriateNewsForViewingInRequest(request);
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();

        if (user != null) {

            // For JForum SSO need to set some variables in the session
            request.getSession().setAttribute("sso.email.attribute", user.getEmail());
            // Not sure if this makes a difference if it's encrypted
            request.getSession().setAttribute("sso.password.attribute", user.getPassword());

            // Is user patient or admin?
            if ("patient".equals(user.getRole())) {
                request.setAttribute("isPatient", true);
            }
            if ((user.getLastlogon() != null)) {
                request.setAttribute("lastLogin", format.format(user.getLastlogon()));
            }
            user.setLastlogon(new Date());

            LegacySpringUtils.getUserManager().save(user);

            if ("patient".equals(user.getRole())) {

                String nhsno = LegacySpringUtils.getUserManager().getUsersRealNhsNoBestGuess(user.getUsername());

                if (nhsno != null && !nhsno.equals("")) {
                    LogEntry log = LegacySpringUtils.getLogEntryManager().getLatestLogEntry(nhsno,
                            AddLog.PATIENT_DATA_FOLLOWUP);
                    if (log != null) {
                        request.setAttribute("lastDataDate", log.getDate().getTime());
                        // Get the unit from the unitcode
                        String unitcode = log.getUnitcode();
                        if (unitcode != null) {
                            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
                            if (null == unit) {
                                request.setAttribute("lastDataFrom", "Unit with code: " + unitcode);
                            } else {
                                request.setAttribute("lastDataFrom", unit.getName());
                            }
                        }
                    }
                }
                forward = "patient";
            } else {
                forward = "admin";
            }
        }
        return LogonUtils.logonChecks(mapping, request, forward);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "user";
    }
}
