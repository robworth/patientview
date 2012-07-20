package com.worthsoln.patientview.model;

public class SplashPageUserSeen extends BaseModel {
    private String username;
    private Long splashpageid;

    public SplashPageUserSeen() {
    }

    public SplashPageUserSeen(String username, Long splashpageid) {
        this.username = username;
        this.splashpageid = splashpageid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSplashpageid() {
        return splashpageid;
    }

    public void setSplashpageid(Long splashpageid) {
        this.splashpageid = splashpageid;
    }
}