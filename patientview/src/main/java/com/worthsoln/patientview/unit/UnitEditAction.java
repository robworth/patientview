package com.worthsoln.patientview.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

public class UnitEditAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");

        HibernateUtil.retrievePersistentObjectAndAddToSessionWithIdParameter(request, Unit.class, unitcode, "unit");

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "unit";
    }
}
