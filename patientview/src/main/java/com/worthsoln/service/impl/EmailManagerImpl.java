package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.service.EmailManager;
import com.worthsoln.service.SecurityUserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

@Service(value = "emailManager")
public class EmailManagerImpl implements EmailManager {

    @Inject
    private JavaMailSender javaMailSender;

    @Inject
    private SecurityUserManager securityUserManager;

    @Autowired
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailManagerImpl.class);

    @Override
    public void sendUserMessage(com.worthsoln.patientview.model.Message message) {
        String subject = "You have been sent a message from " + message.getSender().getName()
                + " on Renal Patient View";

        boolean isPatient = message.getRecipient().getRole().equals("patient");
        Specialty specialty = securityUserManager.getLoggedInSpecialty();
        String context = specialty != null ? "/" + specialty.getContext() : "";

        String messageUrl = context + "/" + (isPatient ? "patient" : "control")
                + "conversation.do?id=" + message.getConversation().getId() + "#message-" + message.getId();

        String body = "<p>Dear " + message.getRecipient().getName() + "</p>";
        body += "<p>You have received a message from " + message.getSender().getName() + " on Renal Patient View.</p>";
        body += "<p>Click <a href=\"" + messageUrl + "\">here to response.</p>";

        sendEmail(env.getRequiredProperty("noreply.email"), new String[]{message.getRecipient().getEmail()},
                null, subject, body);
    }

    @Override
    public void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress,
                          String subject, String emailText) {
        try {
            // convert params to signature to sendEmail
            String[] toAddresses = null;
            String[] bccAddresses = null;
            if (StringUtils.hasLength(toAddress)) {
                toAddresses = new String[]{toAddress};
            }
            if (StringUtils.hasLength(ccAddress)) {
                bccAddresses = new String[]{ccAddress};
            }

            sendEmail(fromAddress, toAddresses, bccAddresses, subject, emailText);
        } catch (Exception e) {
            LOGGER.error("EmailManagerImpl: Failed to send email - " + e.getMessage() + " swallowing exception!");
        }
    }

    private void sendEmail(String from, String[] to, String[] bcc, String subject, String body) {

        if (!StringUtils.hasLength(from)) {
            throw new IllegalArgumentException("Cannot send mail missing 'from'");
        }

        if (!StringUtils.hasLength(subject)) {
            throw new IllegalArgumentException("Cannot send mail missing 'subject'");
        }

        if (!StringUtils.hasLength(body)) {
            throw new IllegalArgumentException("Cannot send mail missing 'body'");
        }

        if ((to == null || to.length == 0) && (bcc == null || bcc.length == 0)) {
            throw new IllegalArgumentException("Cannot send mail missing recipients");
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;

        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(to);
            if (bcc != null && bcc.length > 0) {
                Address[] bccAddresses = new Address[bcc.length];
                for (int i = 0; i < bcc.length; i++) {
                    bccAddresses[i] = new InternetAddress(bcc[i]);
                }
                message.addRecipients(Message.RecipientType.BCC, bccAddresses);
            }
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, false); // Note: the second param indicates to send plaintext

            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (Exception e) {
            LOGGER.error("Could send email: {}", e);
        }
    }
}
