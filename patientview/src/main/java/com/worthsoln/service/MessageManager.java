package com.worthsoln.service;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;

import javax.servlet.ServletContext;
import java.util.List;

public interface MessageManager {

    Conversation getConversation(Long conversationId);

    /**
     * This will get the conversation but applied to the current user
     * So will include number of number of unread messages etc and other users will be set
     * @param conversationId Long
     * @param participantId Long
     * @return Conversation
     */
    Conversation getConversationForUser(Long conversationId, Long participantId);

    List<Conversation> getConversations(Long participantId);

    void deleteConversation(Long conversationId);

    void deleteConversation(Conversation conversation);

    List<Message> getMessages(Long conversationId);

    Message createMessage(ServletContext context, String subject, String content, User sender, User recipient)
            throws Exception;

    Message createGroupMessage(ServletContext context, String subject, String content, User sender, String groupName, String type)
            throws Exception;;

    Message replyToMessage(ServletContext context, String content, Long conversationId, User sender) throws Exception;

    int getTotalNumberUnreadMessages(Long recipientId);

    /**
     * Marks the conversation as read only if logged in user is the recipient
     */
    void markMessagesAsReadForConversation(Long loggedInUserId, Long conversationId);

    List<User> getUnitAdminRecipients(List<Unit> units, User requestingUser);

    List<User> getUnitAdminRecipients(Unit unit, User requestingUser);

    List<User> getUnitStaffRecipients(List<Unit> units, User requestingUser);

    List<User> getUnitStaffRecipients(Unit unit, User requestingUser);

    List<User> getUnitPatientRecipients(List<Unit> units, User requestingUser);

    List<User> getUnitPatientRecipients(Unit unit, User requestingUser);
}
