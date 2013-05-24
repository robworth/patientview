package com.worthsoln.job;

import com.worthsoln.batch.CreateEmailQueueReader;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.sql.PreparedStatement;
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


    protected void prepare(com.worthsoln.patientview.model.Job job){
        reader.refresh(job);
    }

    protected  void setJob() {
       this.job = jobManager.getJobList(SendEmailEnum.PENDING);
    }
}
