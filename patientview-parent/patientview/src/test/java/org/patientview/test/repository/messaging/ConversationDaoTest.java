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

import org.patientview.patientview.model.User;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.repository.messaging.ConversationDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.repository.BaseDaoTest;
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
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

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
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

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

        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        User user3 = repositoryHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3");

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

    @Test
    public void testGetConversationList() throws Exception {

        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        User user3 = repositoryHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3");
        User user4 = repositoryHelpers.createUser("test 4", "tester3@test.com", "test4", "Test 4");

        Conversation conversation1 = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Conversation conversation2 = repositoryHelpers.createConversation("Test subject", user1, user3, true);

        Conversation conversation3 = repositoryHelpers.createConversation("Test subject", user2, user3, true);

        Conversation conversation4 = repositoryHelpers.createConversation("Test subject", user3, user4, true);

        conversation3.setType("BULK");
        conversation3.setGroupEnum(GroupEnum.ALL_PATIENTS);
        conversationDao.save(conversation3);

        conversation4.setType("BULK");
        conversation4.setGroupEnum(GroupEnum.ALL_STAFF);
        conversationDao.save(conversation4);

        List<Conversation> checkUser4Conversations = conversationDao.getConversations(user1.getId(), GroupEnum.ALL_PATIENTS);

        assertEquals("Wrong number of conversations for user 1", 3, checkUser4Conversations.size());
        assertTrue("Conversation not found for user 1", checkUser4Conversations.contains(conversation1));
        assertTrue("Conversation not found for user 1", checkUser4Conversations.contains(conversation2));
        assertTrue("Conversation not found with GroupEnum ALL_PATIENTS", checkUser4Conversations.contains(conversation3));
        assertFalse("Conversation found with GroupEnum All_STAFF", checkUser4Conversations.contains(conversation4));

    }
}
