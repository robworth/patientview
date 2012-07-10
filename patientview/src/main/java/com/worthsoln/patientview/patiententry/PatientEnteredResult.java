package com.worthsoln.patientview.patiententry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientEnteredResult {

    private Calendar datetime;
    private String resultCode1;
    private String value1;
    private String resultCode2;
    private String value2;

    public PatientEnteredResult() {
    }

    public PatientEnteredResult(String year, String month, String day, String hour, String minute,
                                String resultCode1, String value1) {
        setDatetime(year, month, day, hour, minute);
        setResultCode1(resultCode1);
        setValue1(value1);
        setResultCode2("");
        setValue2("");
    }

    public PatientEnteredResult(String year, String month, String day, String hour, String minute,
                                String resultCode1, String value1, String resultCode2, String value2) {
        setDatetime(year, month, day, hour, minute);
        setResultCode1(resultCode1);
        setValue1(value1);
        setResultCode2(resultCode2);
        setValue2(value2);
    }

    public void setDatetime(String year, String month, String day, String hour, String minute) {

        this.datetime = Calendar.getInstance();

        datetime.set(Integer.decode(year), Integer.decode(month)
                , Integer.decode(day), Integer.decode(hour), Integer.decode(minute), (int) (Math.random() * 59));
    }

    public String getStringDate() {
        DateFormat format = new SimpleDateFormat("d-MMM-yyyy");
        return format.format(datetime.getTime());
    }

    public String getStringTime() {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(datetime.getTime());
    }

    public Calendar getDatetime() {
        return datetime;
    }

    public void setDatetime(Calendar datetime) {
        this.datetime = datetime;
    }

    public String getResultCode1() {
        return resultCode1;
    }

    public void setResultCode1(String resultCode1) {
        this.resultCode1 = resultCode1;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getResultCode2() {
        return resultCode2;
    }

    public void setResultCode2(String resultCode2) {
        this.resultCode2 = resultCode2;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}