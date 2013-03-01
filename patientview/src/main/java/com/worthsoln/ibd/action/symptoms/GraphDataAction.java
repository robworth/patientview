package com.worthsoln.ibd.action.symptoms;

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

public class GraphDataAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        User user = UserUtils.retrieveUser(request);

        // can only return data if we have a graph type
        Integer graphType = (Integer) dynaForm.get(Ibd.GRAPH_TYPE_PARAM);
        Date fromDate = convertFormDateString(Ibd.FROM_DATE_PARAM, dynaForm);
        Date toDate = convertFormDateString(Ibd.TO_DATE_PARAM, dynaForm);

        // add the graph data to the request to the request
        addSymptomsGraphData(user, graphType, fromDate, toDate, request);

        return mapping.findForward(SUCCESS);
    }
}
