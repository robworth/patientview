package com.worthsoln.ibd.action.colitis;

import com.worthsoln.ibd.action.BaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ColitisEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        request.getSession().setAttribute(STOOLS_DAY_LIST_PROPERTY, getStoolsDayList());
        request.getSession().setAttribute(STOOLS_NIGHT_LIST_PROPERTY, getStoolsNightList());
        request.getSession().setAttribute(TOILET_TIMING_LIST_PROPERTY, getToiletTimingList());
        request.getSession().setAttribute(PRESENT_BLOOD_LIST_PROPERTY, getPresentBloodList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(FURTHER_COMPLICATION_LIST_PROPERTY, getColitisComplicationList());

        return mapping.findForward(SUCCESS);
    }

}
