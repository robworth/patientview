package com.worthsoln.patientview.logon;

public class PatientLogonWithTreatment extends PatientLogon {

    private String treatment;

    public PatientLogonWithTreatment() {
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
