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
import org.junit.Ignore;
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
public class MyDetailsTest {

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
    public void testMyDetails() {
        login();
        assertLinkPresentWithText("My Details");

        clickLinkWithText("My Details");
        assertTextPresent("My Details");

        // Explain this link should be checked when patient details exists.
        if (!getPageSource().contains("Patient details not uploaded")) {
            assertLinkPresentWithText("Explain this");
            clickLinkWithText("Explain this");
            gotoWindowByTitle("Renal PatientView - Transplant status");
            assertTextPresent("TRANSPLANT STATUS from UK Transplant");
        }
    }

    @Test
    public void testPatientInfo() {
        login();
        assertLinkPresentWithText("Patient Info");

        clickLinkWithText("Patient Info");
        assertTextPresent("Patient Info");
        assertTextPresent("Further Information");
    }

    @Test
    public void testAboutMe() {
        login();
        assertLinkPresentWithText("About Me");

        clickLinkWithText("About Me");
        assertTextPresent("About Me");

        assertTextPresent("Things people should know about me");
        assertTextPresent("Things I'd like to talk about");

    }

    @Test
    public void testMedicines() {
        login();
        assertLinkPresentWithText("Medicines");

        clickLinkWithText("Medicines");
        assertTextPresent("This is a list of medicines");

        assertLinkPresentWithText("Medline Plus");
        clickLinkWithText("Medline Plus");
        gotoWindowByTitle("MedlinePlus: Drugs, Herbs and Supplements: MedlinePlus");
        assertTextPresent("Drugs, Supplements, and Herbal Information");

    }

    @Test
    public void testLetters() {
        login();
        assertLinkPresentWithText("Letters");

        clickLinkWithText("Letters");
        assertTextPresent("Letters are only shown where they can be retrieved");

    }

    @Ignore
    public void testMessages() {
        login();
        assertLinkPresentWithText("Messages");

        clickLinkWithText("Messages");
        assertTextPresent("Messages");

        assertButtonPresentWithText("+ Create Message");

        clickButtonWithText("+ Create Message");
        assertFormElementPresent("recipientId");
        assertFormElementPresent("subject");
        assertFormElementPresent("content");
        assertLinkPresentWithText("Close");
        assertButtonPresentWithText("Send");

    }

    @Test
    public void testContact() {
        login();
        assertLinkPresentWithText("Contact");

        clickLinkWithText("Contact");
        assertTextPresent("Contact Details");

        assertFormElementPresent("message");
        assertFormElementPresent("email");
        assertButtonPresentWithText("Send");

        setTextField("message", "test-message");
        setTextField("email", "test@patientview.com");
        submit();

        assertTextPresent("Your contact form was successfully submitted.");

    }

    @Test
    public void testHelp() {
        login();
        assertLinkPresentWithText("Help?");

        clickLinkWithText("Help?");
        assertTextPresent("Renal PatientView Help page");

        assertLinkPresentWithText("click here to send a message.");
        clickLinkWithText("click here to send a message.");
        assertTextPresent("Contact Details");

    }

    private void login() {
        beginAt("/");                            // start

        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", username);
        setTextField("j_password", password);
        submit();
        assertLinkPresentWithText("Logout");    // we should now be logged in
    }
}
