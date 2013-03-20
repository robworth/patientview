package com.worthsoln.patientview.messaging;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SendMessageAction extends BaseAction {

    private List<String> errors = new ArrayList<String>();

    protected ObjectMapper objectMapper = new ObjectMapper();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        User user = UserUtils.retrieveUser(request);

        if (validateMessage(dynaForm)) {
            try {
                Message message = getMessageManager().createMessage(
                    ((DynaActionForm) form).getString(Messaging.CONTENT_PARAM),
                        user,
                        getRecipient(dynaForm)
                );

                request.setAttribute(Messaging.MESSAGE_PARAM, objectMapper.writeValueAsString(message));
            } catch (Exception e) {
                errors.add("Error sending message");
            }
        }

        if (errors.isEmpty()) {
            return mapping.findForward(SUCCESS);
        }

        request.setAttribute(Messaging.ERRORS_PARAM, objectMapper.writeValueAsString(errors));

        return mapping.findForward(INPUT);
    }

    private boolean validateMessage(DynaActionForm form) {
        errors.clear();

        if (getRecipient(form) == null) {
            errors.add("Invalid recipient");
        }

        if (!StringUtils.hasText((String) form.get(Messaging.CONTENT_PARAM))) {
            errors.add("Please enter a message");
        }

        return errors.isEmpty();
    }

    private User getRecipient(DynaActionForm form) {
        User user = null;

        if (form.get(Messaging.RECIPIENT_ID_PARAM) != null || ((Long) form.get(Messaging.RECIPIENT_ID_PARAM)) > 0) {
            user = getUserManager().get(((Long) form.get(Messaging.RECIPIENT_ID_PARAM)));
        }

        return user;
    }
}
