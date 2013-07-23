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
public class HelpPageTest {

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl("http://patientview.staging.solidstategroup.com");
    }

    @Test
    public void testIndexLogin() {
        beginAt("/help.do");                   // start
        assertTitleEquals("Renal Patient View");     // the home page should be titled "Home"
        assertLinkPresentWithText("log in");    // there should be a "Login" link
        assertTextPresent("Questions about your own information");    // there should be text on page body
    }
}
