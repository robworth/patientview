package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.ibd.model.enums.Feeling;
import com.worthsoln.ibd.model.enums.crohns.AbdominalPain;
import com.worthsoln.ibd.model.enums.crohns.Complication;
import com.worthsoln.ibd.model.enums.crohns.MassInTummy;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "ibd_crohns")
public class Crohns extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date chornsDate;

    @Column(nullable = false)
    private int openBowels;

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
    private AbdominalPain abdominalPain;

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

    public AbdominalPain getAbdominalPain() {
        return abdominalPain;
    }

    public void setAbdominalPain(AbdominalPain abdominalPain) {
        this.abdominalPain = abdominalPain;
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

    @Transient
    private MassInTummy massInTummy;

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

    public MassInTummy getMassInTummy() {
        return massInTummy;
    }

    public void setMassInTummy(MassInTummy massInTummy) {
        this.massInTummy = massInTummy;
    }

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

    public int getOpenBowels() {
        return openBowels;
    }

    public void setOpenBowels(int openBowels) {
        this.openBowels = openBowels;
    }
}
