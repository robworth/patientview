package com.worthsoln.patientview.user;

public class NhsnoUnitcode {
    private String nhsno;
    private String unitcode;

    public NhsnoUnitcode(String nhsno, String unitcode) {
        this.nhsno = nhsno;
        this.unitcode = unitcode;
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
        this.unitcode = unitcode;
    }
}

