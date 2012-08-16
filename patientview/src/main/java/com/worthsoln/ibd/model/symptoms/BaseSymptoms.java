package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.ibd.model.enums.Feeling;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;

@MappedSuperclass
public abstract class BaseSymptoms extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date symptomDate;

    @Transient
    private Feeling feeling;

    @Column(nullable = false)
    private Integer score;

    public abstract Integer calculateScore();

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Date getSymptomDate() {
        return symptomDate;
    }

    public void setSymptomDate(Date symptomDate) {
        this.symptomDate = symptomDate;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
