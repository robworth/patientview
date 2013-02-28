package com.worthsoln.patientview.logon;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.model.Unit;

public class UnitUsersAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");


        Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
        request.setAttribute("unit", unit);

        List unitUsers = LegacySpringUtils.getUserManager().getUnitUsers(unitcode);

        request.setAttribute("unitUsers", unitUsers);

        return LogonUtils.logonChecks(mapping, request);
    }

}
