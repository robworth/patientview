package com.worthsoln.patientview.model;

public class Diagnosis extends BaseModel {

    private String nhsno;
    private String unitcode;
    private String diagnosis;
    private String displayorder;

    public Diagnosis() {
    }

    public Diagnosis(String nhsno, String unitcode, String diagnosis, String displayorder) {
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.diagnosis = diagnosis;
        this.displayorder = displayorder;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }
}
