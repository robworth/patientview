package com.worthsoln.test.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientMainScreenTest extends BaseTest{


    public void testPatientMainScreen(String username, String password, String dob) {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("| Patients");

        assertFormElementPresent("username");
        assertFormElementPresent("password");
        assertFormElementPresent("dateOfBirth");
        setTextField("username", username);
        setTextField("password", password);
        setTextField("dateOfBirth", dob);
        submit();

        assertLinkPresentWithText("Demographics");     // we should now be logged in
        assertLinkPresentWithText("Diagnosis");
        assertLinkPresentWithText("First Visit");
        assertLinkPresentWithText("Follow Up");
        assertLinkPresentWithText("Pathology");
        assertLinkPresentWithText("Relapse");
        assertLinkPresentWithText("Hospitalisation");
    }

    @Test
    public void testSRNSPatientScreen() {
        testPatientMainScreen("patient3","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient3");
        assertSelectedOptionsEqual("renalUnit", new String[]{"SRNS"});

        testTabs();
    }

    @Test
    public void testALPORTPatientScreen() {
        testPatientMainScreen("patient4","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient4");
        assertSelectedOptionsEqual("renalUnit", new String[]{"Alport"});

        testTabs();
    }

    @Test
    public void testHNF1BPatientScreen() {
        testPatientMainScreen("patient5","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient5");
        assertSelectedOptionsEqual("renalUnit", new String[]{"HNF1B"});

        testTabs();
    }
    @Test
    public void testOthersPatientScreen() {
        testPatientMainScreen("patient6","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient6");
        assertSelectedOptionsEqual("renalUnit", new String[]{"STECHUS"});

        testTabs();
    }

    public void testTabs() {

        clickLinkWithText("Diagnosis");
        assertTextNotPresent(error_message);
        clickLinkWithText("First Visit");
        assertTextNotPresent(error_message);
        clickLinkWithText("Follow Up");
        assertTextNotPresent(error_message);
        clickLinkWithText("Pathology");
        assertTextNotPresent(error_message);
        clickLinkWithText("Relapse");
        assertTextNotPresent(error_message);
        clickLinkWithText("Hospitalisation");
        assertTextNotPresent(error_message);
    }


}
