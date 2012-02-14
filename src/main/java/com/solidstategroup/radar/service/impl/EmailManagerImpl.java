package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.EmailManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;

import javax.mail.internet.MimeMessage;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;

public class EmailManagerImpl implements EmailManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailManagerImpl.class);

    private JavaMailSender javaMailSender;
    private VelocityEngine velocityEngine;

    public EmailManagerImpl() {
        try {
            velocityEngine = new VelocityEngine();
            // This sets Velocity to use our own logging implementation so we can use SLF4J
            velocityEngine.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, LOGGER);
            // Init
            velocityEngine.init();
        } catch (Exception e) {
            LOGGER.error("Could not initialise velocity engine!", e);
        }
    }

    public boolean sendTestEmail() {
        try {
            String bodyTemplate = "Hello $UserName";

            Reader reader = new StringReader(bodyTemplate);

            // build our context map
            // TODO: this will need to take a Map as a method param
            // TODO: loop through and add each entry into the VelocityContextMap
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("UserName", "David");

            // Try the render, log any problems
            final Writer writer = new StringWriter();
            try {
                velocityEngine.evaluate(velocityContext, writer, "Email", reader);
            } catch (IOException e) {
                LOGGER.error("Could not render template {}", "email", e);
            }

            String body = writer.toString();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = null;

            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo("david@solidstategroup.com");
            messageHelper.setFrom("david@solidstategroup.com", "David");
            messageHelper.setSubject("Test");
            messageHelper.setText(body, true);
            messageHelper.setReplyTo("david@solidstategroup.com");

            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public void sendPatientRegistrationEmail(PatientUser patientUser, String password) {
        // Todo: Load in patient-registration.vm and then run through Velocity render
    }

    public void sendAdminPatientRegistrationEmail(PatientUser patientUser) {
        // Todo: Same but for admin-patient-registration.vm
        // Todo: Sends to fiona.braddon@nhs.net but put this in radar.properties
    }

    public void sendForgottenPassword(PatientUser patientUser, String password) {
        // Todo: Implement
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
