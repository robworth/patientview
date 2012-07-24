package com.worthsoln.patientview.splashpage;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class SplashPageUtils {

    public static List<SplashPage> retrieveSplashPagesForPatient(User user) {
        return LegacySpringUtils.getSplashPageManager().getAllForPatient(user);
    }

    public static List<SplashPageUserSeen> retrieveSplashPagesPatientHasSeen(User user) {

        return LegacySpringUtils.getSplashPageManager().getSeenForPatient(user);
    }

    static void putSplashPageUnitsInRequest(HttpServletRequest request) throws Exception {
        List<SplashPage> splashpages = LegacySpringUtils.getSplashPageManager().getAll();
        String[] unitsWithSplashPages = new String[splashpages.size() + 1];

        boolean allUnitsSplashPageAlreadyExists = false;

        for (int i = 0; i < splashpages.size(); i++) {
            unitsWithSplashPages[i] = splashpages.get(i).getUnitcode();
            if (unitsWithSplashPages[i].equals("ALL")) {
                allUnitsSplashPageAlreadyExists = true;
            }
        }

        unitsWithSplashPages[splashpages.size()] = UnitUtils.PATIENT_ENTERS_UNITCODE;

        final boolean isSuperAdmin = LegacySpringUtils.getSecurityUserManager().isRolePresent("superadmin");

        if (isSuperAdmin && !allUnitsSplashPageAlreadyExists) {
            UnitUtils.putRelevantUnitsInRequestMinusSomeUnits(request, unitsWithSplashPages, new String[]{"ALL"});
        } else {
            UnitUtils.putRelevantUnitsInRequestMinusSomeUnits(request, unitsWithSplashPages, new String[]{});
        }
    }
}