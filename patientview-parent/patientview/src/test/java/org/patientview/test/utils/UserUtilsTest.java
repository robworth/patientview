package org.patientview.test.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.patientview.patientview.user.UserUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class UserUtilsTest {

    @Test
    public void testNHSNoMissingDigits() {
        assertFalse("Invalid NHS No passed validation", UserUtils.isNhsNumberValid("123456789"));
    }

    @Test
    public void testNHSNoExtraDigits() {
        assertFalse("Invalid NHS No passed validation", UserUtils.isNhsNumberValid("12345678901"));
    }

    @Test
    public void testBadNHSNoChecksum() {
        assertFalse("Invalid NHS No passed validation", UserUtils.isNhsNumberValid("7428721471"));
    }

    @Test
    public void testValidNHSNoChecksum() {
        assertTrue("Valid NHS No did not pass validation", UserUtils.isNhsNumberValid("7428721474"));
    }

    @Test
    public void testLowercaseNHSNo() {
        assertFalse("Invalid NHS No passed validation", UserUtils.isNhsNumberValid("12f45a6789"));
    }

    @Test
    public void testOverridingValidationWithUppercaseNHSNo() {
        assertTrue("Invalid NHS No with uppercase letters did not pass validation although validation should have " +
                "been overridden", UserUtils.isNhsNumberValidWhenUppercaseLettersAreAllowed("12F45A6789"));
    }
}