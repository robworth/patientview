package com.worthsoln.ibd.model.medication;

import com.worthsoln.ibd.model.medication.enums.MedicationFrequency;
import com.worthsoln.ibd.model.medication.enums.MedicationNoOf;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "ibd_my_medication")
public class MyMedication extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date dateStarted;

    @Column(nullable = true)
    private Date dateStopped;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medication_type_id")
    private MedicationType medicationType;

    @ManyToOne(optional = true)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String otherMedication;

    @ManyToOne(optional = true)
    @JoinColumn(name = "medication_dose_id")
    private MedicationDose medicationDose;

    @Transient
    private MedicationNoOf medicationNoOf;

    @Transient
    private MedicationFrequency medicationFrequency;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String reasonForStopping;

    public boolean hasStopped() {
        return dateStopped != null;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateStopped() {
        return dateStopped;
    }

    public void setDateStopped(Date dateStopped) {
        this.dateStopped = dateStopped;
    }

    public MedicationType getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(MedicationType medicationType) {
        this.medicationType = medicationType;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getOtherMedication() {
        return otherMedication;
    }

    public void setOtherMedication(String otherMedication) {
        this.otherMedication = otherMedication;
    }

    public MedicationDose getMedicationDose() {
        return medicationDose;
    }

    public void setMedicationDose(MedicationDose medicationDose) {
        this.medicationDose = medicationDose;
    }

    public MedicationNoOf getMedicationNoOf() {
        return medicationNoOf;
    }

    public void setMedicationNoOf(MedicationNoOf medicationNoOf) {
        this.medicationNoOf = medicationNoOf;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "medication_no_of_id", nullable = false)
    public Long getMedicationNoOfId() {
        if (medicationNoOf != null) {
            return medicationNoOf.getId();
        }

        return null;
    }

    public void setMedicationNoOfId(Long id) {
        this.medicationNoOf = MedicationNoOf.getMedicineNoOf(id);
    }

    public MedicationFrequency getMedicationFrequency() {
        return medicationFrequency;
    }

    public void setMedicationFrequency(MedicationFrequency medicationFrequency) {
        this.medicationFrequency = medicationFrequency;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "medication_frequency_id", nullable = false)
    public Long getMedicationFrequencyId() {
        if (medicationFrequency != null) {
            return medicationFrequency.getId();
        }

        return null;
    }

    public void setMedicationFrequencyId(Long id) {
        this.medicationFrequency = MedicationFrequency.getMedicineFrequency(id);
    }

    public String getReasonForStopping() {
        return reasonForStopping;
    }

    public void setReasonForStopping(String reasonForStopping) {
        this.reasonForStopping = reasonForStopping;
    }
}
