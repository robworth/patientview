package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.job.JobDao;
import com.worthsoln.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "jobManager")
public class JobManagerImpl implements JobManager {

    @Inject
    private JobDao jobDao;

    @Autowired
    private UserManager userManager;


    @Override
    public void save(Job job) {
        jobDao.save(job);
    }

    @Override
    public List<Job> getJobList(SendEmailEnum status) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Job job) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getSpecialGroupUsers(User user, Specialty specialty, String userType) {
        return userManager.getUsers(user, specialty, userType);
    }
}
