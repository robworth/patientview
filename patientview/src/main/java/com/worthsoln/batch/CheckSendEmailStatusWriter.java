package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Update the jobs' status
 */
@Component
@Lazy
public class CheckSendEmailStatusWriter implements ItemWriter<Object> {

    @Autowired
    private JobManager jobManager;

    @Override
    public void write(List<? extends Object> items) throws Exception {

        boolean isFailed = false;
        for (Object obj : items) {
            jobManager.save((Job)obj);
        }
    }
}
