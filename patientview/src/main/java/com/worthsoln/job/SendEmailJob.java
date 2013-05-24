package com.worthsoln.job;

import com.worthsoln.batch.CreateEmailQueueReader;
import com.worthsoln.batch.SendEmailReader;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Send the email to special group members after the unitadmin send the bulk message
 */
@Component
public class SendEmailJob extends BatchJob {

    @Resource(name = "sendEmailBatchJob")
    private Job batchJob;

    @Resource(name = "sendEmailReader")
    private SendEmailReader reader;

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    protected void prepare(com.worthsoln.patientview.model.Job job){
        reader.refresh(job);
    }

    protected  void setJob() {
       this.job = jobManager.getJobList(SendEmailEnum.SENDING);
    }
}
