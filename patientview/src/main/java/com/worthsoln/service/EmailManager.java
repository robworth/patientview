package com.worthsoln.service;

import javax.servlet.ServletContext;

public interface EmailManager {

    void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress, String subject,
                   String emailText);

}
