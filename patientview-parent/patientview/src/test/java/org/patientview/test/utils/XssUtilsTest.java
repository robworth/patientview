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

package org.patientview.test.utils;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.User;
import org.patientview.repository.messaging.ConversationDao;
import org.patientview.repository.messaging.MessageDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.service.BaseServiceTest;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class XssUtilsTest extends BaseServiceTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private ConversationDao conversationDao;

    @Inject
    private MessageDao messageDao;

    @Test
    public void testCleanObjectForXss() {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");

        Conversation conversation = new Conversation();

        conversation.setParticipant1(user1);
        conversation.setParticipant2(user2);
        conversation.setSubject("This is test message");
        conversationDao.save(conversation);

        Message message1 = new Message();

        message1.setConversation(conversation);
        message1.setSender(user1);
        message1.setRecipient(user2);
        message1.setContent("<script>alert('FAILED')</script>");
        messageDao.save(message1);

        Message message2 = new Message();

        message2.setConversation(conversation);
        message2.setSender(user1);
        message2.setRecipient(user2);
        message2.setContent("<IMG SRC=\"javascript:alert('XSS');\">");
        messageDao.save(message2);

        Message message3 = new Message();

        message3.setConversation(conversation);
        message3.setSender(user1);
        message3.setRecipient(user2);
        message3.setContent("<SCRIPT SRC=http://ha.ckers.org/xss.js?< B >");
        messageDao.save(message3);

        Message message4 = new Message();

        message4.setConversation(conversation);
        message4.setSender(user1);
        message4.setRecipient(user2);
        message4.setContent("<IMG SRC=&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116;&#58;&#97;" +
                "&#108;&#101;&#114;&#116;&#40;\n" +
                "&#39;&#88;&#83;&#83;&#39;&#41;>");
        messageDao.save(message4);

        Message message5 = new Message();

        message5.setConversation(conversation);
        message5.setSender(user1);
        message5.setRecipient(user2);
        message5.setContent("<IMG SRC=&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C" +
                "&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29>");
        messageDao.save(message5);


        Message checkMessage1 = messageDao.get(message1.getId());
        assertTrue("Invalid id for message", message1.getId() > 0);
        assertNotNull(checkMessage1);
        assertEquals("Message content not clean", StringUtils.EMPTY, checkMessage1.getContent());

        Message checkMessage2 = messageDao.get(message2.getId());
        assertTrue("Invalid id for message", message2.getId() > 0);
        assertNotNull(checkMessage2);
        assertEquals("Message content not clean", StringUtils.EMPTY, checkMessage2.getContent());

        Message checkMessage3 = messageDao.get(message3.getId());
        assertTrue("Invalid id for message", message3.getId() > 0);
        assertNotNull(checkMessage3);
        assertEquals("Message content not clean", StringUtils.EMPTY, checkMessage3.getContent());

        Message checkMessage4 = messageDao.get(message4.getId());
        assertTrue("Invalid id for message", message4.getId() > 0);
        assertNotNull(checkMessage4);
        assertEquals("Message content not clean", StringUtils.EMPTY, checkMessage4.getContent());

        Message checkMessage5 = messageDao.get(message5.getId());
        assertTrue("Invalid id for message", message5.getId() > 0);
        assertNotNull(checkMessage5);
        assertEquals("Message content not clean", StringUtils.EMPTY, checkMessage5.getContent());
    }
}
