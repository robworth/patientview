package com.worthsoln.job;

import com.worthsoln.patientview.model.Job;
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
import java.util.Map;

/**
 * Definitions base use methods for job
 */
public abstract class BatchJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

    @Autowired
    protected JobLauncher jobLauncher;

    protected Job job;

    protected abstract void setJob();

    public void run(){

        Map<String, JobParameter> map = new HashMap<String, JobParameter>();
        map.put("key", new JobParameter(new Date()));
        try {
            JobExecution result = jobLauncher.run(getBatchJob(), new JobParameters(map));
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JobRestartException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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

    protected abstract org.springframework.batch.core.Job getBatchJob();
}
