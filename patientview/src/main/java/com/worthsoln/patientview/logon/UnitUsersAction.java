package com.worthsoln.patientview.logon;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.HibernateUtil;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.unit.Unit;

public class UnitUsersAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        DatabaseDAO dao = getDao(request);
        HibernateUtil.retrievePersistentObjectAndAddToRequestWithIdParameter(request, Unit.class, unitcode, "unit");
        UnitUsersDao unitUserDao = new UnitUsersDao(unitcode);
        List unitUsers = dao.retrieveList(unitUserDao);

        request.setAttribute("unitUsers", unitUsers);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
