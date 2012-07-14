package com.worthsoln.patientview.splashpage;

public class SplashPageUserSeen {
    private int id;
    private String username;
    private int splashpageid;

    public SplashPageUserSeen() {
    }

    public SplashPageUserSeen(String username, int splashpageid) {
        this.username = username;
        this.splashpageid = splashpageid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSplashpageid() {
        return splashpageid;
    }

    public void setSplashpageid(int splashpageid) {
        this.splashpageid = splashpageid;
    }
}