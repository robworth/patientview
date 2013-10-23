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

public enum Smoking {
    NEVER_SMOKED(1, "Never smoked"),
    PREVIOUSLY_SMOKED(2, "Previously smoked"),
    CURRENT_SMOKER(3, "Current smoker");

    private long id;
    private String name;

    private Smoking(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Smoking getSmoking(Long id) {
        for (Smoking smoking : Smoking.values()) {
            if (smoking.getId() == id) {
                return smoking;
            }
        }

        return NEVER_SMOKED;
    }

    public static Smoking getSmoking(String name) {
        for (Smoking smoking : Smoking.values()) {
            if (smoking.getName().equals(name)) {
                return smoking;
            }
        }

        return NEVER_SMOKED;
    }

    public static List<Smoking> getAsList() {
        return Arrays.asList(Smoking.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
