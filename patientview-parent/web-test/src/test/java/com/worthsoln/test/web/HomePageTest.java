package com.worthsoln.test.web;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class HomePageTest {

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl("http://patientview.staging.solidstategroup.com");
    }

    @Test
    public void testIndexLogin() {
        beginAt("/");                   // start
        assertTitleEquals("Renal Patient View");     // the home page should be titled "Home"
        assertLinkPresentWithText("log in");    // there should be a "Login" link
        assertTextPresent("Renal PatientView is a project of RIXG");    // there should be text on page body
    }
}
