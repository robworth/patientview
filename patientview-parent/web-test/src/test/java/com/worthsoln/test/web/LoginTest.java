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
package com.worthsoln.test.web;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class LoginTest {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${user.username}")
    private String username;

    @Value("${user.password}")
    private String password;

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl(baseUrl);
    }

    @Test
    public void testIndexLoginSuccess() {
        beginAt("/");                            // start
        assertTitleEquals("Patient View");     // the home page should be titled "Patient View"
        clickButtonWithText("Login");           // there should be a "Login" button

        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", username);
        setTextField("j_password", password);
        submit();

        assertLinkPresentWithText("Logout");     // we should now be logged in
    }

    @Test
    public void testIndexLoginFailure() {
        beginAt("/");                            // start
        assertTitleEquals("Patient View");     // the home page should be titled "Patient View"
        clickButtonWithText("Login");           // there should be a "Login" button

        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", "123");
        setTextField("j_password", "456");
        submit();

        assertTextPresent("Please log in below");
        assertLinkNotPresentWithText("Logout");  // we should now in login.jsp
    }

    @Test
    public void testLoginSuccess() {
        beginAt("/login.do");                   // start
        assertTitleEquals("Patient View");     // the home page should be titled "Patient View"
        clickButtonWithText("Login");           // there should be a "Login" button
        assertTextPresent("Please log in below");

        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", username);
        setTextField("j_password", password);
        submit();

        assertLinkPresentWithText("Logout");  // we should now be logged in
    }

    @Test
    public void testLoginFailure() {
        beginAt("/login.do");                   // start
        assertTitleEquals("Patient View");     // the home page should be titled "Patient View"
        clickButtonWithText("Login");           // there should be a "Login" button
        assertTextPresent("Please log in below");

        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", "12345");
        setTextField("j_password", "456678");
        submit();

        assertLinkNotPresentWithText("Logout");      // we should now in login.jsp
        assertTextFieldEquals("j_username", "");    // username and password should be empty when login failed.
        assertTextFieldEquals("j_password", "");
        assertTextPresent("Please log in below");
    }
}
