package com.worthsoln.ibd.model.symptoms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ibd_crohns_symptoms")
public class Crohns extends BaseSymptom {

    @Column(nullable = false)
    private int abdominalPain;

    @Column(nullable = false)
    private int openBowels;

    @Column(nullable = false)
    private int complications;

    @Column(nullable = false)
    private int massInTummy;

    @Override
    public Integer calculateScore() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
