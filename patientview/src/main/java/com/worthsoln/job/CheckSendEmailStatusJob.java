package com.worthsoln.job;

import com.worthsoln.batch.CheckSendEmailStatusReader;
import com.worthsoln.batch.SendEmailReader;
import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * According the status of every entry in EmailQueue,to check whether the job succeeded
 */
@Component
public class CheckSendEmailStatusJob extends BatchJob {

    @Resource(name = "checkSendEmailStatusBatchJob")
    private Job batchJob;

    @Resource(name = "checkSendEmailStatusReader")
    private CheckSendEmailStatusReader reader;

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
        LOGGER.debug(problem.getMessage());

    }

    @Override
    protected void afterBatchJob(JobExecution result) {
        if (result.getStatus() != BatchStatus.COMPLETED) {
            if (result.getFailureExceptions() != null && !result.getFailureExceptions().isEmpty()) {
                LOGGER.debug(result.getFailureExceptions().get(0).getMessage());
            }
        }
    }

    @Override
    protected void onRunError(Exception e) {
        LOGGER.debug(e.getMessage());
    }

    @Override
    protected void prepare() {
        reader.refresh();
    }

}
