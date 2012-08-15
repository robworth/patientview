package com.worthsoln.ibd.action.symptoms.crohns;

import com.worthsoln.ibd.action.BaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrohnsEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        request.getSession().setAttribute(ABDOMINAL_PAIN_LIST_PROPERTY, getAbdominalPainList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(CROHNS_COMPLICATION_LIST_PROPERTY, getCrohnsComplicationList());
        request.getSession().setAttribute(MASS_IN_TUMMY_LIST_PROPERTY, getMassInTummy());
        request.getSession().setAttribute(OPEN_BOWEL_LIST_PROPERTY, getOpenBowelList());

        return mapping.findForward(SUCCESS);
    }
}
