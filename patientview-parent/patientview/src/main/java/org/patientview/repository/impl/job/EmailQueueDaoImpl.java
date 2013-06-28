/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */
package org.patientview.repository.impl.job;

import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.EmailQueue_;

import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.job.EmailQueueDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * EmailQueueDao implement Class
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "emailQueueDao")
public class EmailQueueDaoImpl extends AbstractHibernateDAO<EmailQueue> implements EmailQueueDao {

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
    public List<EmailQueue> getEmailQueueList(Long jobId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmailQueue> criteria = builder.createQuery(EmailQueue.class);

        Root<EmailQueue> root = criteria.from(EmailQueue.class);

        criteria.where(builder.equal(root.get(EmailQueue_.job), jobId));
        criteria.orderBy(builder.desc(root.get(EmailQueue_.status)));

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
