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

package com.worthsoln.patientview;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;

public final class PatientUtils {

    private PatientUtils() {
    }

    public static String retrieveNhsNo(HttpServletRequest request) {
        User user = UserUtils.retrieveUser(request);
        UserMapping userMapping = UserUtils.retrieveUserMappingsPatientEntered(user);
        return userMapping.getNhsno();
    }

    public static Patient retrievePatient(HttpServletRequest request) {
        String nhsno = PatientUtils.retrieveNhsNo(request);
        String unitcode = UnitUtils.retrieveUnitcode(request);
        return retrievePatient(nhsno, unitcode);
    }

    public static Patient retrievePatient(String nhsno, String unitcode) {
        return LegacySpringUtils.getPatientManager().get(nhsno, unitcode);
    }
}
