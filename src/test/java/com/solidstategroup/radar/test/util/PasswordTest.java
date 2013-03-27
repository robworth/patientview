package com.solidstategroup.radar.test.util;

import com.solidstategroup.radar.model.user.User;
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
