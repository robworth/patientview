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

package org.patientview.repository.impl.messaging;

import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Conversation_;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.messaging.ConversationDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "conversationDao")
public class ConversationDaoImpl extends AbstractHibernateDAO<Conversation> implements ConversationDao {

    @Override
    public Conversation get(Long id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Conversation> criteria = builder.createQuery(Conversation.class);

        Root<Conversation> root = criteria.from(Conversation.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Conversation_.deleted), false));
        wherePredicates.add(builder.equal(root.get(Conversation_.id), id));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Conversation> getConversations(Long participantId) {
        // only want to return frameworks if we have a user filter
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Conversation> criteria = builder.createQuery(Conversation.class);

        Root<Conversation> root = criteria.from(Conversation.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Conversation_.deleted), false));
        wherePredicates.add(builder.or(builder.equal(root.get(Conversation_.participant1), participantId),
                builder.equal(root.get(Conversation_.participant2), participantId)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(Conversation_.started)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<Conversation> getConversations(Long participantId, GroupEnum groupEnum) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Conversation> criteria = builder.createQuery(Conversation.class);

        Root<Conversation> root = criteria.from(Conversation.class);

        Predicate predicate1 = builder.and(
                builder.equal(root.get(Conversation_.deleted), false),
                builder.or(builder.equal(root.get(Conversation_.participant1), participantId),
                builder.equal(root.get(Conversation_.participant2), participantId)));

        Predicate predicate2 = builder.and(
                builder.equal(root.get(Conversation_.deleted), false),
                builder.equal(root.get(Conversation_.type), "BULK"),
                builder.equal(root.get(Conversation_.groupEnum), groupEnum)
        );

        predicate2 = builder.or(predicate1, predicate2);

        criteria.where(predicate2);

        criteria.orderBy(builder.asc(root.get(Conversation_.started)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void save(Conversation conversation) {
        if (!conversation.hasValidId()) {
            conversation.setStarted(new Date());
        }

        super.save(conversation);
    }

    @Override
    public void delete(Conversation conversation) {
        conversation.setDeleted(true);
        save(conversation);
    }
}
