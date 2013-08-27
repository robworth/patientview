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
public class LoginTest extends BaseTest{

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

        assertLinkPresentWithText("| Log-out");     // we should now be logged in

        logout();
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
//        System.out.println(getPageSource());
        assertLinkPresentWithText("| Log-out");     // we should now be logged in

        logout();
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

//        System.out.println(getPageSource());
        assertTextPresent("Login failed");
        assertLinkNotPresentWithText("Logout");  // still in login page
    }


    @Test
    public void testAdminIndexLoginSuccess() {
        prepareAndBeginAt("/admins");

        assertTextPresent("Log In");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", superadmin_username);
        setTextField("password", superadmin_password);
        submit();

        assertLinkPresentWithText("| Log-out");     // we should now be logged in
        assertTextPresent("Logged In");
        assertLinkPresentWithText("Users");
        assertLinkPresentWithText("Consultants");
        assertLinkPresentWithText("Patients (All)");
        assertLinkPresentWithText("Patients (User)");
        assertLinkPresentWithText("Issues");

        logout();

    }


    @Test
    public void testAdminIndexLoginFailure() {
        prepareAndBeginAt("/admins");

        assertTextPresent("Log In");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", "12");
        setTextField("password", "12");
        submit();
        System.out.println(getPageSource());
        assertTextPresent("Login failed");
        assertLinkNotPresentWithText("Logout");  // still in login page
    }

    public void logout(){
        clickLinkWithText("| Log-out");
        assertLinkPresentWithText("Patients");
        assertLinkPresentWithText("Professionals");
        assertLinkPresentWithText("Home");
    }
}
