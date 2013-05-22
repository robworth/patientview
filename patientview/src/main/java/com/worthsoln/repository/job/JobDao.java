package com.worthsoln.repository.job;

import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Job dao interface
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface JobDao {

    void save(Job job);

    List<Job> getJobList(SendEmailEnum status);

    void update(Job job);
}
