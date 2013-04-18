package com.worthsoln.patientview.utils;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReference;

public class TimestampUtils {

    public static final SimpleDateFormat DAY_FORMAT_SLASH = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DAY_FORMAT_SLASH_BACKWARDS = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat DAY_FORMAT_DASH = new SimpleDateFormat("dd-MM-yyyy");

    public static Calendar createTimestamp(String dateTimeString) throws IllegalFieldValueException {

        Calendar cal = new GregorianCalendar();
        if (dateTimeString.contains("-")) {
            if (dateTimeString.indexOf("-") == 2) {
                Date date;
                try {
                    date = (DAY_FORMAT_DASH.parse(dateTimeString));
                    if (date != null) {
                        cal.setTime(date);
                    }
                } catch (ParseException e) {
                    cal = null;
                    return cal;
                }
            } else {
                DateTime dateTime = new DateTime(dateTimeString);
                cal.setTime(dateTime.toDate());
            }
        } else {
            AtomicReference<Date> date = new AtomicReference<Date>();
            if (dateTimeString.indexOf("/") == 2) {
                try {
                date.set(DAY_FORMAT_SLASH.parse(dateTimeString));
            } catch (ParseException e) {
                cal = null;
                return cal;
            }
            cal.setTime(date.get());
            } else {
                try {
                    date.set(DAY_FORMAT_SLASH_BACKWARDS.parse(dateTimeString));
                } catch (ParseException e) {
                    cal = null;
                    return cal;
                }
                cal.setTime(date.get());

            }
        }

        return cal;
    }

    public static Calendar createTimestampStartDay(String dateTimeString) throws IllegalFieldValueException {
        Calendar cal = createTimestamp(dateTimeString);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    public static Calendar createTimestampEndDay(String dateTimeString) throws IllegalFieldValueException {
        Calendar cal = createTimestamp(dateTimeString);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal;
    }
}