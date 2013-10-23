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

package org.patientview.patientview.model.enums;

/**
 *
 */
public enum DiagnosticType {
    IMAGING(1, "Imaging"),
    ENDOSCOPY(2, "Endoscopy");

    private int id;
    private String name;

    private DiagnosticType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DiagnosticType getDiagnosticType(int id) {
        for (DiagnosticType type : DiagnosticType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }

        return null;
    }

    public static DiagnosticType getDiagnosticType(String s) {
        for (DiagnosticType type : DiagnosticType.values()) {
            if (type.getName().equalsIgnoreCase(s.trim())) {
                return type;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
