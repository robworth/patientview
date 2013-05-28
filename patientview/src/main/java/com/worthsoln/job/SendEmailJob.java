package com.worthsoln.job;

import com.worthsoln.batch.SendEmailReader;
import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Send the email to special group members after the unitadmin send the bulk message
 */
@Component
public class SendEmailJob extends BatchJob {

    @Resource(name = "sendEmailBatchJob")
    private Job batchJob;

    @Resource(name = "sendEmailReader")
    private SendEmailReader reader;

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    @Override
    protected void onJobSkipInWriter(Object holder, Throwable problem) {

        if (holder instanceof EmailQueue) {
            EmailQueue emailQueue = (EmailQueue) holder;

            try {
                emailQueue.setStatus(SendEmailEnum.FAILED);
                emailQueue.setFinished(new Date());
                emailQueue.addReport(problem.getMessage());
                emailQueue.convertReports();
                emailQueueManager.save((EmailQueue) holder);

            } catch (Exception e) {
                LOGGER.debug(e.getMessage());
            }
        }
    }

    @Override
    protected void afterBatchJob(JobExecution result) {}

    @Override
    protected void onRunError(Exception e) {
        LOGGER.debug(e.getMessage());
    }

    @Override
    protected void prepare() {
        reader.refresh();
    }

}
