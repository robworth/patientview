package com.solidstategroup.radar.model.generic;

import com.solidstategroup.radar.model.Centre;

import java.io.Serializable;

public class AddPatientModel implements Serializable {
    private String patientId;
    private IdType idType;
    private DiseaseGroup diseaseGroup;
    private Centre centre;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
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
