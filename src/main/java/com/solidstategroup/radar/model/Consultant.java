package com.solidstategroup.radar.model;


public class Consultant extends BaseModel{
    private String surname;
    private String firstName;
    private Centre centre;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
