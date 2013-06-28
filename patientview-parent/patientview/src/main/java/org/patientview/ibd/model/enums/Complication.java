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

public enum Complication {
    NONE(1, "None"),
    PERI_ANAL_DISEASE(2, "Peri-anal disease"),
    PERFORATING_DISEASE_FISTULATION(3, "Perforating Disease / Fistulation"),
    SMALL_BOWEL_STRICTURING(4, "Small bowel stricturing"),
    LARGE_BOWEL_STRICTURING(5, "Large bowel stricturing"),
    ABSCESS(6, "Abscess"),
    GASTRO_DUODENAL_CROHNS(7, "Gasto-duodenal Crohn's"),
    ORAL_CROHNS(8, "Oral Crohn's"),
    LOW_GRADE_COLONIC_DYSPLASIA(9, "Low grade colonic dysplasia"),
    HIGH_GRADE_COLONIC_DYSPLASIA(10, "High grade colonic dysplasia"),
    INTESTINAL_FAILURE(11, "Intestinal failure"),
    OSTEOPAENIA(12, "Osteopaenia"),
    OSTEOPORSOSIS(13, "Osteoporsosis"),
    IRON_DEFICIENCY(14, "Iron deficiency"),
    PROXIMAL_CONSTIPATION(15, "Proximal constipation");

    private long id;
    private String name;

    private Complication(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Complication getComplication(Long id) {
        for (Complication complication : Complication.values()) {
            if (complication.getId() == id) {
                return complication;
            }
        }

        return NONE;
    }

    public static Complication getComplication(String name) {
            for (Complication complication : Complication.values()) {
                if (complication.getName() == name) {
                    return complication;
                }
            }

            return NONE;
        }

    public static List<Complication> getAsList() {
        return Arrays.asList(Complication.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
