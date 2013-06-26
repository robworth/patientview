/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.patientview.splashpage;

import org.patientview.patientview.model.SplashPage;
import org.patientview.patientview.model.SplashPageUserSeen;
import org.patientview.patientview.model.User;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public final class SplashPageUtils {

    private SplashPageUtils() {
    }

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
