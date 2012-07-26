package com.worthsoln.patientview.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.worthsoln.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Comment extends BaseModel {

    @Column(nullable = false)
    private Calendar datestamp;

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String body;

    public Comment() {
    }

    public Comment(Long id) {
        this.setId(id);
    }

    public Comment(String nhsno, String body) {
        this.datestamp = Calendar.getInstance();
        setNhsno(nhsno);
        setBody(body);
    }

    public Comment(Calendar datestamp, String nhsno, String body) {
        this.datestamp = datestamp;
        setNhsno(nhsno);
        setBody(body);
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
        if ((datestamp.get(Calendar.HOUR_OF_DAY) == 0) && (datestamp.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamp.getTime());
        } else {
            return dateTimeFormat.format(datestamp.getTime());
        }
    }

    public String getIsoFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.format(datestamp.getTime());
    }

    public String getBodyForHtml() {
        return getBody().replaceAll("\r\n", "<br />");
    }
}
