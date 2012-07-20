package com.worthsoln.patientview;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.uktransplant.UktStatusForPatient;
import com.worthsoln.patientview.model.Unit;

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
