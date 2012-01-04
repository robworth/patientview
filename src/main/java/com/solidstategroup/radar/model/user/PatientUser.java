package com.solidstategroup.radar.model.user;

import java.util.Date;

public class PatientUser extends User {

    private int radarNumber;
    private Date dateOfBirth;

    @Override
    public String getSecurityRole() {
        return User.ROLE_PATIENT;
    }

    public int getRadarNumber() {
        return radarNumber;
    }

    public void setRadarNumber(int radarNumber) {
        this.radarNumber = radarNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
