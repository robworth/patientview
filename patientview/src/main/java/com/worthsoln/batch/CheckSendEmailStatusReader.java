package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * According the status of every entry in EmailQueue,to re-set the jobs' status
 */
@Component
public class CheckSendEmailStatusReader extends ListItemReader<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    public void refresh() {

        List<Object> list = new ArrayList<Object>();
        List<Job> jobs = jobManager.getJobList(SendEmailEnum.SENT);
        List<EmailQueue> emailQueues;
        boolean isFailed = false;
        boolean hasSucceeded = false;
        boolean hasSending = false;

        for (Job job : jobs) {
            isFailed = false;
            hasSucceeded = false;
            hasSending = false;

            emailQueues = emailQueueManager.getEmailQueueList(job.getId());

            for (EmailQueue queue : emailQueues) {
                if (SendEmailEnum.SENDING.equals(queue.getStatus())) {
                    hasSending = true;
                    break;
                }
                if (SendEmailEnum.SUCCEEDED.equals(queue.getStatus())) {
                    hasSucceeded = true;
                }

                if (SendEmailEnum.FAILED.equals(queue.getStatus())) {
                    isFailed = true;
                    break;
                }
            }

            if (hasSending) {
                continue;
            }

            if (emailQueues != null && !emailQueues.isEmpty()) {
                if (hasSucceeded == true && isFailed == true) {
                    job.setStatus(SendEmailEnum.FAILED);
                    list.add(job);
                } else if (hasSucceeded == true && isFailed == false) {
                    job.setStatus(SendEmailEnum.SUCCEEDED);
                    list.add(job);
                } else if (hasSucceeded == false && isFailed == true) {
                    job.setStatus(SendEmailEnum.FAILED);
                    list.add(job);
                } else {}
            }
        }

        setList(list);
    }
}
