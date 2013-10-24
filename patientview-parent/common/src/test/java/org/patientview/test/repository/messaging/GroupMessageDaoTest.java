package org.patientview.test.repository.messaging;

import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.GroupMessage;
import org.patientview.patientview.model.User;
import org.patientview.repository.messaging.GroupMessageDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class GroupMessageDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private GroupMessageDao groupMessageDao;

    @Test
    public void testAddGetMessage() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

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

}
