package com.worthsoln.test.web;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 * Use the professional login page to test login Radar.
 * there should be a radar-login-web-test.properties file in filter folder.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class RadarLoginTest {

    @Value("${radar.base.url}")
    private String baseUrl;

    @Value("${radar.user.username}")
    private String username;

    @Value("${radar.user.password}")
    private String password;

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl(baseUrl);
    }

    @Test
    public void testIndexLoginSuccess() {
        beginAt("/");
        WebClient webClient = ((HtmlUnitTestingEngineImpl)getTestingEngine()).getWebClient();
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("Professionals");                 // click the Professionals link

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", username);
        setTextField("password", password);
        clickButtonWithText("Enter");                       // submit

        assertLinkPresentWithText("| Log-out");
        assertLinkPresentWithText("| Enter New Patient |");

    }
}
