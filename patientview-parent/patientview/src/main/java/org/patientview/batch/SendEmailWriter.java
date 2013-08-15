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
package org.patientview.batch;

import org.patientview.patientview.EmailUtils;
import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

/**
 * SendEmailJob writer
 */
@Component
@Lazy
public class SendEmailWriter implements ItemWriter<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    @Value("${config.site.url}")
    private String url;

    @Value("${noreply.email}")
    private String noReplyEmail;

    @Override
    public void write(List<? extends Object> items) throws Exception {

        StringBuilder subject;
        StringBuilder body;
        EmailQueue emailQueue;

        if (items != null && !items.isEmpty()) {

            for (Object obj : items) {
                emailQueue = (EmailQueue) obj;
                subject = new StringBuilder();
                body = new StringBuilder();

                subject.append("You have been sent a message from ")
                        .append(emailQueue.getMessage().getSender().getName()).append(" on Renal PatientView");

                body.append("Hello ").append(emailQueue.getRecipient().getName()).append("\n\n");
                body.append("You have received a message from ")
                        .append(emailQueue.getMessage().getSender().getName()).append(" on Renal PatientView.\n\n");
                body.append("Click the link below or logon to PatientView and go to the "
                        + "Messages tab to see the message.\n\n");
                body.append(url);

                if (GroupEnum.ALL_ADMINS.equals(emailQueue.getMessage().getGroupEnum())
                        || GroupEnum.ALL_STAFF.equals(emailQueue.getMessage().getGroupEnum())) {
                    body.append("control");
                } else {
                    body.append("patient");
                }
                body.append("/conversation.do?id=").append(emailQueue.getMessage().getConversation().getId())
                        .append("#message-" + emailQueue.getMessage().getId());
                body.append("\n\nPlease don't reply to this message. No one will see it.\n\n");

                try {
                    EmailUtils.sendEmail(noReplyEmail, new String[]{emailQueue.getRecipient().getEmail()},
                            subject.toString(), body.toString());

                    emailQueue.setFinished(new Date());
                    emailQueue.setStatus(SendEmailEnum.SUCCEEDED);
                    emailQueueManager.save(emailQueue);

                } catch (Exception e) {
                    emailQueue.setFinished(new Date());
                    emailQueue.setStatus(SendEmailEnum.FAILED);
                    emailQueue.addReport(e.getMessage());
                    emailQueue.convertReports();
                    emailQueueManager.save(emailQueue);

                }
            }
        }
    }
}
