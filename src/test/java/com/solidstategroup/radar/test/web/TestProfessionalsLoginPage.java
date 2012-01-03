package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.ProfessionalsLoginPage;
import org.junit.Test;

public class TestProfessionalsLoginPage extends BasePageTest {

    @Test
    public void renderPage() {
        // Render login page
        tester.startPage(ProfessionalsLoginPage.class);

        // Assert page rendered
        tester.assertRenderedPage(ProfessionalsLoginPage.class);
    }
}
