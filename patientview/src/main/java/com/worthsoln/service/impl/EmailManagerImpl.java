package com.worthsoln.service.impl;

import com.worthsoln.service.EmailManager;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.util.Properties;

@Service(value = "emailManager")
public class EmailManagerImpl implements EmailManager {

    @Override
    public void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress,
                          String subject, String emailText) {
        try {
            String host = context.getInitParameter("smtp.host");
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            Session session = Session.getInstance(props, null);
            /*
                todo look at providing auth to use send grid
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("xxxx@yyy.com", "pass");
                }
            });
            */

            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(fromAddress));
            email.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            if (null != ccAddress && !"".equals(ccAddress)) {
                email.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
            }
            email.setSubject(subject);
            email.setText(emailText);
            //            Transport.send(email);
        } catch (MessagingException e) {
            System.err.println("EmailUtils: Failed to send email - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
