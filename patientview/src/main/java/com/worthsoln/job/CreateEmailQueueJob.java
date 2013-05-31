package com.worthsoln.job;

import com.worthsoln.batch.CreateEmailQueueReader;
import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Get the unsent messages related members's information then insert the records to EamilQueue
 */
@Component
public class CreateEmailQueueJob extends BatchJob {

    @Resource(name = "createEamilQueueBatchJob")
    private Job batchJob;

    @Resource(name = "createEmailQueueReader")
    private CreateEmailQueueReader reader;

    private List<com.worthsoln.patientview.model.Job> jobs;

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    @Override
    protected void onJobSkipInWriter(Object holder, Throwable problem) {

        if (holder instanceof EmailQueue) {
            EmailQueue emailQueue = (EmailQueue) holder;
            com.worthsoln.patientview.model.Job job = emailQueue.getJob();
            int jobIndex = jobs.indexOf(job);
            if ( jobIndex != -1) {
                job.addReport(
                      "username=" + emailQueue.getRecipient().getUsername()
                    + ",messageId=" + emailQueue.getMessage().getId()
                    + " : " +problem.getMessage());

                job.addErrorCount();
                job.setStatus(SendEmailEnum.FAILED);

            }
        }
    }

    @Override
    protected void afterBatchJob(JobExecution result) {
        if (result.getStatus() != BatchStatus.COMPLETED) {
            updateJobStatusFailded(jobs);
        } else {
            updateJobStatusSucceeded(jobs);
        }
    }

    @Override
    protected void onRunError(Exception e) {
        LOGGER.debug(e.getMessage());
        if (jobs != null) {
            for (com.worthsoln.patientview.model.Job job : jobs) {
                job.addReport(e.getMessage());
                job.convertReports();
                job.setFinished(new Date());
                job.setStatus(SendEmailEnum.FAILED);
                jobManager.save(job);
            }
        }
    }

    @Override
    protected void prepare() {
        jobs = jobManager.getJobList(SendEmailEnum.PENDING);
        reader.refresh(jobs);
    }

    /**
     * Update the jobs' status to FAILED
     * @param jobs
     */
    protected void updateJobStatusFailded(List<com.worthsoln.patientview.model.Job> jobs) {
        LOGGER.debug("==update failed status==");
        for (com.worthsoln.patientview.model.Job job : jobs) {
            job.convertReports();
            job.setFinished(new Date());
            job.setStatus(SendEmailEnum.FAILED);
            jobManager.save(job);
        }
    }

    private void updateJobStatusSucceeded(List<com.worthsoln.patientview.model.Job> jobs) {
        LOGGER.debug("==update sent status==");
        for (com.worthsoln.patientview.model.Job job : jobs) {
            job.convertReports();
            job.setFinished(new Date());
            if (!SendEmailEnum.FAILED.equals(job.getStatus())) {
                job.setStatus(SendEmailEnum.SENT);
            }
            jobManager.save(job);
        }
    }

    /**
     * According to the status, select and set the jobs from Job entry
     */
    private  void setJobs() {
       this.jobs = jobManager.getJobList(SendEmailEnum.PENDING);
    }
}
