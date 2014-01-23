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

package org.patientview.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public enum NhsNumberType {

    // Note: the name needs to be in the format for the wicket pages to work.
    NHS_NUMBER(1, "NHS"),
    CHI_NUMBER(2, "CHI");

    private long id;
    private String name;

    NhsNumberType(long id, String name) {
        this.id = id;
        this.name = name;
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

    public static List<String> getNhsNumberTypeNames() {
        List<String> names = new ArrayList<String>();
        for (NhsNumberType nhsNumberType : NhsNumberType.values()) {
            names.add(nhsNumberType.getName());
        }

        return names;
    }

    public static List<NhsNumberType> getNhsNumberTypesAsList() {
        return Arrays.asList(NhsNumberType.values());
    }

    public static NhsNumberType getNhsNumberType(long id) {
        for (NhsNumberType nhsNumberType : NhsNumberType.values()) {
            if (nhsNumberType.getId() == id) {
                return nhsNumberType;
            }
        }

        return null;
    }

    public static NhsNumberType getNhsNumberType(String name) {
        if (name != null && name.length() > 0) {
            for (NhsNumberType nhsNumberType : NhsNumberType.values()) {
                if (nhsNumberType.getName().equals(name)) {
                    return nhsNumberType;
                }
            }
        }

        return null;
    }
}
