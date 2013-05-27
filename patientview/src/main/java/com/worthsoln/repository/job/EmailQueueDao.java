package com.worthsoln.repository.job;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Job dao interface
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface EmailQueueDao {

    void update(EmailQueue emailQueue);

    void save(EmailQueue emailQueue);

    List<EmailQueue> getEmailQueueList();

    EmailQueue get(Long jobId, Long messageId, Long userId);
}
