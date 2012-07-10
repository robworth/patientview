package com.worthsoln.patientview.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.user.NhsnoUnitcode;

public class UnitUserEditInputAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        User user = (User) HibernateUtil.getPersistentObject(User.class, username);
        NhsnoUnitcode unitcodeThing = new NhsnoUnitcode("", unitcode);

        request.getSession().setAttribute("unitUser", user);
        request.getSession().setAttribute("unitcodeThing", unitcodeThing);

        return LogonUtils.logonChecks(mapping, request);
    }
}
