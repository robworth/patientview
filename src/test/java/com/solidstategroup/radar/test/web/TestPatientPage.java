package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestPatientPage extends BasePageTest {

    @Test
    public void renderPatientPageNewPatient() {
        // Construct demographics to get page parameters
        Demographics demographics = new Demographics();
        demographics.setId(238L);

        // Start and render the test page with page parameters
        tester.startPage(PatientPage.class, PatientPage.getParameters(demographics));
        clickTabsAndAssert();
    }

    @Test
    public void renderPatientPageExistingPatient() {
        //start and render the test page
        tester.startPage(PatientPage.class);

        clickTabsAndAssert();
    }

    private void clickTabsAndAssert() {
        //assert rendered page class
        tester.assertRenderedPage(PatientPage.class);

        // Try Ajax refresh
        tester.assertVisible("demographicsPanel");
        tester.assertInvisible("diagnosisPanel");
        tester.assertInvisible("firstVisitPanel");
        tester.assertInvisible("followUpPanel");
        tester.assertInvisible("pathologyPanel");
        tester.assertInvisible("relapsePanel");
        tester.assertInvisible("hospitalisationPanel");

        tester.clickLink(getLink("diagnosisLink").getPageRelativePath());
        tester.assertVisible("diagnosisPanel");
        tester.assertInvisible("demographicsPanel");
        tester.assertInvisible("pathologyPanel");
        tester.assertInvisible("firstVisitPanel");

        tester.clickLink(getLink("firstVisitLink").getPageRelativePath());
        tester.assertVisible("firstVisitPanel");
        tester.assertInvisible("hospitalisationPanel");
        tester.assertInvisible("diagnosisPanel");

        tester.clickLink(getLink("followUpLink").getPageRelativePath());
        tester.assertVisible("followUpPanel");
        tester.assertInvisible("firstVisitPanel");
        tester.assertInvisible("diagnosisPanel");

        tester.clickLink(getLink("pathologyLink").getPageRelativePath());
        tester.assertVisible("pathologyPanel");
        tester.assertInvisible("demographicsPanel");
        tester.assertInvisible("diagnosisPanel");

        tester.clickLink(getLink("hospitalisationLink").getPageRelativePath());
        tester.assertVisible("hospitalisationPanel");
        tester.assertInvisible("demographicsPanel");
        tester.assertInvisible("diagnosisPanel");

        tester.clickLink(getLink("relapseLink").getPageRelativePath());
        tester.assertVisible("relapsePanel");
        tester.assertInvisible("hospitalisationPanel");
        tester.assertInvisible("diagnosisPanel");
    }

    private Component getLink(String id) {
        return tester.getLastRenderedPage().get("linksContainer").get(id);
    }
}
