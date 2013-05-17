package com.worthsoln.repository.messaging;

import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.enums.GroupEnum;

import java.util.List;

public interface MessageDao {

    Message get(Long id);

    List<Message> getMessages(Long conversationId);

    List<Message> getUnreadMessages(Long recipientId, Long conversationId);

    Long getNumberOfUnreadMessages(Long recipientId, Long conversationId);

    Message getLatestMessage(Long conversationId);

    void save(Message message);

    void delete(Message message);
}