package com.worthsoln.ibd.action.symptoms.colitis;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.BaseSymptoms;
import com.worthsoln.ibd.model.symptoms.ColitisSymptoms;
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

public class ColitisEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = UserUtils.retrieveUser(request);

        // add previous score data from symptoms to the page
        List<SymptomsData> graphData = new ArrayList<SymptomsData>();
        List<ColitisSymptoms> symptoms = getIbdManager().getAllColitis(user);

        for (BaseSymptoms symptom : symptoms) {
            graphData.add(new SymptomsData(symptom));
        }

        request.getSession().setAttribute(Ibd.GRAPH_DATA_PARAM, graphData);

        // set the form to have empty values
        DynaActionForm dynaForm = (DynaActionForm) form;

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

        return mapping.findForward(SUCCESS);
    }
}
