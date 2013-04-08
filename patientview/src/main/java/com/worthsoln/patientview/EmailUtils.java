package com.worthsoln.patientview;

import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.ServletContext;

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

    public static void sendEmail(ServletContext context, String fromAddress, String toAddress, String subject,
                                 String emailText) {
        sendEmail(context, fromAddress, toAddress, "", subject, emailText);
    }

    public static void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress,
                                 String subject, String emailText) {
        LegacySpringUtils.getEmailManager().sendEmail(context, fromAddress, toAddress, ccAddress, subject, emailText);
    }
}
