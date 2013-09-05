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
public class MainScreenTest extends BaseTest{

    @Test
    public void testProfessionalsMainScreen() {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("Professionals");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", professional_username);
        setTextField("password", professional_password);
        submit();

        assertLinkPresentWithText("| Log-out");     // we should now be logged in
        assertLinkPresentWithText("| Enter New Patient");
        assertLinkNotPresentWithText("Modify Patients");
    }

    @Test
    public void testProfessionalsAddPatient() {
        testProfessionalsMainScreen();
        clickLinkWithText("| Enter New Patient");
        assertTextPresent("New Patient");

        assertFormElementPresent("patientId");
        setTextField("patientId", "6810341560");

        assertFormElementPresent("nhsNumberType");
        selectOption("nhsNumberType", "NHS");

        assertFormElementPresent("diseaseGroup");
        selectOptionByValue("diseaseGroup", "ARPKD");

        clickButtonWithText("Add Patient");
        assertTextPresent("Demographics");
        assertTextPresent("Disease Group: ARPKD - autosomal recessive polycystic kidney disease");
    }

    @Test
    public void testProfessionalsViewPatient() {
        testProfessionalsMainScreen();

        clickLinkWithText("Existing Patients");
        getTestingEngine().clickLink("patientsListingPageLink");
        assertTextPresent("View Patient Details");

    }
}
