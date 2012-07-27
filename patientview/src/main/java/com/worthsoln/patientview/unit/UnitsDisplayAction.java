package com.worthsoln.patientview.unit;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;

public class UnitsDisplayAction extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = LegacySpringUtils.getUserManager().getLoggedInUser();

        List items;
        if (user.getRole().equals("superadmin")) {
            items = LegacySpringUtils.getUnitManager().getAll(true);
        } else {
            items = LegacySpringUtils.getUnitManager().getLoggedInUsersUnits();
        }

        request.getSession().setAttribute("units", items);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "unit";
    }
}
