package com.worthsoln.patientview.messaging;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
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
            } else {}

            request.setAttribute(Messaging.BULK_MESSAGE_RECIPIENT, userType);
            request.setAttribute(Messaging.RECIPIENT_UNIT_PARAM, getMessageManager().getMessages(conversation.getId()).get(0).getUnit().getName());


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
