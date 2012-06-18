package com.solidstategroup.radar.model.generic;

import java.io.Serializable;


public class AddPatientModel implements Serializable {
    private String id;
    private IdType idType;
    private DiseaseGroup diseaseGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
