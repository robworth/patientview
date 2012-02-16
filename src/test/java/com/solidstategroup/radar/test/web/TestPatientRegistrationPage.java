package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.regisration.PatientRegistrationPage;
import org.junit.Test;

public class TestPatientRegistrationPage extends BasePageTest {

    @Test
    public void testPatientRegistrationPage() throws Exception {
        // Start and renderTemplate registration page
        tester.startPage(PatientRegistrationPage.class);

        // Assert rendered
        tester.assertRenderedPage(PatientRegistrationPage.class);
    }
}
