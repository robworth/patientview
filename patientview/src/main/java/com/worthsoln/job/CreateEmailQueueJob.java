package com.worthsoln.job;

import com.worthsoln.batch.CreateEmailQueueReader;
import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.sql.PreparedStatement;
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

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    @Override
    protected void onJobSkipInWriter(Object holder, Throwable problem) {

       isSkip = true;
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


    protected void prepare(List<com.worthsoln.patientview.model.Job> jobs){
        reader.refresh(jobs);
    }

    @Override
    protected void updateJobStatusFailded(List<com.worthsoln.patientview.model.Job> jobs) {
        LOGGER.debug("==update failed status==");
        for (com.worthsoln.patientview.model.Job job : jobs) {
            job.convertReports();
            job.setFinished(new Date());
            job.setStatus(SendEmailEnum.FAILED);
            jobManager.save(job);
        }
    }

    @Override
    protected void updateJobAfterJob(List<com.worthsoln.patientview.model.Job> jobs) {
        LOGGER.debug("==update sending status==");
        for (com.worthsoln.patientview.model.Job job : jobs) {
            job.setFinished(new Date());
            if (SendEmailEnum.RUNNING.equals(job.getStatus())) {
                job.setStatus(SendEmailEnum.SENDING);
            }
            jobManager.save(job);
        }
    }

    protected  void setJobs() {
       this.jobs = jobManager.getJobList(SendEmailEnum.PENDING);
    }
}
