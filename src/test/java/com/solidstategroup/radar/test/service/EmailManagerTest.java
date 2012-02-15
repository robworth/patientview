package com.solidstategroup.radar.test.service;

import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.EmailManager;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.test.DatabaseBackedTest;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public class EmailManagerTest extends DatabaseBackedTest {

    @Autowired
    private EmailManager emailManager;

    @Test
    public void testSendEmail() throws Exception {
        emailManager.sendEmail("abul@solidstategroup.com", new String[]{"abul@solidstategroup.com"},
                new String[]{"test bcc"}, "Test subject","Test Body");
    }

    @Test
    public void testRender() throws Exception {
      String text = emailManager.render(new HashMap<String, Object>(), "patient-registration.vm");
      assertTrue(text != null && !text.isEmpty());
    }
}
