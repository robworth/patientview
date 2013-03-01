package com.worthsoln.ibd.action.symptoms.colitis;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class ColitisEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        // if these were set in the other form of the pge they will be passed through with this one
        Date fromDate = convertFormDateString(Ibd.FROM_DATE_PARAM, dynaForm);
        Date toDate = convertFormDateString(Ibd.TO_DATE_PARAM, dynaForm);

        request.setAttribute(Ibd.FROM_DATE_PARAM, convertFormDateString(fromDate));
        request.setAttribute(Ibd.TO_DATE_PARAM, convertFormDateString(toDate));

        // need to re add graph data to the page
        addSymptomsGraphData(user, Ibd.COLITIS_GRAPH_TYPE, fromDate, toDate, request);

        if (request.getParameter(Ibd.SHOW_ADVICE_PARAM) != null) {
            addLastSymptomAdvice(user, Ibd.COLITIS_GRAPH_TYPE, request);
        }

        // set the form to have empty values
        dynaForm.set(Ibd.NUMBER_OF_STOOLS_DAYTIME_PARAM, null);
        dynaForm.set(Ibd.NUMBER_OF_STOOLS_NIGHTTIME_PARAM, null);
        dynaForm.set(Ibd.TOILET_TIMING_PARAM, null);
        dynaForm.set(Ibd.PRESENT_BLOOD_PARAM, null);
        dynaForm.set(Ibd.FEELING_PARAM, null);
        dynaForm.set(Ibd.COMPLICATION_PARAM, null);
        dynaForm.set(Ibd.SYMPTOM_DATE_PARAM, null);

        // add the lists to the page
        request.getSession().setAttribute(STOOLS_DAY_LIST_PROPERTY, getStoolsDayList());
        request.getSession().setAttribute(STOOLS_NIGHT_LIST_PROPERTY, getStoolsNightList());
        request.getSession().setAttribute(TOILET_TIMING_LIST_PROPERTY, getToiletTimingList());
        request.getSession().setAttribute(PRESENT_BLOOD_LIST_PROPERTY, getPresentBloodList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(FURTHER_COMPLICATION_LIST_PROPERTY, getColitisComplicationList());

        // add any managed links in for this page
        addMyIbdLinks(getIbdManager().getMyIbd(UserUtils.retrieveUser(request)), request);

        return mapping.findForward(SUCCESS);
    }
}
