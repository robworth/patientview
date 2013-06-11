package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.job.EmailQueueDao;
import com.worthsoln.service.EmailQueueManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "emailQueueManager")
public class EmailQueueManagerImpl implements EmailQueueManager {

    @Inject
    private EmailQueueDao emailQueueDao;


    @Override
    public void save(EmailQueue emailQueue) throws Exception {
        if (!emailQueue.hasValidId()) {
           emailQueue.setCreated(new Date());
        }
        emailQueueDao.save(emailQueue);
    }

    @Override
    public List<EmailQueue> getEmailQueueList() {
        return emailQueueDao.getEmailQueueList();
    }

    @Override
    public List<EmailQueue> getEmailQueueList(Long jobId) {
        return emailQueueDao.getEmailQueueList(jobId);
    }

    @Override
    public EmailQueue get(Long jobId, Long messageId, Long userId) {
        return emailQueueDao.get(jobId, messageId, userId);
    }
}
