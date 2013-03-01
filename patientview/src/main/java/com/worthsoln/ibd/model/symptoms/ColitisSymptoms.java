package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.ibd.model.enums.colitis.Complication;
import com.worthsoln.ibd.model.enums.colitis.NumberOfStoolsDaytime;
import com.worthsoln.ibd.model.enums.colitis.NumberOfStoolsNighttime;
import com.worthsoln.ibd.model.enums.colitis.PresentBlood;
import com.worthsoln.ibd.model.enums.colitis.ToiletTiming;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ibd_colitis_symptoms")
public class ColitisSymptoms extends BaseSymptoms {

    @Transient
    private NumberOfStoolsDaytime numberOfStoolsDaytime;

    @Transient
    private NumberOfStoolsNighttime numberOfStoolsNighttime;

    @Transient
    private ToiletTiming toiletTiming;

    @Transient
    private PresentBlood presentBlood;

    @Transient
    private Complication complication;

    @Override
    public Integer calculateScore() {
        int score = 0;

        if (numberOfStoolsDaytime != null) {
            score += numberOfStoolsDaytime.getScore();
        }

        if (numberOfStoolsNighttime != null) {
            score += numberOfStoolsNighttime.getScore();
        }

        if (toiletTiming != null) {
            score += toiletTiming.getScore();
        }

        if (presentBlood != null) {
            score += presentBlood.getScore();
        }

        if (complication != null) {
            score += complication.getScore();
        }

        if (getFeeling() != null) {
            score += getFeeling().getScore();
        }

        return score;
    }

    public NumberOfStoolsDaytime getNumberOfStoolsDaytime() {
        return numberOfStoolsDaytime;
    }

    public void setNumberOfStoolsDaytime(NumberOfStoolsDaytime toiletTiming) {
        this.numberOfStoolsDaytime = toiletTiming;
    }

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

    public NumberOfStoolsNighttime getNumberOfStoolsNighttime() {
        return numberOfStoolsNighttime;
    }

    public void setNumberOfStoolsNighttime(NumberOfStoolsNighttime toiletTiming) {
        this.numberOfStoolsNighttime = toiletTiming;
    }

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

    public ToiletTiming getToiletTiming() {
        return toiletTiming;
    }

    public void setToiletTiming(ToiletTiming toiletTiming) {
        this.toiletTiming = toiletTiming;
    }

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

    public PresentBlood getPresentBlood() {
        return presentBlood;
    }

    public void setPresentBlood(PresentBlood presentBlood) {
        this.presentBlood = presentBlood;
    }

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

    public Complication getComplication() {
        return complication;
    }

    public void setComplication(Complication complication) {
        this.complication = complication;
    }

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
}
