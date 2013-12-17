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
