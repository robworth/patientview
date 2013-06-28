package org.patientview.radar.model;

import java.util.Date;

public class Transplant extends RadarModel {

    private Date date;
    private Modality modality;
    private int counter;
    private Boolean recurr;
    private Date dateRecurr, dateRejected, dateBiopsy;
    private RejectData dateFailureRejectData = new RejectData();

    public static class Modality extends BaseModel{
        private Long id;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class RejectData extends BaseModel{
        private Long transplantId;
        private Date rejectedDate;
        private Date biopsyDate;
        private Date failureDate;

        public Long getTransplantId() {
            return transplantId;
        }

        public void setTransplantId(Long transplantId) {
            this.transplantId = transplantId;
        }

        public Date getRejectedDate() {
            return rejectedDate;
        }

        public void setRejectedDate(Date rejectedDate) {
            this.rejectedDate = rejectedDate;
        }

        public Date getBiopsyDate() {
            return biopsyDate;
        }

        public void setBiopsyDate(Date biopsyDate) {
            this.biopsyDate = biopsyDate;
        }

        public Date getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(Date failureDate) {
            this.failureDate = failureDate;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Boolean getRecurr() {
        return recurr;
    }

    public void setRecurr(Boolean recurr) {
        this.recurr = recurr;
    }

    public Date getDateRecurr() {
        return dateRecurr;
    }

    public void setDateRecurr(Date dateRecurr) {
        this.dateRecurr = dateRecurr;
    }

    public RejectData getDateFailureRejectData() {
        return dateFailureRejectData;
    }

    public void setDateFailureRejectData(RejectData dateFailureRejectData) {
        this.dateFailureRejectData = dateFailureRejectData;
    }

    public Date getDateRejected() {
        return dateRejected;
    }

    public void setDateRejected(Date dateRejected) {
        this.dateRejected = dateRejected;
    }

    public Date getDateBiopsy() {
        return dateBiopsy;
    }

    public void setDateBiopsy(Date dateBiopsy) {
        this.dateBiopsy = dateBiopsy;
    }
}
