package com.worthsoln.service;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.User;

import java.util.List;

public interface MessageManager {

    Conversation getConversation(Long conversationId);

    List<Conversation> getConversations(Long participantId);

    void deleteConversation(Long conversationId);

    void deleteConversation(Conversation conversation);

    List<Message> getMessages(Long conversationId);

    Message createMessage(String content, User sender, User recipient) throws Exception;

    int getTotalNumberUnreadMessages(Long recipientId);

    void markMessagesAsReadForConversation(Long recipientId, Long conversationId);
}
