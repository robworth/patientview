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

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

    @Autowired
    protected JobLauncher jobLauncher;

    //@Resource(name = "jobManager")
    @Autowired
    protected JobManager jobManager;

    protected Job job;

    private boolean isSkip = false;

    /**
     * Set job list which status is PENDING
     */
    protected  void setJob() {}

    /**
     *
     */
    public void run(){

        setJob();
        if (this.job == null) {
            return;
        }

        // update jobs' start time and status
        updateJobStatusRunning();
        isSkip = false;

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
        prepare(job);
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

            updateJobStatusFailded(job);
        } else {
            if (getBatchJob().getName().equals("createEamilQueueBatchJob")) {
                updateJobStatusSending(job);
            } else {
                updateJobStatusSuccessed(job);
            }
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

        isSkip = true;
        if (holder instanceof EmailQueue) {
            EmailQueue emailQueue = (EmailQueue) holder;
            Job job = emailQueue.getJob();

            job.addReport("username=" + emailQueue.getRecipient().getUsername()
                    + ",messageId=" + emailQueue.getMessage().getId()
                    + " : " +problem.getMessage());

            job.addErrorCount();

            jobManager.save(job);
        }
    }

    /**
     * Prepare job configuration before batch reader
     */
    protected void prepare(Job job) {}

    /**
     * Update the jobs' status to RUNNING
     */
    public void updateJobStatusRunning() {
        LOGGER.debug("==update running status==");
        job.setStarted(new Date());
        job.setStatus(SendEmailEnum.RUNNING);
        jobManager.save(job);
    }

    /**
     * Update the jobs' status to FAILED
     *
     * @param job
     */
    public void updateJobStatusFailded(Job job) {
        LOGGER.debug("==update failed status==");
        job.convertReports();
        job.setFinished(new Date());
        job.setStatus(SendEmailEnum.FAILED);
        jobManager.save(job);
    }

    /**
     * Update the jobs' status to SUCCESSED
     * @param job
     */
    public void updateJobStatusSuccessed(Job job) {
        LOGGER.debug("==update successed status==");

        job.setFinished(new Date());
        job.setStatus(SendEmailEnum.SUCCESSED);
        jobManager.save(job);
    }

    public void updateJobStatusSending(Job job) {
        LOGGER.debug("==update sending status==");

        job.setFinished(new Date());
        job.setStatus(SendEmailEnum.SENDING);
        jobManager.save(job);
    }

    protected abstract org.springframework.batch.core.Job getBatchJob();
}
