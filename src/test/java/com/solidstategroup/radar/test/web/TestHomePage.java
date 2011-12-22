package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.pages.newPatient.NewPatientPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new RadarApplication());
    }

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(NewPatientPage.class);

        //assert rendered page class
        tester.assertRenderedPage(NewPatientPage.class);
    }
}
