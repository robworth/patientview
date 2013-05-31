package com.worthsoln.repository.impl.job;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.job.JobDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * JobDao implement Class
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
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Job> criteria = builder.createQuery(Job.class);
        Root<Job> jobRoot = criteria.from(Job.class);

        criteria.where(builder.equal(jobRoot.get(Job_.status), status));

        criteria.orderBy(builder.asc(jobRoot.get(Job_.created)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

}
