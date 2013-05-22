package com.worthsoln.repository.impl.job;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.job.EmailQueueDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "emailQueueDao")
public class EmailQueueDaoImpl extends AbstractHibernateDAO<EmailQueue> implements EmailQueueDao {

    @Override
    public List<EmailQueue> getUnsentMessageList(SendEmailEnum status) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(EmailQueue emailQueue) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
