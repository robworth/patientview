package com.worthsoln.test.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminMainScreenTest extends BaseTest{

    @Test
    public void testAdminMainScreen() {
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

    }

    @Test
    public void testUsersTab() {
        testAdminMainScreen();
        clickLinkWithText("Users");
        assertTextPresent("Admin - Users");
        assertButtonPresentWithText("Add a new User");
    }

    @Test
    public void testConsultantsTab() {
        testAdminMainScreen();
        clickLinkWithText("Consultants");
        assertTextPresent("Admin - Consultants");
        assertButtonPresentWithText("Add a new Consultant");
    }

    @Test
    public void testPatientsAllTab() {
        testAdminMainScreen();
        clickLinkWithText("Patients (All)");
        assertTextPresent("Admin - Patients (All)");
        assertButtonPresentWithText("Export to pdf");
    }

    @Test
    public void testPatientsUserTab() {
        testAdminMainScreen();
        clickLinkWithText("Patients (User)");
        assertTextPresent("Admin - Patients (Users)");
        assertButtonPresentWithText("Export to pdf");
    }

    @Test
    public void testIssuesTab() {
        testAdminMainScreen();
        clickLinkWithText("Issues");
        assertTextPresent("Admin - Issues");
        assertButtonPresentWithText("Add a new Issue");
    }
}
