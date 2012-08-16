package com.worthsoln.ibd.action.symptoms.crohns;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.BaseSymptoms;
import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;
import com.worthsoln.ibd.model.symptoms.SymptomsData;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CrohnsEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = UserUtils.retrieveUser(request);

        // add previous score data from symptoms to the page
        List<SymptomsData> graphData = new ArrayList<SymptomsData>();
        List<CrohnsSymptoms> symptoms = getIbdManager().getAllCrohns(user);

        for (BaseSymptoms symptom : symptoms) {
            graphData.add(new SymptomsData(symptom));
        }

        request.getSession().setAttribute(Ibd.GRAPH_DATA_PARAM, graphData);

        // set the form to have empty values
        DynaActionForm dynaForm = (DynaActionForm) form;

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

        return mapping.findForward(SUCCESS);
    }
}
