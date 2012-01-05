package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.ForgottenPasswordPage;
import org.junit.Test;

public class TestForgottenPasswordPage extends BasePageTest {

    @Test
    public void testForgottenPasswordPage() throws Exception {
        // Render forgotten password page
        tester.startPage(ForgottenPasswordPage.class);

        // Assert rendered
        tester.assertRenderedPage(ForgottenPasswordPage.class);
    }
}
