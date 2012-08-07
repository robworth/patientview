package com.worthsoln.ibd.model.medication;

import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ibd_medication_dose")
public class MedicationDose extends BaseModel {

    @Column(nullable = false)
    private Double mg;

    @Column(nullable = true)
    private String extraInformation;

    public String getMgValueAsString() {
        return mg.toString();
    }

    public Double getMg() {
        return mg;
    }

    public void setMg(Double mg) {
        this.mg = mg;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }
}
