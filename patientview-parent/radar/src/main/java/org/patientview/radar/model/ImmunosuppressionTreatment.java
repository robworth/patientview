package org.patientview.radar.model;

import java.util.Date;

public class ImmunosuppressionTreatment extends RadarModel {

    private Immunosuppression immunosuppression;
    private Date startDate, endDate;
    private Double cyclophosphamideTotalDose; // in g
    private Boolean firstFlag; // Not sure what this is for...

    public Immunosuppression getImmunosuppression() {
        return immunosuppression;
    }

    public void setImmunosuppression(Immunosuppression immunosuppression) {
        this.immunosuppression = immunosuppression;
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

    public Double getCyclophosphamideTotalDose() {
        return cyclophosphamideTotalDose;
    }

    public void setCyclophosphamideTotalDose(Double cyclophosphamideTotalDose) {
        this.cyclophosphamideTotalDose = cyclophosphamideTotalDose;
    }

    public Boolean getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(Boolean firstFlag) {
        this.firstFlag = firstFlag;
    }
}
