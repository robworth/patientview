package com.worthsoln.job;

import com.worthsoln.batch.CreateEmailQueueReader;
import com.worthsoln.batch.SendEmailReader;
import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Send the email to special group members after the unitadmin send the bulk message
 */
@Component
public class SendEmailJob extends BatchJob {

    @Resource(name = "sendEmailBatchJob")
    private Job batchJob;

    @Resource(name = "sendEmailReader")
    private SendEmailReader reader;

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    private Map<String, Boolean> map;

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    @Override
    protected void onJobSkipInWriter(Object holder, Throwable problem) {
        isSkip = true;

        if (holder instanceof EmailQueue) {
            ((EmailQueue) holder).setStatus(SendEmailEnum.FAILED);
            ((EmailQueue) holder).setFinished(new Date());
            com.worthsoln.patientview.model.Job job = ((EmailQueue) holder).getJob();

            try {
                emailQueueManager.save((EmailQueue) holder);
                map.put(((EmailQueue) holder).getJob().getId().toString(), true);
                int jobIndex = jobs.indexOf(job);
                if ( jobIndex != -1) {
                    jobs.get(jobIndex).addReport(
                          "username=" + ((EmailQueue) holder).getRecipient().getUsername()
                        + ",messageId=" + ((EmailQueue) holder).getMessage().getId()
                        + " : " + problem.getMessage());

                    jobs.get(jobIndex).addErrorCount();
                    jobs.get(jobIndex).setStatus(SendEmailEnum.FAILED);
                }
            } catch (Exception e) {
                LOGGER.debug(e.getMessage());
            }
        }
    }

    protected void prepare(List<com.worthsoln.patientview.model.Job> jobs){
        map = new HashMap<String, Boolean>();
        reader.refresh(jobs);
    }

    protected  void setJobs() {
       this.jobs = jobManager.getJobList(SendEmailEnum.SENDING);
    }

    @Override
    protected void updateJobStatusFailded(List<com.worthsoln.patientview.model.Job> jobs) {
        for (com.worthsoln.patientview.model.Job job : jobs) {
            job.setFinished(new Date());
            if (map.get(job.getId().toString())) {
                job.setStatus(SendEmailEnum.FAILED);
                job.convertReports();
            } else {
                job.setStatus(SendEmailEnum.SUCCESSED);
            }
            jobManager.save(job);
        }
    }

    @Override
    protected void updateJobAfterJob(List<com.worthsoln.patientview.model.Job> jobs) {
    }
}
