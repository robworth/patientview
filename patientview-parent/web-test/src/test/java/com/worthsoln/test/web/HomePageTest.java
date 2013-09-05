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
public class HomePageTest {

    @Value("${base.url}")
    private String baseUrl;

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl(baseUrl);
    }

    @Test
    public void testIndex() {
        beginAt("/");                            // start
        assertLinkPresentWithText("Providing up-to-date medical information " +
                "for patients and healthcare professionals");
        assertTitleEquals("Patient View");      // the home page should be titled "Patient View"
        assertLinkPresentWithText("Home");       // there should be a "Home" link
        assertLinkPresentWithText("I Want To Join");          // there should be a "I Want To Join" link
        assertLinkPresentWithText("Help?");                    // there should be a "Help?" link
        assertLinkPresentWithText("Forgotten password?");   // there should be a "Forgotten password?" link
        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        assertButtonPresentWithText("Login");                        // there should be a "Login" button
        assertLinkPresentWithText("RIXG");                           // there should be a "RIXG" link
        assertLinkPresentWithText("list of units");                // there should be a "list of units" link
        assertLinkPresentWithText("information links");           // there should be a "information links" link
        assertLinkPresentWithText("demo");                          // there should be a "demo" link
        assertLinkPresentWithText("I want to join");              // there should be a "I want to join" link
        assertTextPresent("Renal PatientView is a project of RIXG");    // there should be text on page body
    }

    @Test
    public void testLink() {
        beginAt("/");
        // Providing link
        clickLinkWithText("Providing up-to-date medical information " +
                "for patients and healthcare professionals");
        assertTextPresent("PatientView is only available from some UK renal units");

        // Home link
        clickLinkWithText("Home");
        assertTextPresent("RPV is funded by contributions from renal units");

        // I Want To Join link
        clickLinkWithText("I Want To Join");
        assertTextPresent("Join Patient View");
        assertTextPresent("Please enter your details. You need to be a patient of a renal unit.");

        // Help link
        clickLinkWithText("Help?");
        assertTextPresent("Renal PatientView Help page");

        // Forgotten password link
        clickLinkWithText("Forgotten password?");
        assertTextPresent("Forgotten password");
        assertTextPresent("Please enter your user name and email address.");

        beginAt("/");                  // return to Home page

        // RIXG link
        clickLinkWithText("RIXG");
        gotoWindowByTitle("RIXG - the Renal Information Exchange Group");
        assertTitleEquals("RIXG - the Renal Information Exchange Group");
        gotoRootWindow();

        // list of units link
        clickLinkWithText("list of units");
        gotoWindowByTitle("RIXG - Where");
        assertTitleEquals("RIXG - Where");
        gotoRootWindow();

        // information links
        clickLinkWithText("information links");
        assertTextPresent("Information Links");
        assertTextPresent("The information links shown here");

        beginAt("/");                    // return to Home page

        clickLinkWithText("demo");
        assertTextPresent("Demo logins");
        assertTextPresent("Here are some logins that you can use");

        beginAt("/");                    // return to Home page

        clickLinkWithText("I want to join");
        assertTextPresent("Join Patient View");
        assertTextPresent("You need to be a patient of a renal unit");

    }
}
