package com.solidstategroup.radar.model;

import java.util.Date;

public class Transplant extends BaseModel {

    private Date date;
    // This should maybe be TransplantModality?
    private String type;
    private int counter;
    private Boolean recurr;
    private Date dateRecurr, dateFailure, dateRejected, dateBiopsy;

}
