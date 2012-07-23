package com.worthsoln.patientview.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

public class UnitAddAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Unit unit = UnitUtils.buildUnit(form);

        LegacySpringUtils.getUnitManager().save(unit);

        request.setAttribute("unit", unit);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getIdentifier() {
        return "unit";
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
