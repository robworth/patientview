package com.worthsoln.service;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmailQueueManager {

    void save(EmailQueue emailQueue) throws Exception;

    List<EmailQueue> getEmailQueueList();

    EmailQueue get(Long jobId, Long messageId, Long userId);
}
