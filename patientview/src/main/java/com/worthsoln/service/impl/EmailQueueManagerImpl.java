package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.job.EmailQueueDao;
import com.worthsoln.service.EmailQueueManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "emailQueueManager")
public class EmailQueueManagerImpl implements EmailQueueManager {

    @Inject
    private EmailQueueDao jobDao;

    @Override
    public void save(EmailQueue emailQueue) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<EmailQueue> getJobList(SendEmailEnum status) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(EmailQueue emailQueue) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
