package org.patientview.radar.model.user;

public class UserMapping {

    private Long userId;
    private Long radarId;
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
