package com.solidstategroup.radar.model.user;

import java.util.Date;

public class PatientUser extends User {

    private long radarNumber;
    private Date dateOfBirth;

    @Override
    public String getSecurityRole() {
        return User.ROLE_PATIENT;
    }

    public long getRadarNumber() {
        return radarNumber;
    }

    public void setRadarNumber(long radarNumber) {
        this.radarNumber = radarNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
