/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.test.helpers.impl;

import org.patientview.service.impl.EmailManagerImpl;
import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
@Ignore
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

    @Override
    public void sendEmail(String from, String[] to, String subject, String body) throws Exception{
        /**
         * Don't send emails in test environment, but log them.
         */
        LOGGER.info("--- Skipping sending bulk email as this is test environment ---");
        LOGGER.info("From: {}\nTo: {}\nSubject: {}\nContent:\n{}",
                new Object[] {from, StringUtils.join(to, ", "), subject, body});
        LOGGER.info("--- End of bulk Email ---");
    }
}
