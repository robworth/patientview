package com.worthsoln.patientview;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.util.Properties;

public class EmailUtils {

    public static void sendEmail(ServletContext context, String emailText) {
        sendEmail(context, "Intranet Feedback", emailText);
    }

    public static void sendEmail(ServletContext context, String subject, String emailText) {
        String to = context.getInitParameter("admin.email.to");
        sendEmail(context, to, subject, emailText);
    }

    public static void sendEmail(ServletContext context, String toAddress, String subject, String emailText) {
        String from = context.getInitParameter("noreply.email");
        sendEmail(context, from, toAddress, subject, emailText);
    }

    public static void sendEmail(ServletContext context, String fromAddress, String toAddress, String subject, String emailText) {
        sendEmail(context, fromAddress, toAddress, "", subject, emailText);
    }

    public static void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress, String subject, String emailText) {
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
            Transport.send(email);
        } catch (MessagingException e) {
            System.err.println("EmailUtils: Failed to send email - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
