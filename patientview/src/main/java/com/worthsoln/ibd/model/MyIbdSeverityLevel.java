package com.worthsoln.ibd.model;

import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.Severity;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ibd_myibd_severity_level")
public class MyIbdSeverityLevel extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Transient
    private Severity severity;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String treatment;

    @Column(nullable = true)
    private Integer level;

    public MyIbdSeverityLevel() {
    }

    public MyIbdSeverityLevel(String nhsno, Severity severity, Integer level, String treatment) {
        this.nhsno = nhsno;
        this.severity = severity;
        this.level = level;
        this.treatment = treatment;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "severity_id", nullable = false)
    public long getSeverityId() {
        if (severity != null) {
            return severity.getId();
        }

        return -1;
    }

    public void setSeverityId(Long id) {
        this.severity = Severity.getSeverity(id);
    }

    public Integer getLevel(Diagnosis diagnosis) {
        if (level == null || level <= 0) {
            return severity.getDefaultLevel(diagnosis);
        }

        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
