package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.ibd.model.enums.crohns.AbdominalPain;
import com.worthsoln.ibd.model.enums.crohns.Complication;
import com.worthsoln.ibd.model.enums.crohns.MassInTummy;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ibd_crohns_symptoms")
public class CrohnsSymptoms extends BaseSymptoms {

    @Column(nullable = false)
    private int openBowels;
    
    @Transient
    private AbdominalPain abdominalPain;

    @Transient
    private MassInTummy massInTummy;

    @Transient
    private Complication complication;

    @Override
    public Integer calculateScore() {
        int score = 0;

        score += openBowels;

        if (abdominalPain != null) {
            score += abdominalPain.getScore();
        }

        if (massInTummy != null) {
            score += massInTummy.getScore();
        }

        if (complication != null) {
            score += complication.getScore();
        }

        if (getFeeling() != null) {
            score += getFeeling().getScore();
        }

        return score;
    }
    
    public int getOpenBowels() {
        return openBowels;
    }

    public void setOpenBowels(int openBowels) {
        this.openBowels = openBowels;
    }

    public AbdominalPain getAbdominalPain() {
        return abdominalPain;
    }

    public void setAbdominalPain(AbdominalPain abdominalPain) {
        this.abdominalPain = abdominalPain;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "abdominal_pain_id", nullable = false)
    public int getAbdominalPainId() {
        if (abdominalPain != null) {
            return abdominalPain.getId();
        }

        return -1;
    }

    public void setAbdominalPainId(int id) {
        this.abdominalPain = AbdominalPain.getAbdominalPain(id);
    }

    public MassInTummy getMassInTummy() {
        return massInTummy;
    }

    public void setMassInTummy(MassInTummy massInTummy) {
        this.massInTummy = massInTummy;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "mass_in_tummy_id", nullable = false)
    public int getMassInTummyId() {
        if (massInTummy != null) {
            return massInTummy.getId();
        }

        return -1;
    }

    public void setMassInTummyId(int id) {
        this.massInTummy = MassInTummy.getMassInTummy(id);
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
