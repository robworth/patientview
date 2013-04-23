package com.worthsoln.patientview.utils;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReference;

public class TimestampUtils {

    public static final SimpleDateFormat DAY_FORMAT_SLASH = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DAY_FORMAT_SLASH_BACKWARDS = new SimpleDateFormat("yyyy/MM/dd");
    public static final String DAY_FORMAT_DASH = "dd-MM-yyyy";
    public static final String DAY_FORMAT_DASH_BACKWARDS = "yyyy-MM-dd";
    public static final String DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_AND_MINUTE = "yyyy-MM-dd'T'HH:mm";
    public static final String DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_MINUTE_AND_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DAY_FORMAT_DASH_BACKWARDS_WITH_T_HOUR_MINUTE_AND_SECOND = "yyyy-MM-dd'T'HH:mm:ss";

    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampUtils.class);

    /**
     * Given a date string, this method attempts to figure out which format it has and then returns a calendar object
     *
     * Ideally this method should get format as a parameter in order to avoid possible ambiguous dates like 2/1/2012
     *
     * @param dateTimeString a date in an recognised format
     * @return date object if date format is recognised
     */
    public static Calendar createTimestamp(String dateTimeString) {
        Calendar cal = new GregorianCalendar();
        DateTimeFormatter formatter = null;

        try {
            if (dateTimeString.contains("-")) {
                if (dateTimeString.indexOf("-") == 2) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH);
                } else if (dateTimeString.indexOf("-") == 4 && dateTimeString.length() == 19 &&
                        dateTimeString.indexOf("T") == 10) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS_WITH_T_HOUR_MINUTE_AND_SECOND);
                } else if (dateTimeString.indexOf("-") == 4 && dateTimeString.length() == 19 &&
                        dateTimeString.indexOf(" ") == 10) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_MINUTE_AND_SECOND);
                } else if (dateTimeString.indexOf("-") == 4 && dateTimeString.length() == 16) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_AND_MINUTE);
                } else if (dateTimeString.indexOf("-") == 4 && dateTimeString.length() == 10) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS);
                }

                DateTime dateTime;
                if (formatter == null) {
                    dateTime = new DateTime(dateTimeString);
                } else {
                    dateTime = formatter.parseDateTime(dateTimeString);
                }

                cal.setTime(dateTime.toDate());
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