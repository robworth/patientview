package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.PatientPage;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestPatientPage extends BasePageTest {

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(PatientPage.class);

        //assert rendered page class
        tester.assertRenderedPage(PatientPage.class);
    }
}
