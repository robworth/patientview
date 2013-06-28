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

package org.patientview.ibd.model.medication.enums;

import java.util.Arrays;
import java.util.List;

public enum MedicationFrequency {
    ONCE_A_DAY(1, "Once a day"),
    ONCE_A_DAY_IN_THE_MORNING(2, "Once a day in the morning"),
    TWICE_A_DAY(3, "Twice a day"),
    THREE_A_DAY(4, "Three a day"),
    FOUR_A_DAY(5, "Four a day"),
    AT_NIGHT(6, "At night"),
    ONCE_A_WEEK(7, "Once a week"),
    ONCE_A_WEEK_BY_TABLET(8, "Once a week by tablet"),
    ONCE_A_WEEK_BY_INJECTION(9, "Once a week by injection"),
    EVERY_2_WEEKS(10, "Every 2 weeks"),
    EVERY_4_WEEKS(11, "Every 4 weeks"),
    EVERY_8_WEEKS(12, "Every 8 weeks"),
    EVERY_3_MONTHS(13, "Every 3 months"),
    AS_REQUIRED(14, "As required"),
    NA(15, "N/A");

    private long id;
    private String name;

    private MedicationFrequency(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MedicationFrequency getMedicineFrequency(Long id) {
        for (MedicationFrequency medicineFrequency : MedicationFrequency.values()) {
            if (medicineFrequency.getId() == id) {
                return medicineFrequency;
            }
        }

        return null;
    }

    public static List<MedicationFrequency> getAsList() {
        return Arrays.asList(MedicationFrequency.values());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
