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

package org.patientview.service.impl;

import org.patientview.patientview.exception.MessagingException;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.GroupMessage;
import org.patientview.patientview.model.User;
import org.patientview.repository.messaging.GroupMessageDao;
import org.patientview.service.GroupMessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(value = "groupMessageManager")
public class GroupMessageManagerImpl implements GroupMessageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupMessageManagerImpl.class);

    @Inject
    private GroupMessageDao groupMessageDao;

    @Override
    public void markGroupMessageAsReadForConversation(User recipient, Conversation conversation)
            throws MessagingException {

        // There was a bug where this block was causing duplicates.  Possibly due to concurrency problems caused by
        // two quick reads of conversations. A unique constraint has been added to the db.
        // Just catch an exceptions such as non-unique errors for now...
        try {
            if (groupMessageDao.get(recipient.getId(), conversation.getId()) == null) {
                GroupMessage groupMessage = new GroupMessage();
                groupMessage.setConversation(conversation);
                groupMessage.setRecipient(recipient);

                groupMessageDao.save(groupMessage);
            }
        } catch (Exception e) {
            throw new MessagingException(e.getMessage());
        }
    }

    @Override
    public GroupMessage get(Long recipientId, Conversation conversation) {
        return groupMessageDao.get(recipientId, conversation.getId());
    }
}
