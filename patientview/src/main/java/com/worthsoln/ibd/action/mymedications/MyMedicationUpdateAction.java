package com.worthsoln.ibd.action.mymedications;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.medication.MyMedication;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyMedicationUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        // the jsp on the front end has some logic in for styles which require the medicationTypeId and MedicationId
        // to not be null all the struts goes spastic so just set to -1 if they are
        if (dynaForm.get(Ibd.MEDICATION_TYPE_ID_PARAM) == null) {
            dynaForm.set(Ibd.MEDICATION_TYPE_ID_PARAM, (long) -1);
        }

        if (dynaForm.get(Ibd.MEDICATION_ID_PARAM) == null) {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, (long) -1);
        }

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        MyMedication myMedication = null;

        Long id = (Long) dynaForm.get(Ibd.ID_PARAM);

        // if it has an id then try and pull it back
        if (id != null && id > 0) {
            myMedication = getIbdManager().getMyMedication(id);
        }

        // if its null then its new so set the nhs no to the user
        if (myMedication == null || !myMedication.hasValidId()) {
            myMedication = new MyMedication();
            myMedication.setNhsno(getNhsNoForUser(request));
        }

        myMedication.setOtherMedication((String) dynaForm.get(Ibd.OTHER_MEDICATION_ID_PARAM));
        myMedication.setOtherMedicationDose((String) dynaForm.get(Ibd.OTHER_MEDICATION_DOSE_ID_PARAM));
        myMedication.setMedicationFrequencyId((Long) dynaForm.get(Ibd.MEDICATION_FREQUENCY_ID_PARAM));
        myMedication.setDateStarted(convertFormDateString(Ibd.DATE_STARTED_PARAM, dynaForm));

        Long medicationTypeId = (Long) dynaForm.get(Ibd.MEDICATION_TYPE_ID_PARAM);
        Long medicationId = (Long) dynaForm.get(Ibd.MEDICATION_ID_PARAM);
        Long medicationDoseId = (Long) dynaForm.get(Ibd.MEDICATION_DOSE_ID_PARAM);

        getIbdManager().saveMyMedication(myMedication, medicationTypeId, medicationId, medicationDoseId);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        if (form.get(Ibd.DATE_STARTED_PARAM) == null || ((String) form.get(Ibd.DATE_STARTED_PARAM)).length() == 0) {
            actionErrors.add(Ibd.DATE_STARTED_PARAM, new ActionMessage(Ibd.DATE_STARTED_REQUIRED));
        }

        if (form.get(Ibd.MEDICATION_TYPE_ID_PARAM) == null || ((Long) form.get(Ibd.MEDICATION_TYPE_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.MEDICATION_TYPE_ID_PARAM, new ActionMessage(Ibd.MEDICATION_TYPE_REQUIRED));
        } else {
            // user has to select a medication or the other option
            // if they have selected a medication then they need to have selected a dosage aswell
            // the other option will pass through -2 in which case they need to have also entered text otherMedication
            Long medicationId = (Long) form.get(Ibd.MEDICATION_ID_PARAM);
            String otherMedication = (String) form.get(Ibd.OTHER_MEDICATION_ID_PARAM);

            if (medicationId == null || (medicationId <= 0 && medicationId != -2)) {
                actionErrors.add(Ibd.MEDICATION_ID_PARAM, new ActionMessage(Ibd.MEDICATION_REQUIRED));
            } else if (medicationId == -2 && (otherMedication == null || otherMedication.length() == 0)) {
                actionErrors.add(Ibd.OTHER_MEDICATION_ID_PARAM, new ActionMessage(Ibd.OTHER_MEDICATION_REQUIRED));
            }

            // if they selected a medication the validate dose
            if (medicationId != null && medicationId > 0) {
                if (form.get(Ibd.MEDICATION_DOSE_ID_PARAM) == null
                        || ((Long) form.get(Ibd.MEDICATION_DOSE_ID_PARAM) <= 0)) {
                    actionErrors.add(Ibd.MEDICATION_DOSE_ID_PARAM, new ActionMessage(Ibd.MEDICATION_DOSE_REQUIRED));
                }
            }

            if (form.get(Ibd.MEDICATION_FREQUENCY_ID_PARAM) == null
                    || ((Long) form.get(Ibd.MEDICATION_FREQUENCY_ID_PARAM) <= 0)) {
                actionErrors.add(Ibd.MEDICATION_FREQUENCY_ID_PARAM,
                        new ActionMessage(Ibd.MEDICATION_FREQUENCY_REQUIRED));
            }
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
