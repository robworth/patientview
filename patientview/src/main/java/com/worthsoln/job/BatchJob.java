package com.worthsoln.job;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.JobManager;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.slf4j.Logger;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definitions base use methods for job
 */
public abstract class BatchJob {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected JobManager jobManager;

    /**
     * Run the batch job
     */
    public void run(){

        Map<String, JobParameter> map = new HashMap<String, JobParameter>();
        map.put("key", new JobParameter(new Date()));
        try {
            JobExecution result = jobLauncher.run(getBatchJob(), new JobParameters(map));
        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace().toString());
            onRunError(e);
        }
    }

    /**
     * Method done before run job
     */
    @BeforeJob
    public void beforeJob() {
        prepare();
    }

    /**
     * Method done after job
     *
     * @param result jobExecution
     */
    @AfterJob
    public void afterJob(JobExecution result) {
        LOGGER.debug(result.toString());
        afterBatchJob(result);
    }

    /**
     * Catch exception from batch reader
     *
     * @param e reader exception
     */
    @OnReadError
    public void onReadError(Exception e) {
        LOGGER.debug(e.getMessage());
    }

    /**
     * Report information for skip rows in batch reader
     *
     * @param holder
     * @param problem
     */
    @OnSkipInWrite
    public void onSkipInWriter(Object holder, Throwable problem) {
        onJobSkipInWriter(holder, problem);
    }

    /**
     * Prepare job configuration before batch reader
     */
    protected abstract void prepare();

    /**
     * Get the runnable org.springframework.batch.core.Job instance
     * @return Job
     */
    protected abstract org.springframework.batch.core.Job getBatchJob();

    /**
     * Do something when skip in writer
     *
     * @param holder
     * @param problem
     */
    protected abstract void onJobSkipInWriter(Object holder, Throwable problem);

    /**
     * Do something after job
     * @param result
     */
    protected abstract void afterBatchJob(JobExecution result);

    /**
     *
     * @param e
     */
    protected abstract void onRunError(Exception e);

}
