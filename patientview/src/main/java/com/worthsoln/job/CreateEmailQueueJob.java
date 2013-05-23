package com.worthsoln.job;

import com.worthsoln.batch.CreateEmailQueueReader;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.sql.PreparedStatement;

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
    protected void setJob() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }


    protected void prepare(){

    }
}
