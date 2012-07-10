package com.worthsoln.patientview.comment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.worthsoln.patientview.utils.TimestampUtils;

public class Comment {

    private int id;
    private Calendar datestamped;
    private String nhsno;
    private String body;

    public Comment() {
    }

    public Comment(int id) {
        this.id = id;
    }

    public Comment(String nhsno, String body) {
        this.datestamped = Calendar.getInstance();
        setNhsno(nhsno);
        setBody(body);
    }

    public Comment(Calendar datestamp, String nhsno, String body) {
        this.datestamped = datestamp;
        setNhsno(nhsno);
        setBody(body);
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

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
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

    public String getIsoFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.format(datestamped.getTime());
    }

    public String getBodyForHtml() {
        return getBody().replaceAll("\r\n", "<br />");
    }
}
