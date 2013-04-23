package com.worthsoln.patientview.utils;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public static final SimpleDateFormat DAY_FORMAT_DASH_BACKWARDS = new SimpleDateFormat("yyyy-MM-dd");

    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampUtils.class);

    public static Calendar createTimestamp(String dateTimeString) throws IllegalFieldValueException {
        Calendar cal = new GregorianCalendar();

        try {
            if (dateTimeString.contains("-")) {
                if (dateTimeString.indexOf("-") == 2) {
                    Date date = DAY_FORMAT_DASH.parse(dateTimeString);

                    if (date != null) {
                        cal.setTime(date);
                    }
                } else if (dateTimeString.indexOf("-") == 4) {
                    Date date = DAY_FORMAT_DASH_BACKWARDS.parse(dateTimeString);

                    if (date != null) {
                        cal.setTime(date);
                    }
                } else {
                    DateTime dateTime = new DateTime(dateTimeString);
                    cal.setTime(dateTime.toDate());
                }
            } else {
                AtomicReference<Date> date = new AtomicReference<Date>();

                if (dateTimeString.indexOf("/") == 2) {
                    date.set(DAY_FORMAT_SLASH.parse(dateTimeString));
                    cal.setTime(date.get());
                } else {
                    date.set(DAY_FORMAT_SLASH_BACKWARDS.parse(dateTimeString));
                    cal.setTime(date.get());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Can not parse date {}", dateTimeString, e.getMessage());

            cal = null;
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