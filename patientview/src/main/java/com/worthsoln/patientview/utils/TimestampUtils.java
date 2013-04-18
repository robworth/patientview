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

    public static final SimpleDateFormat NORMAL_DAY_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
//    public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
//    public static final SimpleDateFormat DAY_HOUR_MIN_FORMAT = new SimpleDateFormat("yyyy-MM-ddThh:mm");
//   public static final SimpleDateFormat DAY_HOUR_MIN_SEC_FORMAT = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss");

    public static Calendar createTimestamp(String dateTimeString) throws IllegalFieldValueException {

        Calendar cal = new GregorianCalendar();
        if (dateTimeString.contains("-")) {
            DateTime dateTime = new DateTime(dateTimeString);
            cal.setTime(dateTime.toDate());
        } else {
            AtomicReference<Date> date = new AtomicReference<Date>();
            try {
                date.set(NORMAL_DAY_FORMAT.parse(dateTimeString));
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            cal.setTime(date.get());
        }

        return cal;
    }

    private static Calendar createNormalDatestamp(String dateTimeString) {
        Calendar datestamp = Calendar.getInstance();
        int year = Integer.parseInt(dateTimeString.substring(6, 10));
        int month = Integer.parseInt(dateTimeString.substring(3, 5));
        int day = Integer.parseInt(dateTimeString.substring(0, 2));

        datestamp.set(year, month - 1, day, 0, 0, 0);

        if (dateTimeString.length() == 16) {
            datestamp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateTimeString.substring(11, 13)));
            datestamp.set(Calendar.MINUTE, Integer.parseInt(dateTimeString.substring(14, 16)));
        } else {
            datestamp.set(Calendar.HOUR_OF_DAY, 0);
            datestamp.set(Calendar.MINUTE, 0);
        }

        datestamp.set(Calendar.SECOND, 00);
        datestamp.set(Calendar.MILLISECOND, 0);

        return datestamp;
    }

    private static Calendar createIsoDatestamp(String dateTimeString) {
        Calendar datestamp = Calendar.getInstance();
        int year = Integer.parseInt(dateTimeString.substring(0, 4));
        int month = Integer.parseInt(dateTimeString.substring(5, 7));
        int day = Integer.parseInt(dateTimeString.substring(8, 10));

        datestamp.set(year, month - 1, day, 0, 0, 10);

        if (-1 != dateTimeString.indexOf("T")) {
            datestamp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateTimeString.substring(11, 13)));
            datestamp.set(Calendar.MINUTE, Integer.parseInt(dateTimeString.substring(14, 16)));

            if (dateTimeString.length() == 19) {
                datestamp.set(Calendar.SECOND, Integer.parseInt(dateTimeString.substring(17, 19)));
            }
        } else {
            datestamp.set(Calendar.HOUR_OF_DAY, 0);
            datestamp.set(Calendar.MINUTE, 0);
            datestamp.set(Calendar.SECOND, 10);
        }

        datestamp.set(Calendar.MILLISECOND, 0);

        return datestamp;
    }
}
