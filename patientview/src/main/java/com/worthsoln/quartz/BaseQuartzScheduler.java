package com.worthsoln.quartz;

import com.worthsoln.job.BatchJob;
import com.worthsoln.service.JobManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * Quartz Scheduler Job
 */
public abstract class BaseQuartzScheduler {

    protected BatchJob batchJob;

    protected abstract void setJob();

    public synchronized void execute() {

        setJob();
        batchJob.run();
    }
}
