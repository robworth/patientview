package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.patient.GenericPatientPage;
import org.junit.Test;

public class TestGenericPatientPage extends BasePageTest {

    @Test
    public void testAddPatientsPage() throws Exception {
        // Render forgotten password page
        tester.startPage(GenericPatientPage.class);

        // Assert rendered
        tester.assertRenderedPage(GenericPatientPage.class);
    }
}
