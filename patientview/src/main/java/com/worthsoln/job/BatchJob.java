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

    protected List<Job> jobs;

    protected boolean isSkip = false;

    /**
     * Run the batch job
     */
    public void run(){

        setJobs();
        if (this.jobs == null || jobs.isEmpty()) {
            return;
        }

        // update jobs' start time and status
        updateJobStatusRunning();
        isSkip = false;

        Map<String, JobParameter> map = new HashMap<String, JobParameter>();
        map.put("key", new JobParameter(new Date()));
        try {
            JobExecution result = jobLauncher.run(getBatchJob(), new JobParameters(map));
        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace().toString());
            updateJobStatusFailded(jobs);
        }
    }

    /**
     * Method done before run job
     */
    @BeforeJob
    public void beforeJob() {
        prepare(jobs);
    }

    /**
     * Method done after job
     *
     * @param result jobExecution
     */
    @AfterJob
    public void afterJob(JobExecution result) {
        LOGGER.debug(result.toString());

        if (result.getStatus() != BatchStatus.COMPLETED || isSkip) {
            updateJobStatusFailded(jobs);
        } else {
            updateJobAfterJob(jobs);
        }
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
     * Update the jobs' status to RUNNING
     */
    public void updateJobStatusRunning() {
        LOGGER.debug("==update running status==");
        for (Job job : jobs) {
            job.setStarted(new Date());
            job.setStatus(SendEmailEnum.RUNNING);
            jobManager.save(job);
        }
    }

     /**
     * According to the status, select and set the jobs from Job entry
     */
    protected abstract  void setJobs();

    /**
     * Prepare job configuration before batch reader
     */
    protected void prepare(List<Job> job) {}

    /**
     * Update the jobs' status to FAILED
     *
     * @param jobs
     */
    protected abstract void updateJobStatusFailded(List<Job> jobs);

    /**
     * Update the jobs' status after finished
     * @param jobs
     */
    protected abstract void updateJobAfterJob(List<Job> jobs);

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
}
