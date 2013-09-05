package com.worthsoln.test.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfessionalMainScreenTest extends BaseTest{

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

    @Test
    public void testProfessionalsViewALPORTPatient() {
        testProfessionalsViewPatient();

        clickLinkWithText("Edit",2);
        assertTextPresent("Disease Group: Alport");
        assertTextFieldEquals("forename", "patient4");
        assertTextFieldEquals("dob", "01-08-1985");

        clickLinkWithText("Medical Results");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Genetics");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Deafness");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Medicines");
        assertTextNotPresent("Whoops!");

    }

    @Test
    public void testProfessionalsViewSRNSPatient() {
        testProfessionalsViewPatient();

        clickLinkWithText("Edit",4);
        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient3");
        assertSelectedOptionsEqual("diagnosis", new String[]{"SRNS"});

        clickLinkWithText("Diagnosis");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("First Visit");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Follow Up");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Pathology");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Relapse");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Hospitalisation");
        assertTextNotPresent("Whoops!");

    }

    @Test
    public void testProfessionalsViewHNF1BPatient() {
        testProfessionalsViewPatient();

        clickLinkWithText("Edit",1);
        assertTextPresent("Disease Group: HNF1b Mutations");
        assertTextFieldEquals("forename", "patien5");
        assertTextFieldEquals("dob", "01-08-1985");

        clickLinkWithText("Genetics");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("HNF1B Misc");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Medical Results");
        assertTextNotPresent("Whoops!");

    }

    @Test
    public void testProfessionalsViewOthersPatient() {
        testProfessionalsViewPatient();

        clickLinkWithText("Edit",0);
        assertTextPresent("Disease Group: STEC-associated HUS");
        assertTextFieldEquals("forename", "patien6");
        assertTextFieldEquals("dob", "01-08-1985");

        clickLinkWithText("Medical Results");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Genetics");
        assertTextNotPresent("Whoops!");
        clickLinkWithText("Medicines");
        assertTextNotPresent("Whoops!");

    }
}
