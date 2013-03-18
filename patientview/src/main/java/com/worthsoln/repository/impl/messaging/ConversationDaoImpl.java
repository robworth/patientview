package com.worthsoln.repository.impl.messaging;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Conversation_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.messaging.ConversationDao;
import com.worthsoln.repository.messaging.MessageDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
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
    public Conversation getConversationBetweenUsers(Long participant1Id, Long participant2Id) {
        // only want to return frameworks if we have a user filter
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Conversation> criteria = builder.createQuery(Conversation.class);

        Root<Conversation> root = criteria.from(Conversation.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Conversation_.deleted), false));

        // from/to user = user 1
        wherePredicates.add(builder.or(builder.equal(root.get(Conversation_.participant1), participant1Id),
                builder.equal(root.get(Conversation_.participant1), participant2Id)));

        // from/to user = user 2
        wherePredicates.add(builder.or(builder.equal(root.get(Conversation_.participant2), participant1Id),
                builder.equal(root.get(Conversation_.participant2), participant2Id)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(Conversation_.started)));

        List<Conversation> conversations = getEntityManager().createQuery(criteria).getResultList();

        // there should only every be 1 conversation at a time between 2 users but just in case
        if (conversations != null && !conversations.isEmpty()) {
            return conversations.get(0);
        }

        return null;
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
