package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.messaging.ConversationDao;
import com.worthsoln.repository.messaging.MessageDao;
import com.worthsoln.service.EmailManager;
import com.worthsoln.service.MessageManager;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service(value = "messageManager")
public class MessageManagerImpl implements MessageManager {

    // TODO: could build the string to format based on if its more than 1 day more than 1 year etc
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MMM/yy HH:mm");
    private static final SimpleDateFormat SHORT_DAY_FORMAT = new SimpleDateFormat("HH:mm");

    @Inject
    private ConversationDao conversationDao;

    @Inject
    private MessageDao messageDao;

    @Inject
    private EmailManager emailManager;

    @Override
    public Conversation getConversation(Long conversationId) {
        return conversationDao.get(conversationId);
    }

    @Override
    public Conversation getConversationForUser(Long conversationId, Long participantId) {
        Conversation conversation = conversationDao.get(conversationId);

        if (!userHasAccessToConversation(conversation, participantId)) {
            return null;
        }

        populateConversation(conversation, participantId);
        return conversation;
    }

    @Override
    public List<Conversation> getConversations(Long participantId) {
        List<Conversation> conversations = conversationDao.getConversations(participantId);
        populateConversations(conversations, participantId);
        return conversations;
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
        List<Message> messages = messageDao.getMessages(conversationId);

        // go through and set any values the manager creates and return
        for (Message message : messages) {
            message.setFriendlyDate(getFriendlyDateTime(message.getDate()));
        }

        return messages;
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

        // now send the message
        emailManager.sendUserMessage(message);

        message.setFriendlyDate(getFriendlyDateTime(message.getDate()));

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

    private boolean userHasAccessToConversation(Conversation conversation, Long participantId) {
        return conversation.getParticipant1().getId().equals(participantId)
                || conversation.getParticipant2().getId().equals(participantId);
    }

    private void populateConversations(List<Conversation> conversations, Long participantId) {
        if (conversations != null) {
            for (Conversation conversation : conversations) {
                populateConversation(conversation, participantId);
            }
        }
    }

    // need to populate this list for the user
    // we add the total number of unread for each convo for THAT user
    // we add the summary of the last message in that convo and the date of it
    // need to go through and show how many messages in a convo that user needs to read
    private void populateConversation(Conversation conversation, Long participantId) {
        if (conversation != null) {
            conversation.setNumberUnread(messageDao.getNumberOfUnreadMessages(
                    participantId, conversation.getId()).intValue());

            // set the summary details for the convo to the last message
            Message latestMessage = messageDao.getLatestMessage(conversation.getId());

            if (latestMessage != null) {
                conversation.setLatestMessageSummary(latestMessage.getSummary());
                conversation.setLatestMessageDate(getFriendlyDateTime(latestMessage.getDate()));
            }

            // as there two users in the convo we want the front end to be able to show titles based on the other
            // user in the convo and not the user who is viewing it
            if (conversation.getParticipant1().getId().equals(participantId)) {
                conversation.setOtherUser(conversation.getParticipant2());
            } else {
                conversation.setOtherUser(conversation.getParticipant1());
            }
        }
    }

    private String getFriendlyDateTime(Date date) {
        DateTime now = new DateTime();
        DateTime dateTime = new DateTime(date);

        if (dateTime.getYear() == now.getYear()
                && dateTime.getMonthOfYear() == now.getMonthOfYear()
                && dateTime.getDayOfWeek() == now.getDayOfWeek()) {
            return SHORT_DAY_FORMAT.format(date);
        } else {
            return DATE_FORMAT.format(date);
        }
    }
}
