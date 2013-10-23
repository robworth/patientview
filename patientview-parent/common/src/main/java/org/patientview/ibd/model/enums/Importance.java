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

public enum Importance {
    NOT_IMPORTANT_AT_ALL(1, "Not important at all"),
    SOMEWHAT_IMPORTANT(2, "Somewhat important"),
    IMPORTANT(3, "Important"),
    VERY_IMPORTANT(6, "Very important");

    private long id;
    private String name;

    private Importance(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Importance getImportance(Long id) {
        for (Importance importance : Importance.values()) {
            if (importance.getId() == id) {
                return importance;
            }
        }

        return NOT_IMPORTANT_AT_ALL;
    }

    public static List<Importance> getAsList() {
        return Arrays.asList(Importance.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
