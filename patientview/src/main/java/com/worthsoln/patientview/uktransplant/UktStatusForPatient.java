package com.worthsoln.patientview.uktransplant;

public class UktStatusForPatient {

    private String nhsno;
    private String uktkidney;
    private String uktpancreas;

    public UktStatusForPatient() {
    }

    public UktStatusForPatient(String nhsno, String uktkidney, String uktpancreas) {
        this.nhsno = nhsno;
        this.uktkidney = uktkidney;
        this.uktpancreas = uktpancreas;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUktkidney() {
        return uktkidney;
    }

    public void setUktkidney(String uktkidney) {
        this.uktkidney = uktkidney;
    }

    public String getUktpancreas() {
        return uktpancreas;
    }

    public void setUktpancreas(String uktpancreas) {
        this.uktpancreas = uktpancreas;
    }
}
