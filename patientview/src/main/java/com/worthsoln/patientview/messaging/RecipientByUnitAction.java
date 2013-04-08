package com.worthsoln.patientview.messaging;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecipientByUnitAction extends BaseAction {

    protected ObjectMapper objectMapper = new ObjectMapper();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = UserUtils.retrieveUser(request);

        // only do this if its a superadmin
        if (getSecurityUserManager().isRolePresent("superadmin")) {
            String unitCode = getUnitCode(request);

            if (StringUtils.hasText(unitCode)) {
                Unit unit = getUnitManager().get(unitCode);

                if (unit != null) {
                    request.setAttribute(Messaging.UNIT_ADMIN_RECIPIENTS_PARAM,
                            getMessageManager().getUnitAdminRecipients(unit, user));
                    request.setAttribute(Messaging.UNIT_STAFF_RECIPIENTS_PARAM,
                            getMessageManager().getUnitStaffRecipients(unit, user));
                    request.setAttribute(Messaging.UNIT_PATIENT_RECIPIENTS_PARAM,
                            getMessageManager().getUnitPatientRecipients(unit, user));
                }
            }
        }

        return mapping.findForward(SUCCESS);
    }

    private String getUnitCode(HttpServletRequest request) {
        try {
            return request.getParameter(Messaging.UNIT_CODE_PARAM);
        } catch (Exception e) {
            return null;
        }
    }
}
