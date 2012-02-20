package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.admin.AdminPatientsAllPage;
import com.solidstategroup.radar.web.pages.login.PatientsLoginPage;
import org.junit.Test;

public class TestAdminPatientsAllPage extends BasePageTest {

    @Test
    public void testAdminPatientsAllPage() throws Exception {
        // Render forgotten password page
        tester.startPage(AdminPatientsAllPage.class);

        // Assert rendered
        tester.assertRenderedPage(AdminPatientsAllPage.class);
    }
}
