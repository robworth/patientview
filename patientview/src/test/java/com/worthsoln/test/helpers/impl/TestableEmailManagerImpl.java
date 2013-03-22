package com.worthsoln.test.helpers.impl;

import com.worthsoln.service.impl.EmailManagerImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;

public class TestableEmailManagerImpl extends EmailManagerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestableEmailManagerImpl.class);

    @Override
    public void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress,
                          String subject, String emailText) {
        sendEmail(fromAddress, new String[] {toAddress}, new String[] {ccAddress}, subject, emailText);
    }

    @Override
    public void sendEmail(String from, String[] to, String[] bcc, String subject, String body) {
        /**
         * Don't send emails in test environment, but log them.
         */
        LOGGER.info("--- Skipping sending an email as this is test environment ---");
        LOGGER.info("From: {}\nTo: {}\nCc: {}\nSubject: {}\nContent:\n{}",
                new Object[] {from, StringUtils.join(to, ", "), StringUtils.join(bcc, ", "), subject, body});
        LOGGER.info("--- End of Email ---");
    }
}
