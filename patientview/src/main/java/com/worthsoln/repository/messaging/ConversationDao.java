package com.worthsoln.repository.messaging;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.enums.GroupEnum;

import java.util.List;

public interface ConversationDao {

    /**
     * Get specific conversation by ID
     * @param id Long
     * @return Conversation
     */
    Conversation get(Long id);

    /**
     * Get all conversations a user is part of
     * @param participantId Long
     * @return List<Conversation>
     */
    List<Conversation> getConversations(Long participantId);

    List<Conversation> getConversations(Long participantId, GroupEnum groupEnum);

    void save(Conversation conversation);

    void delete(Conversation conversation);
}
