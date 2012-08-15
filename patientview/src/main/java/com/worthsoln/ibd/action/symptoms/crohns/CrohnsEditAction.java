package com.worthsoln.ibd.action.symptoms.crohns;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.BaseSymptoms;
import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CrohnsEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = UserUtils.retrieveUser(request);

        // add previous score data from symptoms to the page
        List<Integer> scores = new ArrayList<Integer>();
        List<CrohnsSymptoms> symptoms = getIbdManager().getAllCrohns(user);

        for (BaseSymptoms symptom : symptoms) {
            scores.add(symptom.getScore());
        }

        request.getSession().setAttribute(Ibd.GRAPH_SCORES_PARAM, scores);

        request.getSession().setAttribute(ABDOMINAL_PAIN_LIST_PROPERTY, getAbdominalPainList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(CROHNS_COMPLICATION_LIST_PROPERTY, getCrohnsComplicationList());
        request.getSession().setAttribute(MASS_IN_TUMMY_LIST_PROPERTY, getMassInTummy());
        request.getSession().setAttribute(OPEN_BOWEL_LIST_PROPERTY, getOpenBowelList());

        return mapping.findForward(SUCCESS);
    }
}
