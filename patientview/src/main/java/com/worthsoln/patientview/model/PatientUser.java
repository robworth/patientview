package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "tbl_patient_users")
public class PatientUser extends BaseModel {

    @Column(name = "pID")
    private Integer patientUserId;

    @Column(name = "radar_no")
    private long radarNo;

    public Integer getPatientUserId() {
        return patientUserId;
    }

    public void setPatientUserId(Integer patientUserId) {
        this.patientUserId = patientUserId;
    }

    public long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(long radarNo) {
        this.radarNo = radarNo;
    }
}
