package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
@Lazy
public class CreateEmailQueueWriter implements ItemWriter<Object> {

    @Override
    public void write(List<? extends Object> items) throws Exception {
        for (Object obj : items) {
            if (obj instanceof EmailQueue) {
                // todo
            }
        }

    }
}
