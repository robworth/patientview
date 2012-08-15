package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.ibd.model.enums.Feeling;
import com.worthsoln.ibd.model.enums.colitis.Complication;
import com.worthsoln.ibd.model.enums.colitis.NumberOfStoolsDaytime;
import com.worthsoln.ibd.model.enums.colitis.NumberOfStoolsNighttime;
import com.worthsoln.ibd.model.enums.colitis.PresentBlood;
import com.worthsoln.ibd.model.enums.colitis.ToiletTiming;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "ibd_colitis")
public class Colitis extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date colitisDate;

    @Transient
    private NumberOfStoolsDaytime numberOfStoolsDaytime;

    @Access(AccessType.PROPERTY)
    @Column(name = "number_of_stools_daytime_id", nullable = false)
    public int getNumberOfStoolsDaytimeId() {
        if (numberOfStoolsDaytime != null) {
            return numberOfStoolsDaytime.getId();
        }

        return -1;
    }

    public void setNumberOfStoolsDaytimeId(int id) {
        this.numberOfStoolsDaytime = NumberOfStoolsDaytime.getNumberOfStoolsDaytime(id);
    }

    public NumberOfStoolsDaytime getNumberOfStoolsDaytime() {
        return numberOfStoolsDaytime;
    }

    public void setNumberOfStoolsDaytime(NumberOfStoolsDaytime toiletTiming) {
        this.numberOfStoolsDaytime = toiletTiming;
    }

    @Transient
    private NumberOfStoolsNighttime numberOfStoolsNighttime;

    @Access(AccessType.PROPERTY)
    @Column(name = "number_of_stools_nighttime_id", nullable = false)
    public int getNumberOfStoolsNighttimeId() {
        if (numberOfStoolsNighttime != null) {
            return numberOfStoolsNighttime.getId();
        }

        return -1;
    }

    public void setNumberOfStoolsNighttimeId(int id) {
        this.numberOfStoolsNighttime = NumberOfStoolsNighttime.getNumberOfStoolsNighttime(id);
    }

    public NumberOfStoolsNighttime getNumberOfStoolsNighttime() {
        return numberOfStoolsNighttime;
    }

    public void setNumberOfStoolsNighttime(NumberOfStoolsNighttime toiletTiming) {
        this.numberOfStoolsNighttime = toiletTiming;
    }

    @Transient
    private ToiletTiming toiletTiming;

    @Access(AccessType.PROPERTY)
    @Column(name = "toilet_timing_id", nullable = false)
    public int getToiletTimingId() {
        if (toiletTiming != null) {
            return toiletTiming.getId();
        }

        return -1;
    }

    public void setToiletTimingId(int id) {
        this.toiletTiming = ToiletTiming.getToiletTiming(id);
    }

    public ToiletTiming getToiletTiming() {
        return toiletTiming;
    }

    public void setToiletTiming(ToiletTiming toiletTiming) {
        this.toiletTiming = toiletTiming;
    }

    @Transient
    private PresentBlood presentBlood;

    @Access(AccessType.PROPERTY)
    @Column(name = "present_blood_id", nullable = false)
    public int getPresentBloodId() {
        if (presentBlood != null) {
            return presentBlood.getId();
        }

        return -1;
    }

    public void setPresentBloodId(int id) {
        this.presentBlood = PresentBlood.getPresentBlood(id);
    }

    public PresentBlood getPresentBlood() {
        return presentBlood;
    }

    public void setPresentBlood(PresentBlood presentBlood) {
        this.presentBlood = presentBlood;
    }

    @Transient
    private Feeling feeling;

    @Access(AccessType.PROPERTY)
    @Column(name = "feeling_id", nullable = false)
    public int getFeelingId() {
        if (feeling != null) {
            return feeling.getId();
        }

        return -1;
    }

    public void setFeelingId(int id) {
        this.feeling = Feeling.getFeeling(id);
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    @Transient
    private Complication complication;

    @Access(AccessType.PROPERTY)
    @Column(name = "complication_id", nullable = false)
    public int getComplicationId() {
        if (complication != null) {
            return complication.getId();
        }

        return -1;
    }

    public void setComplicationId(int id) {
        this.complication = Complication.getComplication(id);
    }

    public Complication getComplication() {
        return complication;
    }

    public void setComplication(Complication complication) {
        this.complication = complication;
    }

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


}
