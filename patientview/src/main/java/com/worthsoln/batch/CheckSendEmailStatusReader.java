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

        for (Job job : jobs) {
            emailQueues = emailQueueManager.getEmailQueueList(job.getId());

            if (emailQueues != null && !emailQueues.isEmpty()) {
                job.setStatus(SendEmailEnum.FAILED);
            } else {
                job.setStatus(SendEmailEnum.SUCCEEDED);
            }
            list.add(job);
        }
        setList(list);
    }
}
