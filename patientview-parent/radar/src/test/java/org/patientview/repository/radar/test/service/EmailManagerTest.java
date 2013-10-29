package org.patientview.repository.radar.test.service;

import org.patientview.repository.radar.service.EmailManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.repository.radar.test.TestPvDbSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context-service.xml"})
public class EmailManagerTest extends TestPvDbSchema {

    @Autowired
    private EmailManager emailManager;

    @Test
    public void testSendEmail() throws Exception {
        emailManager.sendEmail("test@test.com", new String[]{"test@test.com"},
                new String[]{"test@test.com"}, "Test subject","Test Body");
    }

    @Test
    public void testRender() throws Exception {
      String text = emailManager.renderTemplate(new HashMap<String, Object>(), "patient-registration.vm");
      assertTrue(text != null && !text.isEmpty());
    }

}
