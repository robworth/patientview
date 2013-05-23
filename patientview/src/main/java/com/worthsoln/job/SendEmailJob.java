package com.worthsoln.job;

import org.springframework.batch.core.Job;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Send the email to special group members after the unitadmin send the bulk message
 */
public class SendEmailJob extends BatchJob {

    @Resource(name = "sendEmailBatchJob")
    private Job batchJob;

    @Override
    protected void setJob() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }
}
