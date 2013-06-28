/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.test.repository.messaging;

import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.User;
import org.patientview.repository.messaging.MessageDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.repository.BaseDaoTest;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MessageDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private MessageDao messageDao;

    @Test
    public void testAddGetMessage() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Message message = repositoryHelpers.createMessage(conversation, user1, user2, "This is a message", true);

        assertTrue("Invalid id for message", message.getId() > 0);

        Message checkMessage = messageDao.get(message.getId());

        assertNotNull(checkMessage);
        assertEquals("Conversation not stored", checkMessage.getConversation(), message.getConversation());
        assertEquals("Description not stored", checkMessage.getContent(), message.getContent());
    }

    @Test
    public void testDeleteMessage() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Message message = repositoryHelpers.createMessage(conversation, user1, user2, "This is a message", true);

        // now delete and try to pull back
        messageDao.delete(message);

        Message checkMessage = messageDao.get(message.getId());

        assertNull("Message was found after being deleted", checkMessage);
    }

    @Test
    public void testGetMessages() throws Exception {
        /**
         * Create 3 users
         *
         * Then start a conversastion between user 1 and user 2 and another between user 1 and user 3
         *
         * Send a message in each conversation
         *
         * Should get back 2 message for conversation 1
         *
         * Should get back 1 message for conversation 2
         */
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        User user3 = repositoryHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3");

        // create convo between 1 and 2
        Conversation conversation1 = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Message message1 = repositoryHelpers.createMessage(conversation1, user1, user2, "Message in conversation 1",
                true);
        Message message2 = repositoryHelpers.createMessage(conversation1, user1, user2, "2nd Message in conversation 1",
                true);

        // create convo between 2 and 3
        Conversation conversation2 = repositoryHelpers.createConversation("Test subject", user1, user3, true);

        Message message3 = repositoryHelpers.createMessage(conversation2, user1, user3,  "Message in conversation 2",
                true);

        // pull back messages for converastion 1 - should get back 2 not including message 3
        List<Message> checkMessagesConversation1 = messageDao.getMessages(conversation1.getId());

        assertEquals("Wrong number of message for conversation 1", checkMessagesConversation1.size(), 2);
        assertFalse("Message 3 found in conversastion 1", checkMessagesConversation1.contains(message3));

        // pull back messages for converastion 2 - should get back 1 not including message 1 & 2
        List<Message> checkMessagesConversation2 = messageDao.getMessages(conversation2.getId());

        assertEquals("Wrong number of message for conversation 2", checkMessagesConversation2.size(), 1);
        assertFalse("Message 1 found in conversastion 2", checkMessagesConversation2.contains(message1));
        assertFalse("Message 2 found in conversastion 2", checkMessagesConversation2.contains(message2));
    }

    @Test
    public void testGetNumberOfUnreadMessages() throws Exception {
        /**
         * 2 users in a convo
         *
         * User 1 sends 2 messages to user 2
         *
         * User 2 sends 1 message to user 1
         *
         * User 1 should have 1 unread messages for that conversation
         *
         * User 2 should have 2 unread messages for that conversation
         */

        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        // create convo between 1 and 2
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        // 2 messages from user 1
        repositoryHelpers.createMessage(conversation, user1, user2, "Message in conversation 1", true);
        repositoryHelpers.createMessage(conversation, user1, user2, "2nd Message in conversation 1", true);

        // 1 messages from user 2
        repositoryHelpers.createMessage(conversation, user2, user1, "3rd Message in conversation 1", true);

        // now check how many unread messages for each user in convo 1
        Long checkUser1UnreadMessages = messageDao.getNumberOfUnreadMessages(user1.getId(), conversation.getId());
        assertNotNull("Number of messages unread was null", checkUser1UnreadMessages);
        assertTrue("Wrong number of unread messages for user 1", checkUser1UnreadMessages == 1L);

        Long checkUser2UnreadMessages = messageDao.getNumberOfUnreadMessages(user2.getId(), conversation.getId());
        assertNotNull("Number of messages unread was null", checkUser2UnreadMessages);
        assertTrue("Wrong number of unread messages for user 2", checkUser2UnreadMessages == 2L);
    }

    @Test
    public void testGetUnreadMessages() throws Exception {
        /**
         * 2 users in a convo
         *
         * User 1 sends 2 messages to user 2
         *
         * User 2 sends 1 message to user 1
         *
         * User 1 should have 1 unread messages for that conversation
         *
         * User 2 should have 2 unread messages for that conversation
         */

        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        // create convo between 1 and 2
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        // 2 messages from user 1
        repositoryHelpers.createMessage(conversation, user1, user2, "Message in conversation 1", true);
        repositoryHelpers.createMessage(conversation, user1, user2, "2nd Message in conversation 1", true);

        // 1 messages from user 2
        repositoryHelpers.createMessage(conversation, user2, user1, "3rd Message in conversation 1", true);

        // now check how many unread messages for each user in convo 1
        List<Message> checkUser1UnreadMessages = messageDao.getUnreadMessages(user1.getId(), conversation.getId());
        assertNotNull("Number of messages unread was null", checkUser1UnreadMessages);
        assertTrue("Wrong number of unread messages for user 1", checkUser1UnreadMessages.size() == 1L);

        List<Message> checkUser2UnreadMessages = messageDao.getUnreadMessages(user2.getId(), conversation.getId());
        assertNotNull("Number of messages unread was null", checkUser2UnreadMessages);
        assertTrue("Wrong number of unread messages for user 2", checkUser2UnreadMessages.size() == 2L);
    }

    @Test
    public void testGetLatestMessage() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        // create convo between 1 and 2
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        // 2 messages from user 1
        Message message1 = repositoryHelpers.createMessage(conversation, user1, user2, "Message in conversation 1",
                true);

        // this is a bit of a hack but cause the test messages are created at almost the same time MySQL doesnt take
        // into account the milliseconds so save the 2nd message then save again to make the time slightly different
        Message message2 = repositoryHelpers.createMessage(conversation, user1, user2, "2nd Message in conversation 1",
                true);

        message2.setDate(new DateTime(message2.getDate()).plusMinutes(1).toDate());
        messageDao.save(message2);

        Message checkMessage = messageDao.getLatestMessage(conversation.getId());

        assertEquals("Wrong message pull back as latest", checkMessage, message2);
    }
}
