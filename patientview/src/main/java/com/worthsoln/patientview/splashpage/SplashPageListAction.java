package com.worthsoln.patientview.splashpage;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SplashPageListAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<SplashPage> splashpages = LegacySpringUtils.getSplashPageManager().getAll();
        User user = UserUtils.retrieveUser(request);
        List<Unit> usersUnits
                = LegacySpringUtils.getUnitManager().
                getLoggedInUsersUnits(new String[]{UnitUtils.PATIENT_ENTERS_UNITCODE}, new String[]{});

        if (user.getRole().equals("superadmin")) {
            Unit unitAllUnits = new Unit("ALL");
            usersUnits.add(unitAllUnits);
        }

        request.setAttribute("splashpages", splashpages);
        request.setAttribute("units", usersUnits);

        return LogonUtils.logonChecks(mapping, request);
    }
}

