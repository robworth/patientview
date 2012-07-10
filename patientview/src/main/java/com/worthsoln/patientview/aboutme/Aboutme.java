package com.worthsoln.patientview.aboutme;


public class Aboutme {

    private int id;
    private String nhsno;
    private String aboutme;
    private String talkabout;

    public Aboutme() {
    }

    public Aboutme(String nhsno, String aboutme, String talkabout) {
        this.nhsno = nhsno;
        this.aboutme = aboutme;
        this.talkabout = talkabout;
    }

    public Aboutme(int id, String nhsno, String aboutme, String talkabout) {
        this.id = id;
        this.nhsno = nhsno;
        this.talkabout = talkabout;
        this.aboutme = aboutme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
