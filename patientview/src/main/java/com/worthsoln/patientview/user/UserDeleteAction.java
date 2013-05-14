package com.worthsoln.patientview.user;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String nhsno = BeanUtils.getProperty(form, "nhsno");

        PatientLogon patient = new PatientLogon();
        Unit unit = UnitUtils.retrieveUnit(unitcode);
        patient.setUsername(username);
        patient.setNhsno(nhsno);
        User user = LegacySpringUtils.getUserManager().get(username);
        patient.setName(user.getName());

        LegacySpringUtils.getUserManager().delete(username, unitcode);

        AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PATIENT_DELETE, username,
                nhsno, unitcode, "");
        String mappingToFind = "success";

        request.setAttribute("units", LegacySpringUtils.getUnitManager().getAll(false));
        request.setAttribute("patient", patient);
        request.setAttribute("unit", unit);

        return mapping.findForward(mappingToFind);
    }
}
