package com.worthsoln.patientview.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class UnitEditAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");

        Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);

        request.getSession().setAttribute("unit", unit);

        return LogonUtils.logonChecks(mapping, request);
    }

}
