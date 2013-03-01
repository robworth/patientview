package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SplashPageUserSeen extends BaseModel {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
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