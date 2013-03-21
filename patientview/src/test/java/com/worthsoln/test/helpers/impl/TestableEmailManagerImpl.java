package com.worthsoln.test.helpers.impl;

import com.worthsoln.service.impl.EmailManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;

public class TestableEmailManagerImpl extends EmailManagerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestableEmailManagerImpl.class);

    @Override
    public void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress,
                          String subject, String emailText) {
        /**
         * Don't send emails in test environment, but log them.
         */
       LOGGER.info("--- Skipping sending an email as this is test environment ---");
       LOGGER.info("From: {}\nTo: {}\nSubject: {}\nContent:\n{}",
               new Object[] {fromAddress, toAddress, subject, emailText});
       LOGGER.info("--- End of Email ---");
    }
}
