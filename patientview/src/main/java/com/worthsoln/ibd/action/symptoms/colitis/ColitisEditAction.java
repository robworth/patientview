package com.worthsoln.ibd.action.symptoms.colitis;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.BaseSymptoms;
import com.worthsoln.ibd.model.symptoms.ColitisSymptoms;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ColitisEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = UserUtils.retrieveUser(request);

        // add previous score data from symptoms to the page
        List<Integer> scores = new ArrayList<Integer>();
        List<ColitisSymptoms> symptoms = getIbdManager().getAllColitis(user);

        for (BaseSymptoms symptom : symptoms) {
            scores.add(symptom.getScore());
        }

        request.getSession().setAttribute(Ibd.GRAPH_SCORES_PARAM, scores);

        request.getSession().setAttribute(STOOLS_DAY_LIST_PROPERTY, getStoolsDayList());
        request.getSession().setAttribute(STOOLS_NIGHT_LIST_PROPERTY, getStoolsNightList());
        request.getSession().setAttribute(TOILET_TIMING_LIST_PROPERTY, getToiletTimingList());
        request.getSession().setAttribute(PRESENT_BLOOD_LIST_PROPERTY, getPresentBloodList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(FURTHER_COMPLICATION_LIST_PROPERTY, getColitisComplicationList());

        return mapping.findForward(SUCCESS);
    }
}
