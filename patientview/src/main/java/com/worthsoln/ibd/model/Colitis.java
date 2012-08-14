package com.worthsoln.ibd.model;

import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ibd_colitis")
public class Colitis extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date colitisDate;

    @Column(nullable = false)
    private int stoolsDay;

    @Column(nullable = false)
    private int stoolsNight;

    @Column(nullable = false)
    private int toiletTiming;

    @Column(nullable = false)
    private int presentBlood;

    @Column(nullable = false)
    private int feeling;

    @Column(nullable = false)
    private int furtherComplications;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Date getColitisDate() {
        return colitisDate;
    }

    public void setColitisDate(Date colitisDate) {
        this.colitisDate = colitisDate;
    }

    public int getStoolsDay() {
        return stoolsDay;
    }

    public void setStoolsDay(int stoolsDay) {
        this.stoolsDay = stoolsDay;
    }

    public int getStoolsNight() {
        return stoolsNight;
    }

    public void setStoolsNight(int stoolsNight) {
        this.stoolsNight = stoolsNight;
    }

    public int getToiletTiming() {
        return toiletTiming;
    }

    public void setToiletTiming(int toiletTiming) {
        this.toiletTiming = toiletTiming;
    }

    public int getPresentBlood() {
        return presentBlood;
    }

    public void setPresentBlood(int presentBlood) {
        this.presentBlood = presentBlood;
    }

    public int getFeeling() {
        return feeling;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }

    public int getFurtherComplications() {
        return furtherComplications;
    }

    public void setFurtherComplications(int furtherComplications) {
        this.furtherComplications = furtherComplications;
    }
}
