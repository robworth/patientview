package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.service.EmailQueueManager;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
@Lazy
public class SendEmailWriter implements ItemWriter<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Override
    public void write(List<? extends Object> items) throws Exception {
        for (Object obj : items) {
            if (obj instanceof EmailQueue) {
                // TODO
            }
        }
    }
}
