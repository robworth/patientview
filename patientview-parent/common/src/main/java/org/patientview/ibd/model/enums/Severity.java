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

public enum Severity {
    SEVERE(1, 16, 10),
    MODERATE(2, 8, 6),
    MILD(3, 4, 4);

    private long id;
    private int crohnsDefaultLevel;
    private int colitisDefaultLevel;

    private Severity(long id, int crohnsDefaultLevel, int colitisDefaultLevel) {
        this.id = id;
        this.crohnsDefaultLevel = crohnsDefaultLevel;
        this.colitisDefaultLevel = colitisDefaultLevel;
    }

    public static Severity getSeverity(Long id) {
        for (Severity severity : Severity.values()) {
            if (severity.getId() == id) {
                return severity;
            }
        }

        return null;
    }

    public long getId() {
        return id;
    }

    public int getDefaultLevel(Diagnosis diagnosis) {
        if (diagnosis.equals(Diagnosis.CROHNS)) {
            return crohnsDefaultLevel;
        } else {
            return colitisDefaultLevel;
        }
    }

    public int getCrohnsDefaultLevel() {
        return crohnsDefaultLevel;
    }

    public int getColitisDefaultLevel() {
        return colitisDefaultLevel;
    }
}
