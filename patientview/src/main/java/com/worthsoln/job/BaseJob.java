package com.worthsoln.job;

import com.worthsoln.patientview.model.Job;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.core.launch.JobLauncher;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

/**
 * Definitions base use methods for job
 */
public class BaseJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseJob.class);

    //@Autowired
    //protected JobLauncher jobLauncher;

    protected Job job;

    public void execute(){
        // todo
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

        if (result.getStatus() != BatchStatus.COMPLETED) {
            updateJobStatusFailded(job);
        } else {
            updateJobStatusSuccessed(job);
        }
    }

    /**
     * Catch exception from batch reader
     *
     * @param e reader exception
     */
    @OnReadError
    public void onReadError(Exception e) {
        // todo
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
        // todo
    }

    /**
     * Prepare job configuration before batch reader
     */
    protected void prepare() {}

    public void updateJobStatusFailded(Job job) {
        //todo
    }

    public void updateJobStatusSuccessed(Job job) {

    }
}
