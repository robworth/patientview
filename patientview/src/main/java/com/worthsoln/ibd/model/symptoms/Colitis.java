package com.worthsoln.ibd.model.symptoms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ibd_colitis_symptoms")
public class Colitis extends BaseSymptom {

    @Column(nullable = false)
    private int stoolsDay;

    @Column(nullable = false)
    private int stoolsNight;

    @Column(nullable = false)
    private int toiletTiming;

    @Column(nullable = false)
    private int presentBlood;

    @Column(nullable = false)
    private int furtherComplications;

    @Override
    public Integer calculateScore() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    public int getFurtherComplications() {
        return furtherComplications;
    }

    public void setFurtherComplications(int furtherComplications) {
        this.furtherComplications = furtherComplications;
    }
}
