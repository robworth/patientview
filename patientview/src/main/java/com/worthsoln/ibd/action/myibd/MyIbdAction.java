package com.worthsoln.ibd.action.myibd;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.medication.MyMedication;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyIbdAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);

        MyIbd myIbd = getIbdManager().getMyIbd(user);

        if (myIbd != null) {
            request.setAttribute(Ibd.MY_IBD_PARAM, myIbd);
        }

        // get the patients latest weight from the test results
        String weight = getIbdManager().getWeight(user);

        if (weight != null) {
            request.setAttribute(Ibd.WEIGHT_PARAM, weight);
        }

        // get list of medications for user
        List<MyMedication> currentMedications = getIbdManager().getCurrentMedications(user);

        if (currentMedications != null && !currentMedications.isEmpty()) {
            request.setAttribute(Ibd.CURRENT_MEDICATIONS_PARAM, currentMedications);
        }

        // add any managed links in for this page
        addMyIbdLinks(myIbd, request);

        return mapping.findForward(SUCCESS);
    }
}
