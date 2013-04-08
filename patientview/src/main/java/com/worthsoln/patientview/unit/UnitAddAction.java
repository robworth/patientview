package com.worthsoln.patientview.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;

public class UnitAddAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Unit unit = new Unit();
        UnitUtils.buildUnit(unit, form);
        LegacySpringUtils.getUnitManager().save(unit);
        request.setAttribute("unit", unit);

        return LogonUtils.logonChecks(mapping, request);
    }

}
