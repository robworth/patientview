package com.worthsoln.ibd.action.mymedications;

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

    private static final String CURRENT_MEDICATIONS_PARAM = "currentMedications";
    private static final String STOPPED_MEDICATIONS_PARAM = "stoppedMedications";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        List<MyMedication> currentMedications = getIbdManager().getCurrentMedications(UserUtils.retrieveUser(request));

        if (currentMedications != null && !currentMedications.isEmpty()) {
            request.setAttribute(CURRENT_MEDICATIONS_PARAM, currentMedications);
        }

        List<MyMedication> stoppedMedications = getIbdManager().getStoppedMedications(UserUtils.retrieveUser(request));

        if (stoppedMedications != null && !stoppedMedications.isEmpty()) {
            request.setAttribute(STOPPED_MEDICATIONS_PARAM, stoppedMedications);
        }

        return mapping.findForward(SUCCESS);
    }
}