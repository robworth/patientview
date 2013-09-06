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
        assertTextNotPresent(error_message);
        clickLinkWithText("Genetics");
        assertTextNotPresent(error_message);
        clickLinkWithText("Deafness");
        assertTextNotPresent(error_message);
        clickLinkWithText("Medicines");
        assertTextNotPresent(error_message);

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

    @Test
    public void testProfessionalsViewHNF1BPatient() {
        testProfessionalsViewPatient();

        clickLinkWithText("Edit",1);
        assertTextPresent("Disease Group: HNF1b Mutations");
        assertTextFieldEquals("forename", "patient5");
        assertTextFieldEquals("dob", "01-08-1985");

        clickLinkWithText("Genetics");
        assertTextNotPresent(error_message);
        clickLinkWithText("HNF1B Misc");
        assertTextNotPresent(error_message);
        clickLinkWithText("Medical Results");
        assertTextNotPresent(error_message);

    }

    @Test
    public void testProfessionalsViewOthersPatient() {
        testProfessionalsViewPatient();

        clickLinkWithText("Edit",0);
        assertTextPresent("Disease Group: STEC-associated HUS");
        assertTextFieldEquals("forename", "patient6");
        assertTextFieldEquals("dob", "01-08-1985");

        clickLinkWithText("Medical Results");
        assertTextNotPresent(error_message);
        clickLinkWithText("Genetics");
        assertTextNotPresent(error_message);
        clickLinkWithText("Medicines");
        assertTextNotPresent(error_message);

    }
}
