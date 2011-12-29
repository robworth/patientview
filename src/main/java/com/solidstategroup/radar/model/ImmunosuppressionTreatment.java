package com.solidstategroup.radar.model;

import java.util.Date;

public class ImmunosuppressionTreatment extends BaseModel {

    private Immunosuppression immunosuppression;
    private Date startDate, endDate;
    private Double cyclophosphamideTotalDose; // in g
    private boolean firstFlag; // Not sure what this is for...

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

    public boolean isFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(boolean firstFlag) {
        this.firstFlag = firstFlag;
    }
}
