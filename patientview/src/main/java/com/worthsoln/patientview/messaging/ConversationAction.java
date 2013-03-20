package com.worthsoln.patientview.messaging;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConversationAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);

        // add the conversation and messages to the page
        Conversation conversation = getMessageManager().getConversationForUser(getConversationId(request),
                user.getId());

        if (conversation == null) {
            return mapping.findForward(ERROR);
        }

        request.setAttribute(Messaging.CONVERSATION_PARAM, conversation);
        request.setAttribute(Messaging.MESSAGES_PARAM, getMessageManager().getMessages(conversation.getId()));

        // as the user has requested to view this page we can assume they have read the messages
        getMessageManager().markMessagesAsReadForConversation(UserUtils.retrieveUser(request).getId(),
                conversation.getId());

        // set the message form up on the page
        DynaActionForm dynaActionForm = (DynaActionForm) form;

        dynaActionForm.set(Messaging.CONTENT_PARAM, "");
        dynaActionForm.set(Messaging.RECIPIENT_ID_PARAM, conversation.getOtherUser().getId());

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
