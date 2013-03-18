package com.solidstategroup.radar.model.generic;

import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.enums.NhsNumberType;

import java.io.Serializable;

public class AddPatientModel implements Serializable {
    private String patientId;
    private NhsNumberType nhsNumberType;
    private DiseaseGroup diseaseGroup;
    private Centre centre;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public NhsNumberType getNhsNumberType() {
        return nhsNumberType;
    }

    public void setNhsNumberType(NhsNumberType nhsNumberType) {
        this.nhsNumberType = nhsNumberType;
    }

    public void setNhsNumberType(String nhsNumberTypeId) {
        this.nhsNumberType = NhsNumberType.getNhsNumberType(Long.parseLong(nhsNumberTypeId));
    }

    public DiseaseGroup getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(DiseaseGroup diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
