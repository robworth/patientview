package com.worthsoln.ibd.action.symptoms.crohns;

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

public class CrohnsEditAction extends BaseAction {

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

        addSymptomsGraphData(user, Ibd.CROHNS_GRAPH_TYPE, fromDate, toDate, request);

        if (request.getParameter(Ibd.SHOW_ADVICE_PARAM) != null) {
            addLastSymptomAdvice(user, Ibd.CROHNS_GRAPH_TYPE, request);
        }

        // set the form to have empty values
        dynaForm.set(Ibd.ABDOMINAL_PAIN_PARAM, null);
        dynaForm.set(Ibd.OPEN_BOWELS_PARAM, null);
        dynaForm.set(Ibd.FEELING_PARAM, null);
        dynaForm.set(Ibd.COMPLICATION_PARAM, null);
        dynaForm.set(Ibd.MASS_IN_TUMMY_PARAM, null);
        dynaForm.set(Ibd.SYMPTOM_DATE_PARAM, null);

        // add the lists to the page
        request.getSession().setAttribute(ABDOMINAL_PAIN_LIST_PROPERTY, getAbdominalPainList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(CROHNS_COMPLICATION_LIST_PROPERTY, getCrohnsComplicationList());
        request.getSession().setAttribute(MASS_IN_TUMMY_LIST_PROPERTY, getMassInTummy());
        request.getSession().setAttribute(OPEN_BOWEL_LIST_PROPERTY, getOpenBowelList());

        // add any managed links in for this page
        addMyIbdLinks(getIbdManager().getMyIbd(UserUtils.retrieveUser(request)), request);

        return mapping.findForward(SUCCESS);
    }
}
