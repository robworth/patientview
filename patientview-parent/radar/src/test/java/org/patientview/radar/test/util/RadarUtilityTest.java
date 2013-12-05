package org.patientview.radar.test.util;


import org.junit.Assert;
import org.junit.Test;
import org.patientview.radar.util.RadarUtility;

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
}