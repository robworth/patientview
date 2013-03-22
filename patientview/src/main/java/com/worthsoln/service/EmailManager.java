package com.worthsoln.service;

import com.worthsoln.patientview.model.Message;

import javax.servlet.ServletContext;

public interface EmailManager {

    void sendUserMessage(Message message);

    void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress, String subject,
                   String emailText);

    void sendEmail(String from, String[] to, String[] bcc, String subject, String body);
}
