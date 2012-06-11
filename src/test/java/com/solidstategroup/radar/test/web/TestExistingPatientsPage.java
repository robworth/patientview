package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.patient.ExistingPatientsListingPage;
import org.junit.Test;

public class TestExistingPatientsPage extends BasePageTest {

    @Test
    public void renderPage() {
        // Render existing patients page
        tester.startPage(ExistingPatientsListingPage.class);
    }

}
