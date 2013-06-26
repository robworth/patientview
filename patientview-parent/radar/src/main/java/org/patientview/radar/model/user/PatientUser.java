package org.patientview.radar.model.user;

import java.util.Date;

public class PatientUser extends User {

    private Long radarNumber;
    private Date dateOfBirth;

    @Override
    public String getSecurityRole() {
        return User.ROLE_PATIENT;
    }

    public Long getRadarNumber() {
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
