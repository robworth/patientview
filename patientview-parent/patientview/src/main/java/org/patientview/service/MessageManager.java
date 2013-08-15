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

package org.patientview.service;

import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.MessageRecipient;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.Unit;

import javax.servlet.ServletContext;
import java.util.List;

public interface MessageManager {

    Conversation getConversation(Long conversationId);

    /**
     * This will get the conversation but applied to the current user
     * So will include number of number of unread messages etc and other users will be set
     * @param conversationId Long
     * @param participantId Long
     * @return Conversation
     */
    Conversation getConversationForUser(Long conversationId, Long participantId);

    List<Conversation> getConversations(Long participantId);

    void deleteConversation(Long conversationId);

    void deleteConversation(Conversation conversation);

    List<Message> getMessages(Long conversationId);

    Message createMessage(ServletContext context, String subject, String content, User sender, User recipient)
            throws Exception;

    Message createGroupMessage(ServletContext context, String subject, String content, User sender,
                               String groupName, String type, Unit unit)
            throws Exception;

    Message replyToMessage(ServletContext context, String content, Long conversationId, User sender) throws Exception;

    int getTotalNumberUnreadMessages(Long recipientId);

    /**
     * Marks the conversation as read only if logged in user is the recipient
     */
    void markMessagesAsReadForConversation(Long loggedInUserId, Long conversationId);

    List<MessageRecipient> getUnitAdminRecipients(List<Unit> units, User requestingUser);

    List<User> getUnitAdminRecipients(Unit unit, User requestingUser);

    List<MessageRecipient> getUnitStaffRecipients(List<Unit> units, User requestingUser);

    List<User> getUnitStaffRecipients(Unit unit, User requestingUser);

    List<MessageRecipient> getUnitPatientRecipients(List<Unit> units, User requestingUser);

    List<User> getUnitPatientRecipients(Unit unit, User requestingUser);

    List<Unit> getMessagingEnabledUnitsForLoggedInUser();
}
