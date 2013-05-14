package com.worthsoln.test.repository.messaging;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.repository.messaging.ConversationDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ConversationDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private ConversationDao conversationDao;

    @Test
    public void testAddGetConversation() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        assertTrue("Invalid id for conversation", conversation.getId() > 0);

        Conversation checkConversation = conversationDao.get(conversation.getId());

        assertNotNull(checkConversation);
        assertEquals("From user not stored", checkConversation.getParticipant1(), conversation.getParticipant1());
        assertEquals("To user not stored", checkConversation.getParticipant2(), conversation.getParticipant2());
        assertEquals("Started not stored", checkConversation.getStarted(), conversation.getStarted());
        assertEquals("Subject not stored", checkConversation.getSubject(), conversation.getSubject());
    }

    @Test
    public void testDeleteConversation() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        // now delete and try to pull back
        conversationDao.delete(conversation);

        Conversation checkConversation = conversationDao.get(conversation.getId());

        assertNull("Conversation was found after being deleted", checkConversation);
    }

    @Test
    public void testGetConversations() throws Exception {
        /**
         * Create 3 users
         *
         * Then start a conversastion between user 1 and user 2 and another between user 1 and user 3
         *
         * Should get back 2 conversations for user 1
         *
         * Should get back 1 conversation for user 2 and 3
          */

        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");
        User user3 = repositoryHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3", "Test 3");

        // create convo between 1 and 2
        Conversation conversation1 = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        // create convo between 2 and 3
        Conversation conversation2 = repositoryHelpers.createConversation("Test subject", user1, user3, true);

        // pull back convos for user 1 - shuold get 2
        List<Conversation> checkUser1Conversations = conversationDao.getConversations(user1.getId());

        assertEquals("Wrong number of conversations for user 1", checkUser1Conversations.size(), 2);

        // pull back convos for user 2 - shuold get back 1
        List<Conversation> checkUser2Conversations = conversationDao.getConversations(user2.getId());

        assertEquals("Wrong number of conversations for user 2", checkUser2Conversations.size(), 1);
        assertFalse("Wrong conversation found for user 2", checkUser2Conversations.contains(conversation2));

        // pull back convos for user 3 - shuold get back 1
        List<Conversation> checkUser3Conversations = conversationDao.getConversations(user3.getId());

        assertEquals("Wrong number of conversations for user 3", checkUser3Conversations.size(), 1);
        assertFalse("Wrong conversation found for user 3", checkUser3Conversations.contains(conversation1));
    }
}
