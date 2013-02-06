package com.worthsoln.patientview.join;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.service.JoinRequestManager;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class JoinRequestSubmitAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception {

        String firstName = BeanUtils.getProperty(form, "firstName");
        String lastName = BeanUtils.getProperty(form, "lastName");
        Date dateOfBirth = convertFormDateString("dateOfBirth", form);
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String email = BeanUtils.getProperty(form, "email");

        JoinRequest joinRequest = new JoinRequest(firstName, lastName, dateOfBirth, unitcode, email);
        LegacySpringUtils.getJoinRequestManager().save(joinRequest);

        return LogonUtils.logonChecks(mapping, request);
    }

    // todo create a BaseAction and move this there?
    private Date convertFormDateString(String formProperty, ActionForm actionForm) throws Exception {
        String dateString = BeanUtils.getProperty(actionForm, formProperty);

        if (dateString != null && dateString.length() > 0) {
            try {
                return Ibd.DATE_FORMAT.parse(dateString);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

}
