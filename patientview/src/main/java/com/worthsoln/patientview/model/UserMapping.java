package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserMapping extends BaseModel {

    @Column
    private String username;

    @Column
    private String unitcode;

    @Column
    private String nhsno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    public UserMapping() {
    }

    public UserMapping(String username, String unitcode, String nhsno) {
        this.username = username;
        this.unitcode = unitcode;
        this.nhsno = nhsno;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }
}
