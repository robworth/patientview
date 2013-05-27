package com.worthsoln.batch;

import com.worthsoln.patientview.EmailUtils;
import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                subject.append("You have been sent a message from ") .append(emailQueue.getMessage().getSender().getName()).append(" on Renal PatientView");

                body.append("Hello ").append(emailQueue.getRecipient().getName()).append("\n\n");
                body.append("You have received a message from ").append(emailQueue.getMessage().getSender().getName()).append(" on Renal PatientView.\n\n");
                body.append("Click the link below or logon to PatientView and go to the Messages tab to see the message.\n\n");
                body.append(url);

                if (GroupEnum.ALL_ADMINS.equals(emailQueue.getMessage().getGroupEnum())
                        || GroupEnum.ALL_STAFF.equals(emailQueue.getMessage().getGroupEnum())) {
                    body.append("control");
                } else {
                    body.append("patient");
                }
                body.append("/conversation.do?id=").append(emailQueue.getMessage().getConversation().getId()) .append("#message-" + emailQueue.getMessage().getId());
                body.append("\n\nPlease don't reply to this message. No one will see it.\n\n");

                try {
                    EmailUtils.sendEmail(noReplyEmail, new String[]{emailQueue.getRecipient().getEmail()}, subject.toString(), body.toString());

                    emailQueue.setFinished(new Date());
                    emailQueue.setStatus(SendEmailEnum.SUCCESSED);
                    emailQueueManager.save(emailQueue);

                } catch (Exception e) {
                    emailQueue.setFinished(new Date());
                    emailQueue.setStatus(SendEmailEnum.FAILED);
                    emailQueueManager.save(emailQueue);

                    emailQueue.getJob().addReport(
                          "username=" + emailQueue.getRecipient().getUsername()
                        + ",messageId=" + emailQueue.getMessage().getId()
                        + " : " + e.getMessage());
                    System.out.println(e.getLocalizedMessage());

                    emailQueue.getJob().convertReports();
                    emailQueue.getJob().addErrorCount();
                    emailQueue.getJob().setStatus(SendEmailEnum.FAILED);
                    jobManager.save(emailQueue.getJob());
                }
            }
        }
    }
}
