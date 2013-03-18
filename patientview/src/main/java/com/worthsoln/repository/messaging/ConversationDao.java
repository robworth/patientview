package com.worthsoln.repository.messaging;

import com.worthsoln.patientview.model.Conversation;

import java.util.List;

public interface ConversationDao {

    /**
     * Get specific conversation by ID
     * @param id Long
     * @return Conversation
     */
    Conversation get(Long id);

    /**
     * Find a conversation between 2 users, there should only ever be one in the DB not including deleted ones
     * @param participant1Id Long
     * @param participant2Id Long
     * @return Conversation
     */
    Conversation getConversationBetweenUsers(Long participant1Id, Long participant2Id);

    /**
     * Get all conversations a user is part of
     * @param participantId Long
     * @return List<Conversation>
     */
    List<Conversation> getConversations(Long participantId);

    void save(Conversation conversation);

    void delete(Conversation conversation);
}
