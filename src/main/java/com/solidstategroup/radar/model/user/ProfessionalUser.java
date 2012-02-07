package com.solidstategroup.radar.model.user;

import com.solidstategroup.radar.model.Centre;

public class ProfessionalUser extends User {

    private String surname, forename, title;
    private String gmc;
    private String role;
    private String email;
    private String phone;
    private Centre centre;

    @Override
    public String getSecurityRole() {
        for(long id : User.SUPER_USER_IDS) {
            if(getId().equals(id)) {
                return User.ROLE_SUPER_USER;
            }
        }
        return User.ROLE_PROFESSIONAL;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGmc() {
        return gmc;
    }

    public void setGmc(String gmc) {
        this.gmc = gmc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
