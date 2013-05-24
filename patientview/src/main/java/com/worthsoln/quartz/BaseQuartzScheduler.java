package com.worthsoln.quartz;

import com.worthsoln.job.BatchJob;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.JobManager;
import org.springframework.batch.core.Job;

import javax.annotation.Resource;
import java.util.List;

/**
 * Quartz Scheduler Job
 */
public abstract class BaseQuartzScheduler {

    protected BatchJob batchJob;

    @Resource(name = "jobManager")
    private JobManager jobManager;

    protected abstract void setJob();

    public synchronized void execute() {
        // search status of job list to see wether has job running now
        com.worthsoln.patientview.model.Job job = jobManager.getJobList(SendEmailEnum.RUNNING);
        if (job == null) {
            setJob();
            batchJob.run();
        }
    }
}
