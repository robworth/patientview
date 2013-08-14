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
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class LoginTest {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${patient.username}")
    private String patient_username;

    @Value("${patient.password}")
    private String patient_password;

    @Value("${patient.dateOfBirth}")
    private String dateOfBirth;

    @Value("${professional.username}")
    private String professional_username;

    @Value("${professional.password}")
    private String professional_password;

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl(baseUrl);

    }

    private void prepareAndBeginAt(String url) {
        beginAt(url);    // start

        WebClient webClient = ((HtmlUnitTestingEngineImpl)getTestingEngine()).getWebClient();
        webClient.setJavaScriptEnabled(true);
        webClient.setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setTimeout(35000);
        webClient.setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(30000);
    }

    @Test
    public void testPatientsIndexLoginSuccess() {
        prepareAndBeginAt("/");

        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("| Patients");

        assertFormElementPresent("username");
        assertFormElementPresent("password");
        assertFormElementPresent("dateOfBirth");
        setTextField("username", patient_username);
        setTextField("password", patient_password);
        setTextField("dateOfBirth", dateOfBirth);
        submit();
        System.out.println(getPageSource());
        assertLinkPresentWithText("| Log-out");     // we should now be logged in
    }

    @Test
    public void testPatientsIndexLoginFailure() {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("| Patients");

        assertFormElementPresent("username");
        assertFormElementPresent("password");
        assertFormElementPresent("dateOfBirth");
        setTextField("username", "12");
        setTextField("password", "12");
        setTextField("dateOfBirth", "01-01-1990");
        submit();
        System.out.println(getPageSource());
        assertTextPresent("Login failed");
        assertLinkNotPresentWithText("Logout");  // still in login page
    }

    @Test
    public void testProfessionalsIndexLoginSuccess() {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("Professionals");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", professional_username);
        setTextField("password", professional_password);
        submit();
        System.out.println(getPageSource());
        assertLinkPresentWithText("| Log-out");     // we should now be logged in
    }

    @Test
    public void testProfessionalsIndexLoginFailure() {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("Professionals");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", "12");
        setTextField("password", "12");
        submit();

        System.out.println(getPageSource());
        assertTextPresent("Login failed");
        assertLinkNotPresentWithText("Logout");  // still in login page
    }
}
