package org.patientview.radar.model.generic;

import org.patientview.model.Centre;
import org.patientview.model.generic.DiseaseGroup;

import java.io.Serializable;

public class AddPatientModel implements Serializable {
    private String patientId;
    private DiseaseGroup diseaseGroup;
    private Centre centre;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
