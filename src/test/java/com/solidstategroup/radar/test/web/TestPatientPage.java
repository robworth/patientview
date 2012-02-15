package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Simple test using the WicketTester
 */
public class TestPatientPage extends BasePageTest {

    @Test
    public void renderPatientPageExistingPatient() {
        // Construct demographics to get page parameters
        Demographics demographics = new Demographics();
        demographics.setId(238L);

        // Start and renderTemplate the test page with page parameters
        tester.startPage(PatientPage.class, PatientPage.getParameters(demographics));
        clickTabsAndAssert(true);
    }

    @Test
    public void renderPatientPageNewPatient() {
        //start and renderTemplate the test page
        tester.startPage(PatientPage.class);

        clickTabsAndAssert(false);
    }

    private void clickTabsAndAssert(boolean hasPatient) {
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

        // If we've got patient then make sure the fields have values set
        if (hasPatient) {
            TextField surnameField =
                    (TextField) tester.getLastRenderedPage().get("demographicsPanel").get("form").get("surname");
            assertEquals("Wrong value on surname", "Mouse", surnameField.getValue());
        }
        if(hasPatient) {
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
    }

    private Component getLink(String id) {
        return tester.getLastRenderedPage().get("linksContainer").get(id);
    }
}
