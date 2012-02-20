package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.regisration.ChangeRegistrationDetails;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestChangeRegistrationDetailsPage extends BasePageTest {

    @Test
    public void homepageRendersSuccessfully() {
        //start and renderTemplate the test page
        tester.startPage(ChangeRegistrationDetails.class);

        //assert rendered page class
        tester.assertRenderedPage(ChangeRegistrationDetails.class);
    }
}
