package com.worthsoln.test.web;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class TestResultTest {

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
    public void testEnterMyOwnResults() {
        login();
        assertLinkPresentWithText("Enter My Own Results");
        clickLinkWithText("Enter My Own Results");

        assertTextPresent("Enter My Own Results");
        assertLinkPresentWithText("Blood Pressure");
        assertLinkPresentWithText("Glucose Values");
        assertLinkPresentWithText("Weight");
        assertLinkPresentWithText("Comment");

        // Blood Pressure link
        checkBloodPressure();
        gotoRootWindow();
        clickLinkWithText("Enter My Own Results");

        // Glucose Values link
        checkGlucoseValues();
        clickLinkWithText("Enter My Own Results");

        // Weight link
        checkWeight();
        clickLinkWithText("Enter My Own Results");

        // Weight link
        checkComment();

    }

    @Test
    public void testResults() {
        login();
        clickLinkWithExactText("Results");

        assertTextPresent("Results");
        assertTextPresent("Result panels");
        assertLinkPresentWithText("2");
        assertLinkPresentWithText("3");
        assertLinkPresentWithText("4");
        assertLinkPresentWithText("5");
        assertLinkPresentWithText("6");
        assertLinkPresentWithText("Next");
        assertLinkPresentWithText("Last");

        assertLinkPresentWithText("Urea");
        assertLinkPresentWithText("Creatinine");
        assertLinkPresentWithText("K");
        assertLinkPresentWithText("Ca");
        assertLinkPresentWithText("PO4");
        assertLinkPresentWithText("Hb");
        assertLinkPresentWithText("wbc");
        assertLinkPresentWithText("plats");
        assertLinkPresentWithText("eGFR");
        assertLinkPresentWithText("Comment");

        clickLinkWithText("Urea");
        gotoWindowByTitle("Urea (Renal PatientView)");
        assertTextPresent("Urea is a small molecule produced from protein");
        gotoRootWindow();

        clickLinkWithText("Creatinine");
        gotoWindowByTitle("Creatinine (Renal PatientView)");
        assertTextPresent("This is the best routine blood test for measuring");
        gotoRootWindow();

        clickLinkWithText("K");
        gotoWindowByTitle("Potassium (Renal PatientView)");
        assertTextPresent("Potassium comes from diet");
        gotoRootWindow();

        clickLinkWithText("Ca");
        gotoWindowByTitle("Calcium (Renal PatientView)");
        assertTextPresent("Low calcium is usual in people with untreated kidney disease");
        gotoRootWindow();

        clickLinkWithText("PO4");
        gotoWindowByTitle("Phosphate (Renal PatientView)");
        assertTextPresent("Phosphate levels are high in kidney failure");
        gotoRootWindow();

        clickLinkWithText("Hb");
        gotoWindowByTitle("Haemoglobin (Renal PatientView)");
        assertTextPresent("Haemoglobin is the red stuff in blood");
        gotoRootWindow();

        clickLinkWithText("wbc");
        gotoWindowByTitle("White blood cells (Renal PatientView)");
        assertTextPresent("White blood cells fight infection in the body.");
        gotoRootWindow();

        clickLinkWithText("plats");
        // The plats links page has the same title with Hb link's
        //gotoWindowByTitle("Haemoglobin (Renal PatientView)");
        //assertTextPresent("Platelets are important in the formation");
        gotoRootWindow();

        clickLinkWithText("eGFR");
        gotoWindowByTitle("eGFR (Renal PatientView)");
        assertTextPresent("eGFR uses the result of your Creatinine test ");
        gotoRootWindow();

        clickLinkWithText("Comment");
        assertTextPresent("Test results for");
        gotoRootWindow();

        checkNo2Panel();
        checkNo3Panel();
        checkNo4Panel();
        checkNo5Panel();
        checkNo6Panel();
    }

    private void checkNo2Panel() {
        clickLinkWithText("2");
        assertLinkPresentWithText("First");
        assertLinkPresentWithText("Prev");
        assertLinkPresentWithText("1");
        assertLinkPresentWithText("3");
        assertLinkPresentWithText("4");
        assertLinkPresentWithText("5");
        assertLinkPresentWithText("6");
        assertLinkPresentWithText("Next");
        assertLinkPresentWithText("Last");

        assertLinkPresentWithText("Alb");
        assertLinkPresentWithText("CRP");
        assertLinkPresentWithText("Na");
        assertLinkPresentWithText("HCO3");
        assertLinkPresentWithText("PCR");
        assertLinkPresentWithText("ACR");
        assertLinkPresentWithText("Cholest");
        assertLinkPresentWithText("TG");
        assertLinkPresentWithText("Gluc");
        assertLinkPresentWithText("AdjCa");

        clickLinkWithText("Alb");
        gotoWindowByTitle("Albumin (Renal PatientView)");
        assertTextPresent("Albumin is a protein in human blood");
        gotoRootWindow();

        clickLinkWithText("CRP");
        gotoWindowByTitle("CRP (Renal PatientView)");
        assertTextPresent("CRP is one of the proteins normally present in blood");
        gotoRootWindow();

        clickLinkWithText("Na");
        gotoWindowByTitle("Sodium (Renal PatientView)");
        assertTextPresent("Sodium is found in common salt");
        gotoRootWindow();

        clickLinkWithText("HCO3");
        gotoWindowByTitle("HCO3 (Renal PatientView)");
        assertTextPresent("Bicarbonate is a chemical in the blood");
        gotoRootWindow();

        clickLinkWithExactText("PCR");
        gotoWindowByTitle("PCR (Renal PatientView)");
        assertTextPresent("These tests measure how much protein is leaking");
        gotoRootWindow();

        clickLinkWithText("ACR");
        gotoWindowByTitle("ACR (Renal PatientView)");
        assertTextPresent("This measures how much of a particular protein");
        gotoRootWindow();

        clickLinkWithText("Cholest");
        gotoWindowByTitle("Lipids (Renal PatientView)");
        assertTextPresent("Cholesterol is the best-known");
        gotoRootWindow();

        // The TG links page has the same title with Cholest link's
        clickLinkWithText("TG");
        //gotoWindowByTitle("Lipids (Renal PatientView)");
        //assertTextPresent("White blood cells fight infection in the body.");
        gotoRootWindow();

        clickLinkWithText("Gluc");
        gotoWindowByTitle("Glucose (Renal PatientView)");
        assertTextPresent("Glucose is a common type of sugar");
        gotoRootWindow();

        clickLinkWithText("AdjCa");
        gotoWindowByTitle("Adjusted Calcium (Renal PatientView)");
        assertTextPresent("Low calcium is usual in people with untreated kidney disease");
        gotoRootWindow();

    }

    private void checkNo3Panel() {
        clickLinkWithText("3");
        assertLinkPresentWithText("First");
        assertLinkPresentWithText("Prev");
        assertLinkPresentWithText("1");
        assertLinkPresentWithText("2");
        assertLinkPresentWithText("4");
        assertLinkPresentWithText("5");
        assertLinkPresentWithText("6");
        assertLinkPresentWithText("Next");
        assertLinkPresentWithText("Last");

        assertLinkPresentWithText("Weight");
        assertLinkPresentWithText("BPsys");
        assertLinkPresentWithText("Height");
        assertLinkPresentWithText("INR");
        assertLinkPresentWithText("Ciclo");
        assertLinkPresentWithText("Tacro");
        assertLinkPresentWithText("Siro");
        assertLinkPresentWithText("PTH");
        assertLinkPresentWithText("HbA1c");

        clickLinkWithText("Weight");
        gotoWindowByTitle("Weight and height (Renal PatientView)");
        assertTextPresent("Weight is an important guide to changes in body fluids");
        gotoRootWindow();

        clickLinkWithText("BPsys");
        gotoWindowByTitle("Blood pressure (Renal PatientView)");
        assertTextPresent("Blood pressure is very important for patients with kidney diseases");
        gotoRootWindow();

        clickLinkWithText("Height");
        gotoWindowByTitle("Weight and height (Renal PatientView)");
        assertTextPresent("Rapid changes in weight - pounds");
        gotoRootWindow();

        clickLinkWithText("INR");
        gotoWindowByTitle("INR (Renal PatientView)");
        assertTextPresent("INR measures how easily your blood");
        gotoRootWindow();

        clickLinkWithExactText("Ciclo");
        gotoWindowByTitle("Ciclosporin (Renal PatientView)");
        assertTextPresent("This is the name of a powerful anti-rejection drugs");
        gotoRootWindow();

        clickLinkWithText("Tacro");
        gotoWindowByTitle("Tacrolimus (Renal PatientView)");
        assertTextPresent("Tacrolimus is a powerful anti-rejection drug");
        gotoRootWindow();

        clickLinkWithText("Siro");
        gotoWindowByTitle("Sirolimus (Renal PatientView)");
        assertTextPresent("Sirolimus is an anti-rejection drug that is used after transplantation");
        gotoRootWindow();

        clickLinkWithText("PTH");
        gotoWindowByTitle("PTH (Renal PatientView)");
        assertTextPresent("Parathyroid hormone (PTH) is measured from time to time");
        gotoRootWindow();

        clickLinkWithText("HbA1c");
        gotoWindowByTitle("Glucose (Renal PatientView)");
        assertTextPresent("Glucose is a common type of sugar");
        gotoRootWindow();

    }

    private void checkNo4Panel() {
        clickLinkWithText("4");
        assertLinkPresentWithText("First");
        assertLinkPresentWithText("Prev");
        assertLinkPresentWithText("1");
        assertLinkPresentWithText("2");
        assertLinkPresentWithText("3");
        assertLinkPresentWithText("5");
        assertLinkPresentWithText("6");
        assertLinkPresentWithText("Next");
        assertLinkPresentWithText("Last");
    }

    private void checkNo5Panel() {
        clickLinkWithText("5");
        assertLinkPresentWithText("First");
        assertLinkPresentWithText("Prev");
        assertLinkPresentWithText("1");
        assertLinkPresentWithText("2");
        assertLinkPresentWithText("3");
        assertLinkPresentWithText("4");
        assertLinkPresentWithText("6");
        assertLinkPresentWithText("Next");
        assertLinkPresentWithText("Last");
    }

    private void checkNo6Panel() {
        clickLinkWithText("6");
        assertLinkPresentWithText("First");
        assertLinkPresentWithText("Prev");
        assertLinkPresentWithText("1");
        assertLinkPresentWithText("2");
        assertLinkPresentWithText("3");
        assertLinkPresentWithText("4");
        assertLinkPresentWithText("5");
        assertLinkPresentWithText("Next");
        assertLinkPresentWithText("Last");
    }

    private void checkBloodPressure() {
        // Blood Pressure
        clickLinkWithText("Blood Pressure");
        assertTextPresent("Enter My Blood Pressure");
        assertTextPresent("Use this page to enter values from home");
        Calendar calendar = Calendar.getInstance();
        assertSelectOptionPresent("day", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        assertSelectOptionPresent("year", String.valueOf(calendar.get(Calendar.YEAR)));
        assertSelectOptionPresent("hour", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
        assertSelectOptionPresent("minute", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0"));

        assertLinkPresentWithText("Systolic");
        assertFormElementPresent("patientResultValue1");
        assertLinkPresentWithText("Diastolic");
        assertFormElementPresent("patientResultValue2");
        assertButtonPresentWithText("Add");

        // add
        setTextField("patientResultValue1", "120");
        setTextField("patientResultValue2", "80");
        submit();

        assertTextPresent("By pressing the Submit All button ");
        assertButtonPresentWithText("Submit All");
        assertButtonPresentWithText("Delete");

        // Submit All
        clickButtonWithText("Submit All");
        assertButtonNotPresentWithText("Delete");

        setTextField("patientResultValue1", "130");
        setTextField("patientResultValue2", "90");
        submit();

        assertButtonPresentWithText("Delete");
        assertButtonPresentWithText("Submit All");

        // Delete
        clickButtonWithText("Delete");
        assertButtonNotPresentWithText("Submit All");
        assertTextFieldEquals("patientResultValue1", "");
        assertTextFieldEquals("patientResultValue2", "");

        // Systolic  link
        clickLinkWithText("Systolic");
        gotoWindowByTitle("Blood pressure (Renal PatientView)");
        assertTitleEquals("Blood pressure (Renal PatientView)");

    }

    private void checkGlucoseValues() {
        // Glucose Values
        clickLinkWithText("Glucose Values");
        assertTextPresent("Enter My Glucose");
        assertTextPresent("these results will not be automatically sent to anyone");
        Calendar calendar = Calendar.getInstance();
        assertSelectOptionPresent("day", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        assertSelectOptionPresent("year", String.valueOf(calendar.get(Calendar.YEAR)));
        assertSelectOptionPresent("hour", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
        assertSelectOptionPresent("minute", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0"));

        assertFormElementPresent("patientResultValue1");
        assertButtonPresentWithText("Add");

        // add
        setTextField("patientResultValue1", "200");
        submit();

        assertTextPresent("you will add these glucose values to your record");
        assertButtonPresentWithText("Submit All");
        assertButtonPresentWithText("Delete");

        // Submit All
        clickButtonWithText("Submit All");
        assertButtonNotPresentWithText("Delete");

        setTextField("patientResultValue1", "130");
        submit();

        assertButtonPresentWithText("Delete");
        assertButtonPresentWithText("Submit All");

        // Delete
        clickButtonWithText("Delete");
        assertButtonNotPresentWithText("Submit All");
        assertTextFieldEquals("patientResultValue1", "");

    }

    private void checkWeight() {
        // Weight
        clickLinkWithText("Weight");
        assertTextPresent("Enter My Weight");
        assertTextPresent("If you need advice");
        Calendar calendar = Calendar.getInstance();
        assertSelectOptionPresent("day", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        assertSelectOptionPresent("year", String.valueOf(calendar.get(Calendar.YEAR)));
        assertSelectOptionPresent("hour", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
        assertSelectOptionPresent("minute", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0"));

        assertFormElementPresent("patientResultValue1");
        assertButtonPresentWithText("Add");

        // add
        setTextField("patientResultValue1", "180");
        submit();

        assertTextPresent("you will add these weight values to your record");
        assertButtonPresentWithText("Submit All");
        assertButtonPresentWithText("Delete");

        // Submit All
        clickButtonWithText("Submit All");
        assertButtonNotPresentWithText("Delete");

        setTextField("patientResultValue1", "170");
        submit();

        assertButtonPresentWithText("Delete");
        assertButtonPresentWithText("Submit All");

        // Delete
        clickButtonWithText("Delete");
        assertButtonNotPresentWithText("Submit All");
        assertTextFieldEquals("patientResultValue1", "");

    }

    private void checkComment() {
        // Comment
        clickLinkWithText("Comment");
        assertTextPresent("Enter My Comments");
        assertTextPresent("Currently comments are limited to 100 characters.");
        Calendar calendar = Calendar.getInstance();
        assertSelectOptionPresent("day", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        assertSelectOptionPresent("year", String.valueOf(calendar.get(Calendar.YEAR)));
        assertSelectOptionPresent("hour", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
        assertSelectOptionPresent("minute", StringUtils.leftPad(
                String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0"));

        assertFormElementPresent("patientResultValue1");
        assertButtonPresentWithText("Add");

        // add
        setTextField("patientResultValue1", "this is test comment!");
        submit();

        assertTextPresent("this is test comment!");
        assertTextPresent("you will add these comments to your record");
        assertButtonPresentWithText("Submit All");
        assertButtonPresentWithText("Delete");

        // Submit All
        clickButtonWithText("Submit All");
        assertButtonNotPresentWithText("Delete");
        assertTextNotPresent("this is test comment!");

        setTextField("patientResultValue1", "test comment");
        submit();

        assertButtonPresentWithText("Delete");
        assertButtonPresentWithText("Submit All");

        // Delete
        clickButtonWithText("Delete");
        assertButtonNotPresentWithText("Submit All");
        assertTextFieldEquals("patientResultValue1", "");

    }

    private void login() {
        beginAt("/");                            // start

        assertFormElementPresent("j_username");
        assertFormElementPresent("j_password");
        setTextField("j_username", username);
        setTextField("j_password", password);
        submit();
        assertLinkPresentWithText("Logout");    // we should now be logged in
    }
}
