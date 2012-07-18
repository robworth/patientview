package com.worthsoln.patientview.logon;

import com.worthsoln.patientview.model.BaseModel;

public class UserMapping extends BaseModel {

    private String username;
    private String unitcode;
    private String nhsno;

    public UserMapping() {
    }

    public UserMapping(String username, String unitcode, String nhsno) {
        this.username = username;
        this.unitcode = unitcode;
        this.nhsno = nhsno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }
}
