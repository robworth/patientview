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

package org.patientview.radar.model.generic;

import org.patientview.model.Centre;
import org.patientview.model.enums.NhsNumberType;
import org.patientview.model.generic.DiseaseGroup;

import java.io.Serializable;

public class AddPatientModel implements Serializable {
    private String patientId;
    private NhsNumberType nhsNumberType;
    private DiseaseGroup diseaseGroup;
    private Centre centre;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public NhsNumberType getNhsNumberType() {
        return nhsNumberType;
    }

    public void setNhsNumberType(NhsNumberType nhsNumberType) {
        this.nhsNumberType = nhsNumberType;
    }

    public void setNhsNumberType(String nhsNumberTypeId) {
        this.nhsNumberType = NhsNumberType.getNhsNumberType(Long.parseLong(nhsNumberTypeId));
    }

    public DiseaseGroup getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(DiseaseGroup diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
