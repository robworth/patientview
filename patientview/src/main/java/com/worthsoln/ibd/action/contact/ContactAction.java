package com.worthsoln.ibd.action.contact;

import com.worthsoln.ibd.action.BaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactAction extends BaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm dynaActionForm = (DynaActionForm) form;

        dynaActionForm.set("message", "");
        dynaActionForm.set("email", "");

        return mapping.findForward(SUCCESS);
    }
}
