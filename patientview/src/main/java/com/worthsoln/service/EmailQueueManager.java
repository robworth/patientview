package com.worthsoln.service;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;

import java.util.List;

public interface EmailQueueManager {

    void save(EmailQueue emailQueue);

    List<EmailQueue> getJobList(SendEmailEnum status);

    void update(EmailQueue emailQueue);
}
