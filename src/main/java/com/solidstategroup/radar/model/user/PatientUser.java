package com.solidstategroup.radar.model.user;

import java.util.Date;

public class PatientUser extends BaseUser {

    private int radarNumber;
    private Date dateOfBirth;

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
