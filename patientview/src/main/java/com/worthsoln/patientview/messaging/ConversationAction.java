package com.worthsoln.patientview.messaging;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Conversation;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConversationAction extends BaseAction {

    private static final String CONVERSATION_ID_PARAM = "id";
    private static final String CONVERSATION_PARAM = "conversation";
    private static final String MESSAGES_PARAM = "messages";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        Conversation conversation = getMessageManager().getConversation(getConversationId(request));

        if (conversation == null) {
            return mapping.findForward(ERROR);
        }

        request.setAttribute(CONVERSATION_PARAM, conversation);
        request.setAttribute(MESSAGES_PARAM, getMessageManager().getMessages(conversation.getId()));

        return mapping.findForward(SUCCESS);
    }

    private Long getConversationId(HttpServletRequest request) {
        try {
            return Long.parseLong(request.getParameter(CONVERSATION_ID_PARAM));
        } catch (Exception e) {
            return null;
        }
    }
}
