package com.solidstategroup.radar.model.user;

import com.solidstategroup.radar.model.BaseModel;

import java.util.Date;

public class BaseUser extends BaseModel {

    private String username, password;
    private Date dateRegistered;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
