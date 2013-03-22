package com.worthsoln.test.service;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.User;
import com.worthsoln.service.MessageManager;
import com.worthsoln.test.helpers.SecurityHelpers;
import com.worthsoln.test.helpers.ServiceHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MessageManagerTest extends BaseServiceTest {

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private MessageManager messageManager;

    private User user;
    private Specialty specialty;

    @Before
    public void setupSystem() {
        // create an admin adminUser and specialty and log them in
        user = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1", "Test 1");
        specialty = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        serviceHelpers.createSpecialtyUserRole(specialty, user, "unitadmin");

        securityHelpers.loginAsUser(user.getUsername(), specialty);
    }

    @Test
    public void testGetConversation() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation(user, user2, true);

        assertTrue("Invalid id for message", conversation.getId() > 0);

        Conversation checkConversation = messageManager.getConversation(conversation.getId());
        assertNotNull("Conversation nout found", checkConversation);
    }

    @Test
    public void testDeleteConversation() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation(user, user2, true);

        // now delete and try to pull back
        messageManager.deleteConversation(conversation);

        Conversation checkConversation = messageManager.getConversation(conversation.getId());

        assertNull("Conversation was found after being deleted", checkConversation);
    }

    @Test
    public void testDeleteConversationById() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation(user, user2, true);

        // now delete and try to pull back
        messageManager.deleteConversation(conversation.getId());

        Conversation checkConversation = messageManager.getConversation(conversation.getId());

        assertNull("Conversation was found after being deleted", checkConversation);
    }

    @Test
    public void testCreateMessageForNewConversation() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Message message = messageManager.createMessage("This is my first message", user, user2);

        assertTrue("Invalid id for message", message.getId() > 0);

        // now try and pull back conversations for both users - both should have 1 conversation
        List<Conversation> checkUser1Conversations = messageManager.getConversations(user.getId());
        assertEquals("Wrong number of conversations for user 1", checkUser1Conversations.size(), 1);

        List<Conversation> checkUser2Conversations = messageManager.getConversations(user2.getId());
        assertEquals("Wrong number of conversations for user 2", checkUser2Conversations.size(), 1);

        // now pull back the messages for a conversation to see if the message was actually saved
        List<Message> checkMessages = messageManager.getMessages(checkUser1Conversations.get(0).getId());
        assertEquals("Wrong number of messages for conversation", checkMessages.size(), 1);
    }

    @Test
    public void testCreateMessageForExistingConversation() throws Exception {
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        Conversation conversation = serviceHelpers.createConversation(user, user2, true);

        Message message = messageManager.createMessage("This is my first message", user, user2);

        // the conversatino assigned to the message should be the same as the one created above
        assertEquals("Wrong conversation stored", conversation, message.getConversation());
    }

    @Test
    public void testMarkMessagesAsReadForConversation() throws Exception {
        /**
         * Send a message from user 1 to user 2
         *
         * Check how many unread messages they have
         *
         * Then mark all messages as read for user 2 in the conversation
         *
         * Then check how many unread messages they have again
         */
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");

        serviceHelpers.createConversation(user, user2, true);

        messageManager.createMessage("This is my first message", user, user2);

        // now pull abck conversation for user 2
        List<Conversation> checkUser2Conversations = messageManager.getConversations(user2.getId());

        // check number of unread messages for only convo
        assertEquals("Wrong number of unread messages for conversation",
                checkUser2Conversations.get(0).getNumberUnread(), 1);

        // now mark as unread
        messageManager.markMessagesAsReadForConversation(user2.getId(), checkUser2Conversations.get(0).getId());

        // now pull back again
        List<Conversation> checkUser2ConversationsAgain = messageManager.getConversations(user2.getId());

        // check number of unread messages again
        assertEquals("Wrong number of unread messages for conversation",
                checkUser2ConversationsAgain.get(0).getNumberUnread(), 0);
    }

    @Test
    public void testGetTotalNumberUnreadMessages() throws Exception {
        /**
         * Create 3 users
         *
         * Conversation between user 1 and 2
         * Conversation between user 1 and 3
         *
         * Then send messages in conversation 1 from user 2 to 1
         *
         * and
         *
         * messages in conversation 1 from user 3 to 1
         *
         * User 1 should then have 2 unread messages across conversations
          */
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2", "Test 2");
        User user3 = serviceHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3", "Test 3");

        // first convo with message from 2 to 1
        serviceHelpers.createConversation(user1, user2, true);
        messageManager.createMessage("This is my first message", user2, user1);

        // second convo with message from 3 to 1
        serviceHelpers.createConversation(user1, user3, true);
        messageManager.createMessage("This is my first message", user3, user1);

        // now pull back and check unread messages for user 1
        int checkNumberUnreadMessages = messageManager.getTotalNumberUnreadMessages(user1.getId());
        assertEquals("Wrong number of unread messages", checkNumberUnreadMessages, 2);
    }
}
