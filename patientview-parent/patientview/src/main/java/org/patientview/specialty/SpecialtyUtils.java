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

package org.patientview.specialty;

import org.patientview.patientview.model.Specialty;
import org.patientview.utils.LegacySpringUtils;


/**
 * todo spring bean?
 */
public final class SpecialtyUtils {

    private SpecialtyUtils() {

    }

    public static String rewriteSpecialtyUriRemoveContext(String uri) {

        String result = uri;

        // if there is no Specialty then skip
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty() && uri != null) {

            // if there is a specialty then check for the specialty context and strip off
            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            String specialtyContext = "/" + specialty.getContext();

            if (uri.startsWith(specialtyContext)) {
                // remove and forward
                result = uri.substring(specialtyContext.length());
            }
        }

        return result;
    }

    public static String rewriteSpecialtyUrlAddContext(String uri) {

        if (uri != null) {

            // check for leading double slash
            if (uri.startsWith("//")) {
                uri = uri.substring(1);
            }

            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            String context = specialty != null ? "/" + specialty.getContext() : "";

            return context + uri;
        } else {
            return "";
        }
    }
}
