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

public enum FamilyHistory {
    NONE(1, "None"),
    CROHNS_DISEASE(2, "Crohn's Disease"),
    ULCERATIVE_COLITIS(3, "Ulcerative Colitis"),
    BOWEL_CANCER(4, "Bowel Cancer");

    private long id;
    private String name;

    private FamilyHistory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FamilyHistory getFamilyHistory(Long id) {
        for (FamilyHistory familyHistory : FamilyHistory.values()) {
            if (familyHistory.getId() == id) {
                return familyHistory;
            }
        }

        return NONE;
    }

    public static FamilyHistory getFamilyHistory(String name) {
            for (FamilyHistory familyHistory : FamilyHistory.values()) {
                if (familyHistory.getName() == name) {
                    return familyHistory;
                }
            }

            return NONE;
        }

    public static List<FamilyHistory> getAsList() {
        return Arrays.asList(FamilyHistory.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
