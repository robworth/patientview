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
