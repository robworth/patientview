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

package org.patientview.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Confidence {

    NOT_CONFIDENT(1, "Not confident"),
    SOMEWHAT_CONFIDENT(2, "Somewhat confident"),
    CONFIDENT(3, "Confident"),
    VERY_CONFIDENT(4, "Very confident");

    private long id;
    private String name;

    private Confidence(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Confidence getConfidence(Long id) {
        for (Confidence confidence : Confidence.values()) {
            if (confidence.getId() == id) {
                return confidence;
            }
        }

        return NOT_CONFIDENT;
    }

    public static List<Confidence> getAsList() {
        return Arrays.asList(Confidence.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
