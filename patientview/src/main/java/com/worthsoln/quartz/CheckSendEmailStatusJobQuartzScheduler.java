package com.worthsoln.quartz;

import com.worthsoln.job.CheckSendEmailStatusJob;
import com.worthsoln.job.SendEmailJob;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Quartz CheckSendEmailStatusJobQuartzScheduler Job
 */
public class CheckSendEmailStatusJobQuartzScheduler extends BaseQuartzScheduler {

    @Autowired
    private CheckSendEmailStatusJob checkSendEmailStatusJob;

   @Override
    protected void setJob() {
        this.batchJob = checkSendEmailStatusJob;
    }
}
