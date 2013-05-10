package com.worthsoln.patientview.logon;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.model.Unit;

public class UnitUserEditAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String password = BeanUtils.getProperty(form, "password");
        String name = BeanUtils.getProperty(form, "name");
        String email = BeanUtils.getProperty(form, "email");
        boolean emailverified = "true".equals(BeanUtils.getProperty(form, "emailverified"));
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String role = BeanUtils.getProperty(form, "role");
        boolean firstlogon = "true".equals(BeanUtils.getProperty(form, "firstlogon"));

        UnitAdmin unitAdmin = new UnitAdmin(username, password, name, email, emailverified, role, firstlogon);

        LegacySpringUtils.getUserManager().saveUserFromUnitAdmin(unitAdmin, unitcode);

        Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
        request.setAttribute("unit", unit);

        List unitUsers = LegacySpringUtils.getUnitManager().getUnitUsers(unitcode);

        request.setAttribute("unitUsers", unitUsers);

        return LogonUtils.logonChecks(mapping, request);
    }

}
