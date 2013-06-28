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

package org.patientview.patientview;

import org.patientview.patientview.model.EdtaCode;
import org.patientview.patientview.model.Patient;
import org.patientview.patientview.uktransplant.UktStatusForPatient;
import org.patientview.patientview.model.Unit;

import java.util.List;


public class PatientDetails {

    private Patient patient;
    private Unit unit;
    private EdtaCode edtaDiagnosis;
    private EdtaCode edtaTreatment;
    private UktStatusForPatient uktStatus;
    private List otherDiagnoses;

    public PatientDetails() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public EdtaCode getEdtaDiagnosis() {
        return edtaDiagnosis;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setEdtaDiagnosis(EdtaCode diagnosis) {
        this.edtaDiagnosis = diagnosis;
    }

    public EdtaCode getEdtaTreatment() {
        return edtaTreatment;
    }

    public void setEdtaTreatment(EdtaCode edtaTreatment) {
        this.edtaTreatment = edtaTreatment;
    }

    public UktStatusForPatient getUktStatus() {
        return uktStatus;
    }

    public void setUktStatus(UktStatusForPatient uktStatus) {
        this.uktStatus = uktStatus;
    }

    public List getOtherDiagnoses() {
        return otherDiagnoses;
    }

    public void setOtherDiagnoses(List otherDiagnoses) {
        this.otherDiagnoses = otherDiagnoses;
    }
}
