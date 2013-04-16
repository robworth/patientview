package com.worthsoln.test.security;

import com.worthsoln.patientview.logon.LogonUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class PasswordTest {

    @Test
    public void testPasswordHash() throws Exception {
        assertNotNull(LogonUtils.hashPassword("123abc"));
    }
}
