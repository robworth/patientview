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

package org.patientview.service.impl;

import org.patientview.patientview.messaging.Messaging;
import org.patientview.patientview.model.SpecialtyUserRole;
import org.patientview.service.EmailManager;
import org.patientview.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;

@Service(value = "emailManager")
public class EmailManagerImpl implements EmailManager {

    @Inject
    private JavaMailSender javaMailSender;

    @Inject
    private UserManager userManager;

    @Value("${noreply.email}")
    private String noReplyEmail;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailManagerImpl.class);

    @Override
    public void sendUserMessage(ServletContext context, org.patientview.patientview.model.Message message) {
        String subject = "You have been sent a message from " + message.getSender().getName()
                + " on Renal PatientView";

        // need to work out if the recipient of this message is a patient or something higher
        // if staff or admin then they go to /control if patient they go to /patient
        boolean isAdminOrStaff = false;

        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(message.getRecipient());

        for (SpecialtyUserRole specialtyUserRole : specialtyUserRoles) {
            if (specialtyUserRole.getRole().equals("unitadmin")
                    || specialtyUserRole.getRole().equals("unitstaff")
                    || specialtyUserRole.getRole().equals("superadmin")) {
                isAdminOrStaff = true;
                break;
            }
        }

        String messageUrl = context.getInitParameter("config.site.url") + (isAdminOrStaff ? "control" : "patient")
                + "/conversation.do?" + Messaging.CONVERSATION_ID_PARAM + "=" + message.getConversation().getId()
                + "#message-" + message.getId();

        String body = "Hello " + message.getRecipient().getName() + "\n\n";
        body += "You have received a message from " + message.getSender().getName() + " on Renal PatientView.\n\n";
        body += "Click the link below or logon to PatientView and go to the Messages tab to see the message.\n\n";
        body += messageUrl;
        body += "\n\nPlease don't reply to this message. No one will see it.\n\n";

        sendEmail(noReplyEmail, new String[]{message.getRecipient().getEmail()},
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

    public void sendEmail(String from, String[] to, String[] bcc, String subject, String body) {

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

    @Override
    public void sendEmail(String from, String[] to, String subject, String body) throws Exception {

        if (!StringUtils.hasLength(from)) {
            throw new IllegalArgumentException("Cannot send mail missing 'from'");
        }

        if (!StringUtils.hasLength(subject)) {
            throw new IllegalArgumentException("Cannot send mail missing 'subject'");
        }

        if (!StringUtils.hasLength(body)) {
            throw new IllegalArgumentException("Cannot send mail missing 'body'");
        }

        if ((to == null || to.length == 0)) {
            throw new IllegalArgumentException("Cannot send mail missing recipients");
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;

        messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        messageHelper.setText(body, false);

        javaMailSender.send(messageHelper.getMimeMessage());
    }


    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
