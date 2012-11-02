package com.worthsoln.ibd.model;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.model.enums.*;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ibd_myibd")
public class MyIbd extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Transient
    private Diagnosis diagnosis;

    @Transient
    private DiseaseExtent diseaseExtent;

    @Column(nullable = true)
    private Date yearOfDiagnosis;

    @Transient
    private List<Complication> complications = new ArrayList<Complication>() {
        {
            add(Complication.NONE);
        }
    };

    @Column(nullable = true, columnDefinition = "TEXT")
    private String bodyPartAffected;

    @Column(nullable = true)
    private Date yearForSurveillanceColonoscopy;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String namedConsultant;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String nurses;

    @Column(nullable = true)
    private Double weight;

    @Column(nullable = true)
    private String eiManifestations;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String familyHistory;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String smoking;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String surgery;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String vaccinationRecord;

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

    @Access(AccessType.PROPERTY)
    @Column(name = "diagnosis_id", nullable = false)
    public long getDiagnosisId() {
        if (diagnosis != null) {
            return diagnosis.getId();
        }

        return -1;
    }

    public void setDiagnosisId(Long id) {
        this.diagnosis = Diagnosis.getDiagnosis(id);
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "disease_extent_id", nullable = true)
    public long getDiseaseExtentId() {
        if (diseaseExtent != null) {
            return diseaseExtent.getId();
        }

        return -1;
    }

    public void setDiseaseExtentId(Long id) {
        this.diseaseExtent = DiseaseExtent.getDiseaseExtent(id);
    }

    public DiseaseExtent getDiseaseExtent() {
        return diseaseExtent;
    }

    public void setDiseaseExtent(DiseaseExtent diseaseExtent) {
        this.diseaseExtent = diseaseExtent;
    }

    public Date getYearOfDiagnosis() {
        return yearOfDiagnosis;
    }

    public String getYearOfDiagnosisAsString() {
        return Ibd.YEAR_DATE_FORMAT.format(yearOfDiagnosis);
    }

    public void setYearOfDiagnosis(Date yearOfDiagnosis) {
        this.yearOfDiagnosis = yearOfDiagnosis;
    }

    @Access(AccessType.PROPERTY)
    @ElementCollection
    @CollectionTable(name = "ibd_myibd_complications", joinColumns = @JoinColumn(name = "myibd_id"))
    @Column(name = "complication_id")
    public Set<Long> getComplicationIds() {
        if (complications != null) {
            Set<Long> complicationIds = new HashSet<Long>(complications.size());

            for (Complication complication : complications) {
                complicationIds.add(complication.getId());
            }

            return complicationIds;
        }

        return new HashSet<Long>(0);
    }

    public void setComplicationIds(Set<Long> complicationIds) {
        if (complicationIds != null) {
            complications = new ArrayList<Complication>(complicationIds.size());

            for (long complicationId : complicationIds) {
                complications.add(Complication.getComplication(complicationId));
            }
        }
    }

    public List<Complication> getComplications() {
        return complications;
    }

    public void setComplications(List<Complication> complications) {
        this.complications = complications;
    }

    public String getBodyPartAffected() {
        return bodyPartAffected;
    }

    public void setBodyPartAffected(String bodyPartAffected) {
        this.bodyPartAffected = bodyPartAffected;
    }

    public Date getYearForSurveillanceColonoscopy() {
        return yearForSurveillanceColonoscopy;
    }

    public String getYearForSurveillanceColonoscopyAsString() {
        if (yearForSurveillanceColonoscopy != null) {
            return Ibd.YEAR_DATE_FORMAT.format(yearForSurveillanceColonoscopy);
        }

        return "";
    }

    public void setYearForSurveillanceColonoscopy(Date yearForSurveillanceColonoscopy) {
        this.yearForSurveillanceColonoscopy = yearForSurveillanceColonoscopy;
    }

    public String getNamedConsultant() {
        return namedConsultant;
    }

    public void setNamedConsultant(String namedConsultant) {
        this.namedConsultant = namedConsultant;
    }

    public String getNurses() {
        return nurses;
    }

    public void setNurses(String nurses) {
        this.nurses = nurses;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getVaccinationRecord() {
        return vaccinationRecord;
    }

    public void setVaccinationRecord(String vaccinationRecord) {
        this.vaccinationRecord = vaccinationRecord;
    }

    public String getEiManifestations() {
        return eiManifestations;
    }

    public void setEiManifestations(String eiManifestations) {
        this.eiManifestations = eiManifestations;
    }
}
