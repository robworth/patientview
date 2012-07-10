package com.worthsoln.patientview.contact;

import com.worthsoln.patientview.Patient;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.unit.Unit;

public class Contact {

    private Patient patient;
    private Unit unit;
    private UserMapping usermapping;

    public Contact() {
    }

    public Contact(Patient patient, Unit unit, UserMapping usermapping) {
        this.patient = patient;
        this.unit = unit;
        this.usermapping = usermapping;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public UserMapping getUsermapping() {
        return usermapping;
    }

    public void setUsermapping(UserMapping usermapping) {
        this.usermapping = usermapping;
    }
}
