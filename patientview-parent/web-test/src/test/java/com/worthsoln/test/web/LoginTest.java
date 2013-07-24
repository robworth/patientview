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
