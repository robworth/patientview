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

import org.patientview.ibd.Ibd;

import java.util.Arrays;
import java.util.List;

public enum Diagnosis {
    ULCERATIVE_COLITIS(1, "Ulcerative colitis", Ibd.ULCERATIVE_COLITIS_DIAGRAM, "Ulcerative Colitis"),
    COLITIS_UNSPECIFIED(2, "Colitis unspecified", Ibd.COLITIS_UNSPECIFIED_DIAGRAM, "IBD - Unclassified (IBDU)"),
    CROHNS(3, "Crohn's", Ibd.CROHNS_DIAGRAM, "Crohn's Disease");

    private long id;
    private String name;
    private String diagram;
    private String xmlName;

    private Diagnosis(long id, String name, String diagram, String xmlName) {
        this.id = id;
        this.name = name;
        this.diagram = diagram;
        this.xmlName = xmlName;
    }

    public static Diagnosis getDiagnosis(Long id) {
        for (Diagnosis diagnosis : Diagnosis.values()) {
            if (diagnosis.getId() == id) {
                return diagnosis;
            }
        }

        return null;
    }

    public static Diagnosis getDiagnosisByXmlName(String name) {
        for (Diagnosis diagnosis : Diagnosis.values()) {
            if (diagnosis.getXmlName().equalsIgnoreCase(name)) {
                return diagnosis;
            }
        }

        return null;
    }

    public static List<Diagnosis> getAsList() {
        return Arrays.asList(Diagnosis.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDiagram() {
        return diagram;
    }

    public String getXmlName() {
        return xmlName;
    }
}
