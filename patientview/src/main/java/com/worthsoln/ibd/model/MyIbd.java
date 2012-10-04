package com.worthsoln.ibd.model;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.model.enums.BodyPartAffected;
import com.worthsoln.ibd.model.enums.Complication;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.DiseaseExtent;
import com.worthsoln.ibd.model.enums.FamilyHistory;
import com.worthsoln.ibd.model.enums.Smoking;
import com.worthsoln.ibd.model.enums.Surgery;
import com.worthsoln.ibd.model.enums.VaccinationRecord;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ibd_myibd")
public class MyIbd extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Transient
    private Diagnosis diagnosis;

    @Transient
    private DiseaseExtent diseaseExtent;

    @Column(nullable = false)
    private Date yearOfDiagnosis;

    @Transient
    private List<Complication> complications = new ArrayList<Complication>() {
        {
            add(Complication.NONE);
        }
    };

    @Transient
    private BodyPartAffected bodyPartAffected = BodyPartAffected.NONE;

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

    @Transient
    private FamilyHistory familyHistory = FamilyHistory.NONE;

    @Transient
    private Smoking smoking = Smoking.NEVER_SMOKED;

    @Transient
    private Surgery surgery = Surgery.NO_PREVIOUS_OPERATIONS;

    @Transient
    private VaccinationRecord vaccinationRecord = VaccinationRecord.NOT_KNOWN;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
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
    @Column(name = "disease_extent_id", nullable = false)
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
        return Ibd.DATE_FORMAT.format(yearOfDiagnosis);
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

    @Access(AccessType.PROPERTY)
    @Column(name = "body_part_affected_id", nullable = false)
    public long getBodyPartAffectedId() {
        if (bodyPartAffected != null) {
            return bodyPartAffected.getId();
        }

        return -1;
    }

    public void setBodyPartAffectedId(Long id) {
        this.bodyPartAffected = BodyPartAffected.getBodyPartAffected(id);
    }

    public BodyPartAffected getBodyPartAffected() {
        return bodyPartAffected;
    }

    public void setBodyPartAffected(BodyPartAffected bodyPartAffected) {
        this.bodyPartAffected = bodyPartAffected;
    }

    public Date getYearForSurveillanceColonoscopy() {
        return yearForSurveillanceColonoscopy;
    }

    public String getYearForSurveillanceColonoscopyAsString() {
        if (yearForSurveillanceColonoscopy != null) {
            return Ibd.DATE_FORMAT.format(yearForSurveillanceColonoscopy);
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

    @Access(AccessType.PROPERTY)
    @Column(name = "family_history_id", nullable = false)
    public long getFamilyHistoryId() {
        if (familyHistory != null) {
            return familyHistory.getId();
        }

        return -1;
    }

    public void setFamilyHistoryId(Long id) {
        this.familyHistory = FamilyHistory.getFamilyHistory(id);
    }

    public FamilyHistory getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(FamilyHistory familyHistory) {
        this.familyHistory = familyHistory;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "smoking_id", nullable = false)
    public long getSmokingId() {
        if (smoking != null) {
            return smoking.getId();
        }

        return -1;
    }

    public void setSmokingId(Long id) {
        this.smoking = Smoking.getSmoking(id);
    }

    public Smoking getSmoking() {
        return smoking;
    }

    public void setSmoking(Smoking smoking) {
        this.smoking = smoking;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "surgery_id", nullable = false)
    public long getSurgeryId() {
        if (surgery != null) {
            return surgery.getId();
        }

        return -1;
    }

    public void setSurgeryId(Long id) {
        this.surgery = Surgery.getSurgery(id);
    }

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "vaccination_record_id", nullable = false)
    public long getVaccinationRecordId() {
        if (vaccinationRecord != null) {
            return vaccinationRecord.getId();
        }

        return -1;
    }

    public void setVaccinationRecordId(Long id) {
        this.vaccinationRecord = VaccinationRecord.getVaccinationRecord(id);
    }

    public VaccinationRecord getVaccinationRecord() {
        return vaccinationRecord;
    }

    public void setVaccinationRecord(VaccinationRecord vaccinationRecord) {
        this.vaccinationRecord = vaccinationRecord;
    }

    public String getEiManifestations() {
        return eiManifestations;
    }

    public void setEiManifestations(String eiManifestations) {
        this.eiManifestations = eiManifestations;
    }
}
