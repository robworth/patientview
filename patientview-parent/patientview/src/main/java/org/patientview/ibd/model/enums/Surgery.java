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

public enum Surgery {
    NO_PREVIOUS_OPERATIONS(1, "No previous operations"),
    PREVIOUS_APPENDECTOMY(2, "Previous appendectomy"),
    SETON_PLACEMENT(3, "Seton placement"),
    ABSCESS_INICISION_AND_DRAINAGE(4, "Abscess Incision and Drainage"),
    DOUBLE_BALLOON_ENTEROSTOMY(5, "Double Balloon enterostomy"),
    TOTAL_COLECTOMY_AND_ILEOSTOMY(6, "Total Colectomy and ileostomy"),
    SUB_TOTAL_COLECTOMY_AND_ILEOSTOMY(7, "Sub-total Colectomy and ileostomy"),
    SMALL_BOWEL_RESECTION(8, "Small bowel resection"),
    LARGE_BOWEL_RESECTION(9, "Large bowel resection"),
    ILEO_CAECAL_RESECTION(10, "Ileo-caecal  resection"),
    POUCH_FORMATION(11, "Pouch formation"),
    EUA(12, "E.U.A");

    private long id;
    private String name;

    private Surgery(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Surgery getSurgery(Long id) {
        for (Surgery surgery : Surgery.values()) {
            if (surgery.getId() == id) {
                return surgery;
            }
        }

        return NO_PREVIOUS_OPERATIONS;
    }

    public static Surgery getSurgery(String name) {
        for (Surgery surgery : Surgery.values()) {
            if (surgery.getName().equals(name)) {
                return surgery;
            }
        }

        return NO_PREVIOUS_OPERATIONS;
    }

    public static List<Surgery> getAsList() {
        return Arrays.asList(Surgery.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
