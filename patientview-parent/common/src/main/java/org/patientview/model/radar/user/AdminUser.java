package org.patientview.model.radar.user;

public class AdminUser extends User {

    @Override
    public String getSecurityRole() {
        return User.ROLE_ADMIN;
    }
}
