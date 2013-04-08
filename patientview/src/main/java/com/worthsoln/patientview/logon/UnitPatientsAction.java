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

public class UnitPatientsAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        unitcode = (unitcode == null) ? "" : unitcode;
        String nhsno = BeanUtils.getProperty(form, "nhsno");
        nhsno = (nhsno == null) ? "" : nhsno;
        String name = BeanUtils.getProperty(form, "name");
        name = (name == null) ? "" : name;
        boolean showgps = "true".equals(BeanUtils.getProperty(form, "showgps"));

        if (!"".equals(unitcode)) {
            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
            request.setAttribute("unit", unit);
        }

        List patients
                = LegacySpringUtils.getPatientManager().getUnitPatientsWithTreatment(unitcode, nhsno, name, showgps);

        request.setAttribute("patients", patients);

        return LogonUtils.logonChecks(mapping, request);
    }

}
