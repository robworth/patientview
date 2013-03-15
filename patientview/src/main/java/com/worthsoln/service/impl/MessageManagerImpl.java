package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.messaging.ConversationDao;
import com.worthsoln.repository.messaging.MessageDao;
import com.worthsoln.service.MessageManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service(value = "messageManager")
public class MessageManagerImpl implements MessageManager {

    @Inject
    private ConversationDao conversationDao;

    @Inject
    private MessageDao messageDao;

    @Override
    public Conversation getConversation(Long conversationId) {
        return conversationDao.get(conversationId);
    }

    @Override
    public List<Conversation> getConversations(Long participantId) {
        return conversationDao.getConversations(participantId);
    }

    @Override
    public void deleteConversation(Long conversationId) {
        deleteConversation(conversationDao.get(conversationId));
    }

    @Override
    public void deleteConversation(Conversation conversation) {
        if (conversation != null) {
            conversationDao.delete(conversation);
        }
    }

    @Override
    public List<Message> getMessages(Long conversationId) {
        return messageDao.getMessages(conversationId);
    }

    @Override
    public Message createMessage(String content, User sender, User recipient) {
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("Invalid required parameter content");
        }

        if (sender == null || !sender.hasValidId()) {
            throw new IllegalArgumentException("Invalid required parameter sender");
        }

        if (recipient == null || !recipient.hasValidId()) {
            throw new IllegalArgumentException("Invalid required parameter recipient");
        }

        Conversation conversation = conversationDao.getConversationBetweenUsers(sender.getId(),
                recipient.getId());

        if (conversation == null) {
            conversation = new Conversation();
            conversation.setParticipant1(sender);
            conversation.setParticipant2(recipient);
            conversationDao.save(conversation);
        }

        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        messageDao.save(message);

        return message;
    }

    @Override
    public int getTotalNumberUnreadMessages(Long recipientId) {
        int total = 0;

        List<Conversation> conversations = getConversations(recipientId);

        for (Conversation conversation : conversations) {
            total += conversation.getNumberUnread();
        }

        return total;
    }

    @Override
    public void markMessagesAsReadForConversation(Long recipientId, Long conversationId) {
        List<Message> unreadMessages = messageDao.getUnreadMessages(recipientId, conversationId);

        for (Message message : unreadMessages) {
            message.setHasRead(true);
            messageDao.save(message);
        }
    }
}
