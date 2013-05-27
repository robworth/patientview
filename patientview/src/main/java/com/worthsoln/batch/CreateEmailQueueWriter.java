package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.service.EmailManager;
import com.worthsoln.service.EmailQueueManager;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CreateEmailQueueJob writer
 */
@Component
@Lazy
public class CreateEmailQueueWriter implements ItemWriter<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Override
    public void write(List<? extends Object> items) throws Exception {
        for (Object obj : items) {
            if (obj instanceof EmailQueue) {
                emailQueueManager.save((EmailQueue)obj);
            }
        }
    }
}
