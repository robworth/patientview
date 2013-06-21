package org.patientview.radar.model;

import java.util.Date;

public class Treatment extends RadarModel {

    private TreatmentModality treatmentModality;
    private Date startDate, endDate;

    public TreatmentModality getTreatmentModality() {
        return treatmentModality;
    }

    public void setTreatmentModality(TreatmentModality treatmentModality) {
        this.treatmentModality = treatmentModality;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
