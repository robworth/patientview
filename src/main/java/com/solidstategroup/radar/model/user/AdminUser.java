package com.solidstategroup.radar.model.user;

public class AdminUser extends User {
    
    private String name;
    private String email;
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
