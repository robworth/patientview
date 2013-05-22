package com.worthsoln.patientview.model.radar;

import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "rdr_user_mapping")
public class RadarUserMapping extends BaseModel {

    @Column
    private Long userId;

    @Column(name = "radarUserId")
    private Long radarId;

    @Column
    private String role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRadarId() {
        return radarId;
    }

    public void setRadarId(Long radarId) {
        this.radarId = radarId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
