package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.PatientPage;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestPatientPage extends BasePageTest {

    @Test
    public void renderPatientPage() {
        //start and render the test page
        tester.startPage(PatientPage.class);

        //assert rendered page class
        tester.assertRenderedPage(PatientPage.class);

        // Try Ajax refresh
        tester.assertVisible("demographicsPanel");
        tester.assertInvisible("diagnosisPanel");
        tester.assertInvisible("pathologyPanel");

        tester.clickLink("diagnosisLink");
        tester.assertVisible("diagnosisPanel");
        tester.assertInvisible("demographicsPanel");
        tester.assertInvisible("pathologyPanel");

        tester.clickLink("pathologyLink");
        tester.assertVisible("pathologyPanel");
        tester.assertInvisible("demographicsPanel");
        tester.assertInvisible("diagnosisPanel");

        tester.clickLink("hospitalisationLink");
        tester.assertVisible("hospitalisationPanel");
        tester.assertInvisible("demographicsPanel");
        tester.assertInvisible("diagnosisPanel");

        tester.clickLink("relapseLink");
        tester.assertVisible("relapsePanel");
        tester.assertInvisible("hospitalisationPanel");
        tester.assertInvisible("diagnosisPanel");
    }
}
