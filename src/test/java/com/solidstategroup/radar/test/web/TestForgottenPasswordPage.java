package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.ForgottenPasswordPage;
import com.solidstategroup.radar.web.pages.PatientForgottenPasswordPage;
import org.junit.Test;

public class TestForgottenPasswordPage extends BasePageTest {

    @Test
    public void testPatientForgottenPasswordPage() throws Exception {
        // Render forgotten password page
        tester.startPage(PatientForgottenPasswordPage.class);

        // Assert rendered
        tester.assertRenderedPage(PatientForgottenPasswordPage.class);
    }
}
