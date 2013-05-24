package com.worthsoln.quartz;

import com.worthsoln.job.SendEmailJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Quartz SendEmailJobQuartzScheduler Job
 */
public class SendEmailJobQuartzScheduler extends BaseQuartzScheduler {

    @Autowired
    private SendEmailJob sendEmailJob;

   @Override
    protected void setJob() {
        this.batchJob = sendEmailJob;
    }
}
