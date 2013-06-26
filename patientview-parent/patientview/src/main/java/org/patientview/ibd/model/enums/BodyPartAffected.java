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

public enum BodyPartAffected {
    NONE(1, "None"),
    PRIMARY_SCLEROSING_CHOLANGITIS(2, "Primary Sclerosing Cholangitis"),
    NON_SPECIFIC_ARTRALGIA(3, "Non-specific arthralgia"),
    TYPE_1_ARTHRITIS(4, "Type 1 arthritis (>5 joints, worse with active disease)"),
    TYPE_2_ARTHRITIS(5, "Type 2 arthritis (<5 joints, small joints, symmetrical)"),
    SACROILILITIS(6, "Sacroililitis"),
    ANKYLOSING_SPONDYLITIS(7, "Ankylosing Spondylitis"),
    ERYTHEMA_NODOSUM(8, "Erythema Nodosum"),
    PYODERMA_GANGRENOSUM(9, "Pyoderma Gangrenosum"),
    EPISCLERITIS(10, "Episcleritis"),
    UVEITIS(11, "Uveitis");

    private long id;
    private String name;

    private BodyPartAffected(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BodyPartAffected getBodyPartAffected(Long id) {
        for (BodyPartAffected bodyPartAffected : BodyPartAffected.values()) {
            if (bodyPartAffected.getId() == id) {
                return bodyPartAffected;
            }
        }

        return NONE;
    }

    public static BodyPartAffected getBodyPartAffected(String name) {
        for (BodyPartAffected bodyPartAffected : BodyPartAffected.values()) {
            if (bodyPartAffected.getName() == name) {
                return bodyPartAffected;
            }
        }

        return NONE;
    }

    public static List<BodyPartAffected> getAsList() {
        return Arrays.asList(BodyPartAffected.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
