package com.solidstategroup.radar.test.util;

import com.solidstategroup.radar.util.TripleDes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TripleDesTest {

    @Test
    public void testTripleDes() throws Exception {
        // Password to test
        String password = "password12";

        // Encrypt and decrypt
        String encryptedBytes = TripleDes.encrypt(password);
        String decryptedPassword = TripleDes.decrypt(encryptedBytes);

        assertEquals("Passwords do not match after encryption and decryption", password, decryptedPassword);
    }

}
