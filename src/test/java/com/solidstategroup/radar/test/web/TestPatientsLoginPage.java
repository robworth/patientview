package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.PatientsLoginPage;
import org.junit.Test;

public class TestPatientsLoginPage extends BasePageTest {

    @Test
    public void testPatientsLoginPage() throws Exception {
        // Render forgotten password page
        tester.startPage(PatientsLoginPage.class);

        // Assert rendered
        tester.assertRenderedPage(PatientsLoginPage.class);
    }
}
