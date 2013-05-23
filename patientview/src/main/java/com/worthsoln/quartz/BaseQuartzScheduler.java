package com.worthsoln.quartz;

import com.worthsoln.job.BatchJob;
import org.springframework.batch.core.Job;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-23
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseQuartzScheduler {

    protected BatchJob batchJob;

    protected abstract void setJob();

    public synchronized void execute() {
        // todo
        setJob();
        batchJob.run();
    }
}
