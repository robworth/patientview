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
