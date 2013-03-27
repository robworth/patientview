package com.solidstategroup.radar.model.user;

public class AdminUser extends User {

    private String name;

    @Override
    public String getSecurityRole() {
        return User.ROLE_ADMIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
