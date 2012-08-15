package com.worthsoln.ibd.action.symptoms;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.BaseSymptoms;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraphDataAction extends BaseAction {

    private static final String NO_GRAPH_TYPE_SPECIFIED = "No graph type specified";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        User user = UserUtils.retrieveUser(request);

        // returning an error string if an error happened and a list of scores.
        // Returning both as struts goes spastic bout nulls
        String error = "";
        List<Integer> scores = new ArrayList<Integer>();

        // can only return data if we have a graph type
        Integer graphType = (Integer) dynaForm.get(Ibd.GRAPH_TYPE_PARAM);

        if (graphType != null) {
            Date fromDate = null;
            Date toDate = null;
            List<? extends BaseSymptoms> symptoms = null;

            String fromDateString = (String) dynaForm.get(Ibd.FROM_DATE_PARAM);
            String toDateString = (String) dynaForm.get(Ibd.TO_DATE_PARAM);

            if (fromDateString != null && fromDateString.length() > 0) {
                try {
                    fromDate = Ibd.DATE_FORMAT.parse(fromDateString);
                } catch (Exception e) {
                    // dunno
                }
            }

            if (toDateString != null && toDateString.length() > 0) {
                try {
                    toDate = Ibd.DATE_FORMAT.parse(toDateString);
                } catch (Exception e) {
                    // dunno
                }
            }

            if (graphType == Ibd.COLITIS_GRAPH_TYPE) {
                symptoms = getIbdManager().getAllColitis(user, fromDate, toDate);
            } else if (graphType == Ibd.CROHNS_GRAPH_TYPE) {
                symptoms = getIbdManager().getAllCrohns(user, fromDate, toDate);
            } else {
                request.setAttribute(Ibd.GRAPH_DATA_ERROR_PARAM, NO_GRAPH_TYPE_SPECIFIED);
            }

            if (symptoms != null) {
                for (BaseSymptoms symptom : symptoms) {
                    scores.add(symptom.getScore());
                }
            }
        } else {
            error = NO_GRAPH_TYPE_SPECIFIED;
        }

        // add the objects to the request
        request.setAttribute(Ibd.GRAPH_DATA_ERROR_PARAM, error);
        request.setAttribute(Ibd.GRAPH_SCORES_PARAM, scores);

        return mapping.findForward(SUCCESS);
    }
}
