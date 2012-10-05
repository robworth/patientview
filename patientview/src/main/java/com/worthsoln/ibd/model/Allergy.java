package com.worthsoln.ibd.model;

import com.worthsoln.patientview.model.BaseModel;
import com.worthsoln.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "pv_allergy")
public class Allergy extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = true)
    private String substance;

    @Column(nullable = true)
    private String typeCode;

    @Column(nullable = true)
    private String reaction;

    @Column(nullable = true)
    private String confidenceLevel;

    @Column(nullable = true)
    private String infoSource;

    @Column(nullable = true)
    private String status;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Calendar recordedDate;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(String confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Calendar recordedDate) {
        this.recordedDate = recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = TimestampUtils.createTimestamp(recordedDate);
    }
}
