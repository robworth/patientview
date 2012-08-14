package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ibd_crohns")
public class Crohns extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date chornsDate;

    @Column(nullable = false)
    private int abdominalPain;

    @Column(nullable = false)
    private int openBowels;

    @Column(nullable = false)
    private int feeling;

    @Column(nullable = false)
    private int complications;

    @Column(nullable = false)
    private int massInTummy;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Date getChornsDate() {
        return chornsDate;
    }

    public void setChornsDate(Date chornsDate) {
        this.chornsDate = chornsDate;
    }

    public int getAbdominalPain() {
        return abdominalPain;
    }

    public void setAbdominalPain(int abdominalPain) {
        this.abdominalPain = abdominalPain;
    }

    public int getOpenBowels() {
        return openBowels;
    }

    public void setOpenBowels(int openBowels) {
        this.openBowels = openBowels;
    }

    public int getFeeling() {
        return feeling;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }

    public int getComplications() {
        return complications;
    }

    public void setComplications(int complications) {
        this.complications = complications;
    }

    public int getMassInTummy() {
        return massInTummy;
    }

    public void setMassInTummy(int massInTummy) {
        this.massInTummy = massInTummy;
    }
}
