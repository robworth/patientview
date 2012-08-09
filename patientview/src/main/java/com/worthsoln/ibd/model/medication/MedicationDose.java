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

    public String getFormattedValue() {
        String doseValueAsString = "";

        /**
         * If the mg is grt OR eq to 1,000,000 then convert to KG
         * If the mg is grt OR eq to 1,000 then convert to Grams
         * Else just leave as mg
         */
        if (mg >= 1000000) {
            doseValueAsString = (mg / (1000 * 1000)) + "Kg";
        } else if (mg >= 1000) {
            doseValueAsString = (mg / 1000) + "G";
        } else {
            doseValueAsString = mg + "mg";
        }

        if (extraInformation != null && extraInformation.length() > 0) {
            doseValueAsString += extraInformation;
        }

        return doseValueAsString;
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
