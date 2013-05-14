package com.worthsoln.patientview.logon;

import com.worthsoln.ibd.Ibd;

import java.util.Date;

public class PatientLogonWithTreatment extends PatientLogon {

    private String treatment;
    private Date dateofbirth;

    public PatientLogonWithTreatment() {
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getDateofbirthFormatted() {
        if (dateofbirth != null) {
            return Ibd.DATE_FORMAT.format(dateofbirth);
        }

        return "";
    }
}
