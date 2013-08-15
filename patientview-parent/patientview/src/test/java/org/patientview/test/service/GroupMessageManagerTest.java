package org.patientview.test.service;

import org.patientview.patientview.model.*;
import org.patientview.repository.messaging.GroupMessageDao;
import org.patientview.service.GroupMessageManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 *      These tests require an admin adminUser to be logged into a specialty
 */
public class GroupMessageManagerTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private GroupMessageDao groupMessageDao;

    @Inject
    private GroupMessageManager groupMessageManager;


    @Test
    public void testGetGroupMessage() throws Exception {
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);

        GroupMessage message = new GroupMessage();
        message.setConversation(conversation);
        message.setRecipient(user2);
        groupMessageDao.save(message);

        assertTrue("Invalid id for groupmessage", message.getId() > 0);

        GroupMessage checkMessage = groupMessageDao.get(user2.getId(), conversation.getId());

        assertNotNull(checkMessage);
        assertEquals("Conversation not stored", checkMessage.getConversation(), message.getConversation());
        assertEquals("Description not stored", checkMessage.getRecipient(), message.getRecipient());
    }

    @Test
    public void testMarkGroupMessageAsReadForConversation() throws Exception {
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);

        groupMessageManager.markGroupMessageAsReadForConversation(user2, conversation);
        GroupMessage checkMessage = groupMessageManager.get(user2.getId(), conversation);

        assertNotNull(checkMessage);
        assertEquals("Conversation not stored", checkMessage.getConversation(), conversation);
        assertEquals("Recipient not stored", checkMessage.getRecipient(), user2);
    }
}
