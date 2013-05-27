package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import com.worthsoln.service.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * SendEmailJob reader
 */
@Component
public class SendEmailReader extends ListItemReader<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    public void refresh(List<Job> jobs) {

        List<Object> emailQueues = new ArrayList<Object>();

        emailQueues.addAll(emailQueueManager.getEmailQueueList());

        setList(emailQueues);

    }
}
