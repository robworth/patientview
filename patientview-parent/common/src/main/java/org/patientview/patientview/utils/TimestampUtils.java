/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.patientview.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReference;

public final class TimestampUtils {

    public static final SimpleDateFormat DAY_FORMAT_SLASH = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DAY_FORMAT_SLASH_BACKWARDS = new SimpleDateFormat("yyyy/MM/dd");
    public static final String DAY_FORMAT_DASH = "dd-MM-yyyy";
    public static final String DAY_FORMAT_DASH_BACKWARDS = "yyyy-MM-dd";
    public static final String DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_AND_MINUTE = "yyyy-MM-dd'T'HH:mm";
    public static final String DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_MINUTE_AND_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DAY_FORMAT_DASH_BACKWARDS_WITH_T_HOUR_MINUTE_AND_SECOND = "yyyy-MM-dd'T'HH:mm:ss";

    private static final int YEAR_FIRST_DASH_POS = 4;
    private static final int DATE_WITH_SECONDS_LENGTH = 19;
    private static final int DATE_WITH_MINUTES_LENGTH = 16;
    private static final int DATE_ONLY_LENGTH = 10;
    private static final int ISO_DATE_T_POS = 10;
    private static final int NORMAL_DATE_SPACE_POS = 10;

    private static final int LAST_HOUR = 23;
    private static final int LAST_MINUTE = 59;
    private static final int LAST_SECOND = 59;
    private static final int LAST_MILLISECOND = 999;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampUtils.class);

    /**
     * Given a date string, this method attempts to figure out which format it has and then returns a calendar object
     * <p/>
     * Ideally this method should get format as a parameter in order to avoid possible ambiguous dates like 2/1/2012
     *
     * @return date object if date format is recognised
     */
    private TimestampUtils() {

    }

    public static Calendar createTimestamp(String dateTimeString) {
        Calendar cal = new GregorianCalendar();
        DateTimeFormatter formatter = null;

        try {
            if (dateTimeString.contains("-")) {
                if (dateTimeString.indexOf("-") == 2) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH);
                } else if (dateTimeString.indexOf("-") == YEAR_FIRST_DASH_POS
                        && dateTimeString.length() == DATE_WITH_SECONDS_LENGTH
                        && dateTimeString.indexOf("T") == ISO_DATE_T_POS) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS_WITH_T_HOUR_MINUTE_AND_SECOND);
                } else if (dateTimeString.indexOf("-") == YEAR_FIRST_DASH_POS
                        && dateTimeString.length() == DATE_WITH_SECONDS_LENGTH
                        && dateTimeString.indexOf(" ") == NORMAL_DATE_SPACE_POS) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_MINUTE_AND_SECOND);
                } else if (dateTimeString.indexOf("-") == YEAR_FIRST_DASH_POS
                        && dateTimeString.length() == DATE_WITH_MINUTES_LENGTH) {
                    formatter = DateTimeFormat.forPattern(DAY_FORMAT_DASH_BACKWARDS_WITH_HOUR_AND_MINUTE);
                } else if (dateTimeString.indexOf("-") == YEAR_FIRST_DASH_POS
                        && dateTimeString.length() == DATE_ONLY_LENGTH) {
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

    public static Calendar createTimestampStartDay(String dateTimeString) {
        Calendar cal = createTimestamp(dateTimeString);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    public static Calendar createTimestampEndDay(String dateTimeString) {
        Calendar cal = createTimestamp(dateTimeString);
        cal.set(Calendar.HOUR_OF_DAY, LAST_HOUR);
        cal.set(Calendar.MINUTE, LAST_MINUTE);
        cal.set(Calendar.SECOND, LAST_SECOND);
        cal.set(Calendar.MILLISECOND, LAST_MILLISECOND);

        return cal;
    }
}
