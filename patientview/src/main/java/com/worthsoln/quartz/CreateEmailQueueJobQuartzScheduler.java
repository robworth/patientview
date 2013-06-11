package com.worthsoln.quartz;

import com.worthsoln.job.CreateEmailQueueJob;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Quartz CreateEmailQueueJobQuartzScheduler Job
 */
public class CreateEmailQueueJobQuartzScheduler extends BaseQuartzScheduler {

    @Autowired
    private CreateEmailQueueJob createEmailQueueJob;

    @Override
    protected void setJob() {
       this.batchJob = createEmailQueueJob;
    }
}
