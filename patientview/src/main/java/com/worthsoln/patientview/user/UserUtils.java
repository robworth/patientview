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

package com.worthsoln.patientview.user;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public final class UserUtils {

    private static final int NHSNO_LENGTH = 10;
    private static final int LAST_MAIN_DIGIT_POSITION = 8;
    private static final int CHECKSUM_DIGIT_POSITION = 9;
    private static final int TEN = 10;
    private static final int CHECKSUM_MODULUS = 11;

    private UserUtils() {

    }

    public static User retrieveUser(HttpServletRequest request) {
        String username = null;

        if (!LegacySpringUtils.getSecurityUserManager().isRolePresent("patient")) {
            username = (String) request.getSession().getAttribute("userBeingViewedUsername");
        }

        if (username == null || "".equals(username)) {
            username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        }

        return LegacySpringUtils.getUserManager().get(username);
    }

    public static List<UserMapping> retrieveUserMappings(User user) {
        return LegacySpringUtils.getUserManager().getUserMappings(user.getUsername());
    }

    public static String retrieveUsersRealUnitcodeBestGuess(String username) {
        return LegacySpringUtils.getUserManager().getUsersRealUnitcodeBestGuess(username);
    }

    public static String retrieveUsersRealNhsnoBestGuess(String username) {
        return LegacySpringUtils.getUserManager().getUsersRealNhsNoBestGuess(username);
    }

    public static UserMapping retrieveUserMappingsPatientEntered(User user) {
        return LegacySpringUtils.getUserManager().getUserMappingPatientEntered(user);
    }

    public static void removePatientFromSystem(String nhsno, String unitcode) {
        LegacySpringUtils.getPatientManager().removePatientFromSystem(nhsno, unitcode);
    }

    public static boolean nhsNumberChecksumValid(String nhsno) {
        if (nhsno.length() != NHSNO_LENGTH) {
            return false;
        } else { //generate the checksum using modulus 11 algorithm
            int checksum = 0;

            //multiply each of the first 9 digits by 10-character position (where the left character is in position 0)
            for (int i = 0; i <= LAST_MAIN_DIGIT_POSITION; i++) { //sum of each
                int digit = Integer.parseInt(nhsno.substring(i, i + 1));
                checksum += digit * (TEN - i);
            }
            //(modulus 11)
            checksum = CHECKSUM_MODULUS - checksum % CHECKSUM_MODULUS;

            if (checksum == CHECKSUM_MODULUS) {
                checksum = 0;
            }

            //does checksum match the 10th digit?
            return checksum == Integer.parseInt(nhsno.substring(CHECKSUM_DIGIT_POSITION));
        }
    }
}
