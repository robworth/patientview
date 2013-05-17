package com.worthsoln.repository.impl.messaging;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.messaging.GroupMessageDao;
import com.worthsoln.repository.messaging.MessageDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "groupMessageDao")
public class GroupMessageDaoImpl extends AbstractHibernateDAO<GroupMessage> implements GroupMessageDao {


    @Override
    public void save(GroupMessage groupMessage) {
        super.save(groupMessage);
    }

    @Override
    public int getNumberOfUnreadMessages(Long conversationId, GroupEnum groupEnum) {

        String sql = "SELECT count(groupmessage.conversation_id) FROM groupmessage, conversation " +
                    "WHERE groupmessage.conversation_id = conversation.id " +
                    "AND conversation.deleted = 0 " +
                    "AND conversation.groupenum = :groupName " +
                    "AND groupmessage.conversation_id = :conversationId";

        Query query = getEntityManager().createNativeQuery(sql, GroupMessage.class);

        query.setParameter("conversationId", conversationId);
        query.setParameter("groupName", groupEnum);

         return query.getFirstResult();

    }

    @Override
    public GroupMessage get(Long recipientId, Long conversationId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GroupMessage> criteria = builder.createQuery(GroupMessage.class);

        Root<GroupMessage> root = criteria.from(GroupMessage.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(GroupMessage_.conversation), conversationId));
        wherePredicates.add(builder.equal(root.get(GroupMessage_.recipient), recipientId));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
