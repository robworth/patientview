package com.worthsoln.patientview.letter;

import com.worthsoln.patientview.utils.TimestampUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Letter {

    private int id;
    private String nhsno;
    private String unitcode;
    private Calendar date;
    private String type;
    private String content;

    public Letter() {
    }

    public Letter(int id, String nhsno, String unitcode, Calendar date, String type, String content) {
        this.id = id;
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public Letter(String nhsno, String unitcode, Calendar date, String type, String content) {
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setStringDate(String dateString) {
        this.date = TimestampUtils.createTimestamp(dateString);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter = (Letter) o;

        if (id != letter.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nhsno != null ? nhsno.hashCode() : 0);
        result = 31 * result + (unitcode != null ? unitcode.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
