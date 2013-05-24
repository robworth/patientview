package com.worthsoln.service;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.SendEmailEnum;

import javax.servlet.ServletContext;
import java.util.List;

public interface JobManager {

    void save(Job job);

    Job getJobList(SendEmailEnum status);

    void update(Job job);

    List<User> getSpecialGroupUsers(User user, Specialty specialty, String userType, String messageId);
}
