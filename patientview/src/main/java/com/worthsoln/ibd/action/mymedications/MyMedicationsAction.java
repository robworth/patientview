package com.worthsoln.ibd.action.mymedications;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.medication.MyMedication;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyMedicationsAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        List<MyMedication> currentMedications = getIbdManager().getCurrentMedications(UserUtils.retrieveUser(request));

        if (currentMedications != null && !currentMedications.isEmpty()) {
            request.setAttribute(Ibd.CURRENT_MEDICATIONS_PARAM, currentMedications);
        }

        List<MyMedication> stoppedMedications = getIbdManager().getStoppedMedications(UserUtils.retrieveUser(request));

        if (stoppedMedications != null && !stoppedMedications.isEmpty()) {
            request.setAttribute(Ibd.STOPPED_MEDICATIONS_PARAM, stoppedMedications);
        }

        return mapping.findForward(SUCCESS);
    }
}
