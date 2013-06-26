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

package org.patientview.patientview.messaging;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.action.BaseAction;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConversationAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        /**
         * If an admin is logged in as patient, the user below would be the patient.
         * Whereas {@link LegacySpringUtils.getUserManager().getLoggedInUser} would return the admin.
         */
        User user = UserUtils.retrieveUser(request);
        User loggedInUser = getUserManager().getLoggedInUser();

        // add the conversation and messages to the page
        Conversation conversation = getMessageManager().getConversationForUser(getConversationId(request),
                user.getId());

        if (conversation == null) {
            return mapping.findForward(ERROR);
        }

        request.setAttribute(Messaging.CONVERSATION_PARAM, conversation);
        request.setAttribute(Messaging.MESSAGES_PARAM, getMessageManager().getMessages(conversation.getId()));

        // single message the type is null
        if (conversation.getType() == null) {
            getMessageManager().markMessagesAsReadForConversation(loggedInUser.getId(), conversation.getId());
        } else {
            getGroupMessageManager().markGroupMessageAsReadForConversation(loggedInUser, conversation);
            request.setAttribute(Messaging.IS_BULK_MESSAGE_PARAM, true);
            String userType = "";
            if (GroupEnum.ALL_ADMINS.equals(conversation.getGroupEnum())) {
                userType = "all admins";
            } else if (GroupEnum.ALL_PATIENTS.equals(conversation.getGroupEnum())) {
                userType = "all patients";
            } else if (GroupEnum.ALL_STAFF.equals(conversation.getGroupEnum())) {
                userType = "all staff";
            } else {
                userType = "";
            }

            request.setAttribute(Messaging.BULK_MESSAGE_RECIPIENT, userType);
            request.setAttribute(Messaging.RECIPIENT_UNIT_PARAM,
                    getMessageManager().getMessages(conversation.getId()).get(0).getUnit().getName());

        }

        request.setAttribute(Messaging.CONTENT_PARAM, "");

        boolean readerIsTheRecipient = user.getId().equals(loggedInUser.getId());
        if (!readerIsTheRecipient) {
            request.setAttribute(Messaging.IS_READER_THE_RECIPIENT, "false");
        }

        return mapping.findForward(SUCCESS);
    }

    private Long getConversationId(HttpServletRequest request) {
        try {
            return Long.parseLong(request.getParameter(Messaging.CONVERSATION_ID_PARAM));
        } catch (Exception e) {
            return null;
        }
    }
}
