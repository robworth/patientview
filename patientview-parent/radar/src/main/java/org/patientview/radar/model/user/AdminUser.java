package org.patientview.radar.model.user;

public class AdminUser extends User {

    @Override
    public String getSecurityRole() {
        return User.ROLE_ADMIN;
    }
}
