package org.patientview.radar.model.user;

import org.patientview.radar.model.Centre;

public class ProfessionalUser extends User {

    // super users are hardcoded by id in the previous implementation
    private static final long[] SUPER_USER_IDS = {28, 15};

    private String surname = "", forename = "", title = "", gmc = "", role = "", phone = ""
            , securityQuestion = "", securityQuestionAnsw = "";
    private Centre centre = new Centre();

    @Override
    public String getSecurityRole() {
        for(long id : SUPER_USER_IDS) {
            if (getId().equals(id)) {
                return User.ROLE_SUPER_USER;
            }
        }
        return User.ROLE_PROFESSIONAL;
    }

    @Override
    public String getName() {
        return forename + " " + surname;
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

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityQuestionAnsw() {
        return securityQuestionAnsw;
    }

    public void setSecurityQuestionAnsw(String securityQuestionAnsw) {
        this.securityQuestionAnsw = securityQuestionAnsw;
    }
}
