package com.worthsoln.repository.impl.messaging;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Conversation_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.messaging.ConversationDao;
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
                builder.equal(root.get(Conversation_.participant2), participantId),
                builder.equal(root.get(Conversation_.type), "BULK")));

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
