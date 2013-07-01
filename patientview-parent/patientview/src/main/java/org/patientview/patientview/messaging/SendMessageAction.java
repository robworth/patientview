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

import org.patientview.ibd.action.BaseAction;
import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.patientview.user.UserUtils;
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

    private ObjectMapper objectMapper = new ObjectMapper();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        User user = UserUtils.retrieveUser(request);

        if (validateMessage(dynaForm)) {
            try {
                // if there is a conversation id then its a reply else its new
                Long conversationId = getConversationId(dynaForm);

                Message message;

                if (conversationId != null) {
                    message = getMessageManager().replyToMessage(
                            request.getSession().getServletContext(),
                            dynaForm.getString(Messaging.CONTENT_PARAM),
                            conversationId,
                            user
                    );
                } else {
                    if (dynaForm.getString(Messaging.RECIPIENT_ID_PARAM) != null
                            && dynaForm.getString(Messaging.RECIPIENT_ID_PARAM).matches("[0-9]*")) {
                        message = getMessageManager().createMessage(
                                request.getSession().getServletContext(),
                                dynaForm.getString(Messaging.SUBJECT_PARAM),
                                dynaForm.getString(Messaging.CONTENT_PARAM),
                                user,
                                getRecipient(dynaForm)
                        );
                    } else {
                        // add the bulk message for special group
                        message = getMessageManager().createGroupMessage(
                                request.getSession().getServletContext(),
                                dynaForm.getString(Messaging.SUBJECT_PARAM),
                                dynaForm.getString(Messaging.CONTENT_PARAM),
                                user,
                                dynaForm.getString(Messaging.RECIPIENT_ID_PARAM),
                                "BULK",
                                getUnit(dynaForm)
                        );
                    }
                }

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

        // if there isnt a conversation id we can assume new convo so we need a subject and a recipient
        // if there is a convo then we can work out the other person in the convo from the user in the session
        if (getConversationId(form) == null) {

            if (form.get(Messaging.RECIPIENT_ID_PARAM) != null
                    && form.get(Messaging.RECIPIENT_ID_PARAM).toString().matches("[0-9]*")) {
                if (getRecipient(form) == null) {
                    errors.add("Invalid recipient");
                }
            }

            if (StringUtils.hasText(form.get(Messaging.UNIT_CODE_PARAM).toString())) {
                if (getUnit(form) == null) {
                    errors.add("Invalid unit");
                }
            }

            if (!StringUtils.hasText((String) form.get(Messaging.SUBJECT_PARAM))) {
                errors.add("Please enter a subject");
            }
        }

        if (!StringUtils.hasText((String) form.get(Messaging.CONTENT_PARAM))) {
            errors.add("Please enter a message");
        }

        return errors.isEmpty();
    }

    private Long getConversationId(DynaActionForm form) {
        Long conversationId = null;

        if (form.get(Messaging.CONVERSATION_ID_PARAM) != null
                && ((Long) form.get(Messaging.CONVERSATION_ID_PARAM)) > 0) {
            conversationId = ((Long) form.get(Messaging.CONVERSATION_ID_PARAM));
        }

        return conversationId;
    }

    private User getRecipient(DynaActionForm form) {
        User user = null;

        if (form.get(Messaging.RECIPIENT_ID_PARAM) != null
                && (Long.parseLong(form.get(Messaging.RECIPIENT_ID_PARAM).toString())) > 0) {
            user = getUserManager().get(Long.parseLong(form.get(Messaging.RECIPIENT_ID_PARAM).toString()));
        }

        return user;
    }

    private Unit getUnit(DynaActionForm form) {
        Unit unit = null;

        if (form.get(Messaging.UNIT_CODE_PARAM) != null) {
            unit = getUnitManager().get(form.get(Messaging.UNIT_CODE_PARAM).toString());
        }

        return unit;
    }

}
