package com.worthsoln.patientview.logon;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.Unit;

public class UnitPatientsAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        unitcode = (unitcode == null) ? "" : unitcode;
        String nhsno = BeanUtils.getProperty(form, "nhsno");
        nhsno = (nhsno == null) ? "" : nhsno;
        String name = BeanUtils.getProperty(form, "name");
        name = (name == null) ? "" : name;
        boolean showgps = "true".equals(BeanUtils.getProperty(form, "showgps"));
        DatabaseDAO dao = getDao(request);
        if (!"".equals(unitcode)) {
            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
            request.setAttribute("unit", unit);
        }
        UnitPatientsWithTreatmentDao patientDao = new UnitPatientsWithTreatmentDao(unitcode, nhsno, name, showgps);
        List patients = dao.retrieveList(patientDao);
        request.setAttribute("patients", patients);
        return LogonUtils.logonChecks(mapping, request);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
