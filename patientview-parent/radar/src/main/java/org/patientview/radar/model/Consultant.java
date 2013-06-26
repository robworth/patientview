package org.patientview.radar.model;

public class Consultant extends BaseModel {

    private String surname;
    private String forename;
    private Centre centre;

    public String getFullName() {
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

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
