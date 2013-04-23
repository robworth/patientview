package com.worthsoln.test.utils;

import com.worthsoln.patientview.utils.TimestampUtils;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class TimestampUtilsTest {

    @Test
    public void testCreateTimestampGoodDay() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime((TimestampUtils
                .createTimestamp("2012-03-15")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDayHourMin() {
        assertEquals("Timestamp not equal", "2012-03-15T04:34:00.000Z", new DateTime((TimestampUtils
                .createTimestamp("2012-03-15T04:34")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDayHourMinSec() {
        assertEquals("Timestamp not equal", "2012-03-15T14:56:45.000Z", new DateTime((TimestampUtils
                .createTimestamp("2012-03-15T14:56:45")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDaySlash() {
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime((TimestampUtils
                .createTimestamp("22/05/2011")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDaySlashBackwards() {
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime((TimestampUtils
                .createTimestamp("2011/05/22")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDayDash() {
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime((TimestampUtils
                .createTimestamp("22-05-2011")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampBadDayOfMonth() {
        assertNull(TimestampUtils.createTimestamp("2012-02-32"));
    }

    @Test
    public void testCreateTimestampBadMonth() {
        assertNull(TimestampUtils.createTimestamp("2012-13-15"));
    }

    @Test
    public void testCreateTimestampBadDate() throws IllegalFieldValueException {
        assertNull(TimestampUtils.createTimestamp("asdasdasasd"));
    }

    @Test
    public void testCreateTimestampStartDay() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime((TimestampUtils
                .createTimestampStartDay("2012-03-15")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampStartDayWithHoursEtc() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime((TimestampUtils
                .createTimestampStartDay("2012-03-15T12:43:54.878")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampEndDay() {
        assertEquals("Timestamp not equal", "2012-03-15T23:59:59.999Z", new DateTime((TimestampUtils
                .createTimestampEndDay("2012-03-15")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampEndDayWithHoursEtc() {
        assertEquals("Timestamp not equal", "2012-03-15T23:59:59.999Z", new DateTime((TimestampUtils
                .createTimestampEndDay("2012-03-15T12:43:54.878")).getTime().getTime()).toString());
    }

}
