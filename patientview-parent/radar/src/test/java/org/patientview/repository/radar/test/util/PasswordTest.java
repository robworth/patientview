package org.patientview.repository.radar.test.util;

import org.patientview.model.radar.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class PasswordTest {

    @Test
    public void testPasswordHash() throws Exception {
        assertNotNull(User.getPasswordHash("123abc"));
    }
}
