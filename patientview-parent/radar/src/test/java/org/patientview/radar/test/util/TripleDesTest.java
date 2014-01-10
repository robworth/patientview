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

package org.patientview.radar.test.util;

import org.patientview.radar.util.TripleDes;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TripleDesTest {

    @Test
    public void testTripleDes() throws Exception {
        // Password to test
        String password = "password12";

        // Encrypt and decrypt
        byte[] encryptedBytes = TripleDes.encrypt(password);
        String decryptedPassword = TripleDes.decrypt(encryptedBytes);

        assertEquals("Passwords do not match after encryption and decryption", password, decryptedPassword);
    }

    @Test
    public void testTripleDesEncrypt() throws Exception {
        // Expected value  - copied byte array from database
        byte[] expectedValue = new byte[]{112, 83, -102, 50, -29, 42, -63, 68, -91, -80, 56, 124, 16, 36, -128, -119};

        // I know that byte array corresponds to this (it was somebody's NHS number in demographics table)
        String toEncrypt = "4921148228";
        byte[] encryptedValue = TripleDes.encrypt(toEncrypt);

        // Assert it worked
        assertTrue("String incorrectly encrypted", Arrays.equals(expectedValue, encryptedValue));
    }

}
