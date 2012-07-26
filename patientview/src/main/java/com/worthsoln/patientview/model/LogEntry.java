package com.worthsoln.patientview.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.worthsoln.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "log")
public class LogEntry extends BaseModel {

    @Column(nullable = false)
    private Calendar date;

    @Column(nullable = true)
    private String nhsno;

    @Column(nullable = true)
    private String user;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = true)
    private String unitcode;

    @Column(nullable = true)
    private String extrainfo;

    public LogEntry() {
    }

    public LogEntry(String nhsno, String user, String action, String actor, String unitcode, String extrainfo) {
        this.action = action;
        this.nhsno = nhsno;
        this.user = user;
        this.actor = actor;
        setUnitcode(unitcode);
        this.extrainfo = extrainfo;
        this.date = Calendar.getInstance();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        this.date = TimestampUtils.createTimestamp(dateString);
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((date.get(Calendar.HOUR_OF_DAY) == 0) && (date.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(date.getTime());
        } else {
            return dateTimeFormat.format(date.getTime());
        }
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getExtrainfo() {
        return extrainfo;
    }

    public String getExtrainfolessxmlfilename() {
        String extrainfoReturn = extrainfo;
        String xmlFilename =  getXmlfilename();
        if(xmlFilename.length() != 0) {
           extrainfoReturn = extrainfo.substring(xmlFilename.length());
        }        
        return extrainfoReturn;
    }

    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    public String getXmlfilename() {
        String filename = "";

        if (extrainfo != null && !extrainfo.equals("")) {
            String suffix = ".xml";
            int endFilename = extrainfo.indexOf(suffix);

            if (endFilename != -1) {
                filename = extrainfo.substring(0, endFilename + suffix.length());
            }
        }

        return filename;
    }
}
