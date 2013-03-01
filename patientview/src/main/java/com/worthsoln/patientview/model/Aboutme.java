package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Aboutme extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = true)
    private String aboutme;

    @Column(nullable = true)
    private String talkabout;

    public Aboutme() {
    }

    public Aboutme(String nhsno, String aboutme, String talkabout) {
        this.nhsno = nhsno;
        this.aboutme = aboutme;
        this.talkabout = talkabout;
    }

    public Aboutme(Long id, String nhsno, String aboutme, String talkabout) {
        this.setId(id);
        this.nhsno = nhsno;
        this.talkabout = talkabout;
        this.aboutme = aboutme;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getTalkabout() {
        return talkabout;
    }

    public void setTalkabout(String talkabout) {
        this.talkabout = talkabout;
    }
}
