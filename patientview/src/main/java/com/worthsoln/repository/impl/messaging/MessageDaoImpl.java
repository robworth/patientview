package com.worthsoln.repository.impl.messaging;

import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.Message_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.messaging.MessageDao;
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
@Repository(value = "messageDao")
public class MessageDaoImpl extends AbstractHibernateDAO<Message> implements MessageDao {

    @Override
    public Message get(Long id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Message> criteria = builder.createQuery(Message.class);

        Root<Message> root = criteria.from(Message.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Message_.deleted), false));
        wherePredicates.add(builder.equal(root.get(Message_.id), id));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Message> getMessages(Long conversationId) {
        // only want to return frameworks if we have a user filter
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Message> criteria = builder.createQuery(Message.class);

        Root<Message> root = criteria.from(Message.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Message_.deleted), false));
        wherePredicates.add(builder.equal(root.get(Message_.conversation), conversationId));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(Message_.date)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<Message> getUnreadMessages(Long recipientId, Long conversationId) {
        // only want to return frameworks if we have a user filter
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Message> criteria = builder.createQuery(Message.class);

        Root<Message> root = criteria.from(Message.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Message_.deleted), false));
        wherePredicates.add(builder.equal(root.get(Message_.conversation), conversationId));
        wherePredicates.add(builder.equal(root.get(Message_.recipient), recipientId));
        wherePredicates.add(builder.equal(root.get(Message_.hasRead), false));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(Message_.date)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public Long getNumberOfUnreadMessages(Long recipientId, Long conversationId) {
        // only want to return frameworks if we have a user filter
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

        Root<Message> root = criteria.from(Message.class);

        criteria.select(builder.count(root));

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(Message_.deleted), false));
        wherePredicates.add(builder.equal(root.get(Message_.conversation), conversationId));
        wherePredicates.add(builder.equal(root.get(Message_.recipient), recipientId));
        wherePredicates.add(builder.equal(root.get(Message_.hasRead), false));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(Message_.date)));

        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    @Override
    public void save(Message message) {
        if (!message.hasValidId()) {
            message.setDate(new Date());
        }

        super.save(message);
    }

    @Override
    public void delete(Message message) {
        message.setDeleted(true);
        save(message);
    }
}
