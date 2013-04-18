package com.worthsoln.test.utils;

import com.worthsoln.patientview.utils.TimestampUtils;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.junit.Test;

import java.util.Calendar;

public class TimestampUtilsTest extends TestCase {

    @Test
    public void testCreateTimestampGoodDates() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime(((Calendar) TimestampUtils
                .createTimestamp("2012-03-15")).getTime().getTime()).toString());
        assertEquals("Timestamp not equal", "2012-03-15T04:34:00.000Z", new DateTime(((Calendar) TimestampUtils
                .createTimestamp("2012-03-15T04:34")).getTime().getTime()).toString());
        assertEquals("Timestamp not equal", "2012-03-15T14:56:45.000Z", new DateTime(((Calendar) TimestampUtils
                .createTimestamp("2012-03-15T14:56:45")).getTime().getTime()).toString());
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime(((Calendar) TimestampUtils
                .createTimestamp("22/05/2011")).getTime().getTime()).toString());

    }

    @Test(expected = org.joda.time.IllegalFieldValueException.class)
    public void testCreateTimestampBadDates() throws IllegalFieldValueException {
        TimestampUtils.createTimestamp("2012-03-32");
    }
}
