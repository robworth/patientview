package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.patient.AddPatientPage;
import org.junit.Test;

public class TestAddPatientsPage extends BasePageTest {

    @Test
    public void testAddPatientsPage() throws Exception {
        // Render forgotten password page
        tester.startPage(AddPatientPage.class);

        // Assert rendered
        tester.assertRenderedPage(AddPatientPage.class);
    }
}
