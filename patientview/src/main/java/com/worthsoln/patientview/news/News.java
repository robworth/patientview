package com.worthsoln.patientview.news;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.worthsoln.patientview.utils.TimestampUtils;

public class News {

    private int id;
    private Calendar datestamped;
    private String unitcode;
    private boolean admin;
    private boolean patient;
    private boolean everyone;
    private String headline;
    private String body;

    public News() {
    }

    public News(int id) {
        this.id = id;
    }

    public News(String unitcode, boolean admin, boolean patient, boolean everyone, String headline, String body) {
        this.datestamped = Calendar.getInstance();
        setUnitcode(unitcode);
        this.admin = admin;
        this.patient = patient;
        this.everyone = everyone;
        this.headline = headline;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDatestamp() {
        return datestamped;
    }

    public void setDatestamp(Calendar date) {
        this.datestamped = date;
    }

    public void setDatestamp(String dateString) {
        this.datestamped = TimestampUtils.createTimestamp(dateString);
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
        if ((datestamped.get(Calendar.HOUR_OF_DAY) == 0) && (datestamped.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamped.getTime());
        } else {
            return dateTimeFormat.format(datestamped.getTime());
        }
    }

    public String getBodyForHtml() {
        return getBody().replaceAll("\r\n", "<br />");
    }
}
