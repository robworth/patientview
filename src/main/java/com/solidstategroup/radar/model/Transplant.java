package com.solidstategroup.radar.model;

import java.util.Date;

public class Transplant extends BaseModel {

    private Date date;
    // This should maybe be TransplantModality?
    private String type;
    private int counter;
    private Boolean recurr;
    private Date dateRecurr, dateFailure, dateRejected, dateBiopsy;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getDateFailure() {
        return dateFailure;
    }

    public void setDateFailure(Date dateFailure) {
        this.dateFailure = dateFailure;
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
