package com.worthsoln.ibd.action.mydetails;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.PatientDetailsAction;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.service.ibd.IbdManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDetailsAction extends PatientDetailsAction {

    /**
     * This is a sub class just so that we can add the myIbd stuff into the request without having to
     * write all the myDetails code and JSPs again
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        MyIbd myIbd = getIbdManager().getMyIbd(UserUtils.retrieveUser(request));

        if (myIbd != null && myIbd.getDiagnosis() != null) {
            request.getSession().setAttribute(Ibd.MY_IBD_DIAGNOSIS_PARAM, myIbd.getDiagnosis().getName());
        }

        return super.execute(mapping, form, request, response);
    }

    protected IbdManager getIbdManager() {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(
                servlet.getServletContext());

        return webApplicationContext.getBean(IbdManager.class);
    }
}
