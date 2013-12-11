package org.patientview.radar.test.util;


import org.junit.Assert;
import org.junit.Test;
import org.patientview.radar.util.RadarUtility;

import java.util.Calendar;
import java.util.Date;

public class RadarUtilityTest {

    @Test
    public void testIsNhsNumberValid() throws Exception {
        String validCode = "4010232137";
        Assert.assertTrue(RadarUtility.isNhsNumberValid(validCode));
    }

    @Test
    public void testIsNhsNumberValidWithUpperCaseLetters() throws Exception {
        String validCode = "3001837DBG";
        Assert.assertTrue(RadarUtility.isNhsNumberValidWhenUppercaseLettersAreAllowed(validCode));
    }

    /**
     * Test the date formatter functionality
     *
     *
     */
    @Test
    public void testParseTest(){
        final String testDateString1 = "08.02.94";
        final String testDateString2 = "28-11-95";
        final String testDateString3 = "1964-05-12";

        Date date = RadarUtility.parseDate(testDateString1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertTrue("The year for " + testDateString1 + " should 1994", calendar.get(Calendar.YEAR) == 1994);
        Assert.assertTrue("The month for " + testDateString1 + " should 2", calendar.get(Calendar.MONTH) == 1);
        Assert.assertTrue("The day of the month for " + testDateString1 + " should 2", calendar.get(Calendar.DAY_OF_MONTH) == 8);

        date = RadarUtility.parseDate(testDateString2);

        calendar.setTime(date);
        Assert.assertTrue("The year for " + testDateString2 + " should 1995", calendar.get(Calendar.YEAR) == 1995);
        Assert.assertTrue("The month for " + testDateString2 + " should 11", calendar.get(Calendar.MONTH) == 10);
        Assert.assertTrue("The day of the month for " + testDateString2 + " should 28", calendar.get(Calendar.DAY_OF_MONTH) == 28);

        date = RadarUtility.parseDate(testDateString3);

        calendar.setTime(date);
        Assert.assertTrue("The year for " + testDateString3 + " should 1964", calendar.get(Calendar.YEAR) == 1964);
        Assert.assertTrue("The month for " + testDateString3 + " should 5", calendar.get(Calendar.MONTH) == 4);
        Assert.assertTrue("The day of the month for " + testDateString3 + " should 12", calendar.get(Calendar.DAY_OF_MONTH) == 12);

    }


}