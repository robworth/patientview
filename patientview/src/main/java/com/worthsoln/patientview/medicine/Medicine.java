package com.worthsoln.patientview.medicine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;

import com.worthsoln.patientview.utils.TimestampUtils;

public class Medicine {

    private int id;
    private String nhsno;
    private String unitcode;
    private Calendar startdate;
    private String name;
    private String dose;

    public Medicine() {
    }

    public Medicine(int id, String nhsno, String unitcode, Calendar startdate, String name, String dose) {
        this.id = id;
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.dose = dose;
        this.startdate = startdate;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.decode(id);
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Calendar getStartdate() {
        return startdate;
    }

    public void setStartdate(Calendar startdate) {
        this.startdate = startdate;
    }

    public void setStartdate(Timestamp datestamped) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datestamped.getTime());
        this.startdate = cal;
    }

    public void setStartdate(String dateString) {
        this.startdate = TimestampUtils.createTimestamp(dateString);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((startdate.get(Calendar.HOUR_OF_DAY) == 0) && (startdate.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(startdate.getTime());
        } else {
            return dateTimeFormat.format(startdate.getTime());
        }
    }

}
