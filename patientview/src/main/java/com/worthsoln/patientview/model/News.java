package com.worthsoln.patientview.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.worthsoln.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class News extends BaseModel {

    @Column(nullable = false)
    private Calendar datestamp;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private boolean admin;

    @Column(nullable = false)
    private boolean patient;

    @Column(nullable = false)
    private boolean everyone;

    @Column(nullable = false)
    private String headline;

    @Column(nullable = false)
    private String body;

    public News() {
    }

    public News(Long id) {
        this.setId(id);
    }

    public News(String unitcode, boolean admin, boolean patient, boolean everyone, String headline, String body) {
        this.datestamp = Calendar.getInstance();
        setUnitcode(unitcode);
        this.admin = admin;
        this.patient = patient;
        this.everyone = everyone;
        this.headline = headline;
        this.body = body;
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar date) {
        this.datestamp = date;
    }

    public void setDatestamp(String dateString) {
        this.datestamp = TimestampUtils.createTimestamp(dateString);
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPatient() {
        return patient;
    }

    public void setPatient(boolean patient) {
        this.patient = patient;
    }

    public boolean isEveryone() {
        return everyone;
    }

    public void setEveryone(boolean everyone) {
        this.everyone = everyone;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((datestamp.get(Calendar.HOUR_OF_DAY) == 0) && (datestamp.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamp.getTime());
        } else {
            return dateTimeFormat.format(datestamp.getTime());
        }
    }

    public String getBodyForHtml() {
        return getBody().replaceAll("\r\n", "<br />");
    }
}
