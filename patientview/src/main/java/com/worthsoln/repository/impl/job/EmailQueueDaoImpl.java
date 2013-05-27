package com.worthsoln.repository.impl.job;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.job.EmailQueueDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "emailQueueDao")
public class EmailQueueDaoImpl extends AbstractHibernateDAO<EmailQueue> implements EmailQueueDao {

    @Override
    public void update(EmailQueue emailQueue) {
        super.save(emailQueue);
    }

    @Override
    public void save(EmailQueue emailQueue) {
        super.save(emailQueue);
    }

    @Override
    public List<EmailQueue> getEmailQueueList() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmailQueue> criteria = builder.createQuery(EmailQueue.class);

        Root<EmailQueue> root = criteria.from(EmailQueue.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.or(builder.equal(root.get(EmailQueue_.status), SendEmailEnum.SENDING),
                builder.equal(root.get(EmailQueue_.status), SendEmailEnum.FAILED)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(EmailQueue_.created)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public EmailQueue get(Long jobId, Long messageId, Long userId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmailQueue> criteria = builder.createQuery(EmailQueue.class);

        Root<EmailQueue> root = criteria.from(EmailQueue.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(EmailQueue_.job), jobId));
        wherePredicates.add(builder.equal(root.get(EmailQueue_.message), messageId));
        wherePredicates.add(builder.equal(root.get(EmailQueue_.recipient), userId));


        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(EmailQueue_.created)));

        List<EmailQueue> emailQueues = getEntityManager().createQuery(criteria).getResultList();

        if (emailQueues != null && !emailQueues.isEmpty()) {
            return emailQueues.get(0);
        } else {
            return null;
        }
    }


}
