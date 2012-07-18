package com.worthsoln.patientview.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientCount extends BaseModel {

    private Calendar datestamp;
    private String unitcode;
    private String role;
    private int count;

    public PatientCount() {
    }

    public String getYearmonth() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        return dateFormat.format(datestamp.getTime());
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar datestamp) {
        this.datestamp = datestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }
}
