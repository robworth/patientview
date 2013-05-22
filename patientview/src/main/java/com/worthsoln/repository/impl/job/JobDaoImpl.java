package com.worthsoln.repository.impl.job;

import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.job.JobDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "jobDao")
public class JobDaoImpl extends AbstractHibernateDAO<Job> implements JobDao {

    @Override
    public void save(Job job) {
        if (!job.hasValidId()) {
            job.setCreated(new Date());
        }

        super.save(job);
    }

    @Override
    public List<Job> getJobList(SendEmailEnum status) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Job job) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
