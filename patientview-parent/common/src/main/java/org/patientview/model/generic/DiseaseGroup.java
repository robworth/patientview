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

package org.patientview.model.generic;

import java.io.Serializable;

/**
 * using 'unit' table in db
 * also sometimes referred to as working group
 */
public class DiseaseGroup implements Serializable, Comparable<DiseaseGroup> {
    public static final String SRNS_DISEASE_GROUP_ID = "SRNS";
    public static final String MPGN_DISEASEGROUP_ID = "MPGN";
    public static final String ALPORT_DISEASEGROUP_ID = "ALPORT";
    public static final String HNF1B_DISEASEGROUP_ID = "HNF1B";
    public static final String AHUS_DISEASEGROUP_ID = "AHUS";
    public static final String ARPKD_DISEASEGROUP_ID = "ARPKD";
    public static final String VASRDG_DISEASEGROUP_ID = "VASRDG";
    public static final String HYPALK_DISEASEGROUP_ID = "HYPALK";
    public static final String CYSTIN_DISEASEGROUP_ID = "CYSTIN";
    public static final String STECHUS_DISEASEGROUP_ID = "STECHUS";
    public static final String HYPERRDG_DISEASEGROUP_ID = "HYPERRDG";
    public static final String FUAN_DISEASEGROUP_ID = "FUAN";
    public static final String MEMRDG_DISEASEGROUP_ID = "MEMRDG";

    private String id;
    private String name;
    private String shortName;

    public int compareTo(DiseaseGroup o) {
        return name.compareTo(o.getName()); // todo might need to compare short names
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
