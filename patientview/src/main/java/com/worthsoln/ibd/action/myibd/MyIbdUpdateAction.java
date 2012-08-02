package com.worthsoln.ibd.action.myibd;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

public class MyIbdUpdateAction extends BaseAction {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        User user = UserUtils.retrieveUser(request);

        MyIbd myIbd = getIbdManager().getMyIbd(user);

        // if its null then its new so set the nhs no to the user
        if (myIbd == null || !myIbd.hasValidId()) {
            myIbd = new MyIbd();
            myIbd.setNhsno(getNhsNoForUser(request));
        }

        myIbd.setDiagnosisId((Long) dynaForm.get(Ibd.DIAGNOSIS_ID_PARAM));
        myIbd.setDiseaseExtentId((Long) dynaForm.get(Ibd.DISEASE_EXTENT_ID_PARAM));
        myIbd.setBodyPartAffectedId((Long) dynaForm.get(Ibd.BODY_PART_AFFECTED_ID_PARAM));
        myIbd.setWeight((Double) dynaForm.get(Ibd.WEIGHT_PARAM));
        myIbd.setFamilyHistoryId((Long) dynaForm.get(Ibd.FAMILY_HISTORY_ID_PARAM));
        myIbd.setSmokingId((Long) dynaForm.get(Ibd.SMOKING_ID_PARAM));
        myIbd.setSurgeryId((Long) dynaForm.get(Ibd.SURGERY_ID_PARAM));
        myIbd.setVaccinationRecordId((Long) dynaForm.get(Ibd.VACCINATION_RECORD_ID_PARAM));

        Long[] complicationIds = (Long[]) dynaForm.get(Ibd.COMPLICATION_IDS_PARAM);
        myIbd.setComplicationIds(new HashSet<Long>(Arrays.asList(complicationIds)));

        getIbdManager().saveMyIbd(myIbd);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        // Comment out the ones that do not require validation

        if (form.get(Ibd.DIAGNOSIS_ID_PARAM) == null || ((Long) form.get(Ibd.DIAGNOSIS_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.DIAGNOSIS_ID_PARAM, new ActionMessage(Ibd.DIAGNOSIS_REQUIRED));
        }

        if (form.get(Ibd.DISEASE_EXTENT_ID_PARAM) == null || ((Long) form.get(Ibd.DISEASE_EXTENT_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.DISEASE_EXTENT_ID_PARAM, new ActionMessage(Ibd.DISEASE_EXTENT_REQUIRED));
        }

        if (form.get(Ibd.COMPLICATION_IDS_PARAM) == null
                || ((Long[]) form.get(Ibd.COMPLICATION_IDS_PARAM)).length == 0) {
            actionErrors.add(Ibd.COMPLICATION_IDS_PARAM, new ActionMessage(Ibd.COMPLICATIONS_REQUIRED));
        }

        if (form.get(Ibd.BODY_PART_AFFECTED_ID_PARAM) == null
                || ((Long) form.get(Ibd.BODY_PART_AFFECTED_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.BODY_PART_AFFECTED_ID_PARAM, new ActionMessage(Ibd.BODY_PART_AFFECTED_REQUIRED));
        }

        if (form.get(Ibd.WEIGHT_PARAM) == null || ((Double) form.get(Ibd.WEIGHT_PARAM) <= 0)) {
            actionErrors.add(Ibd.WEIGHT_PARAM, new ActionMessage(Ibd.WEIGHT_REQUIRED));
        }

        if (form.get(Ibd.FAMILY_HISTORY_ID_PARAM) == null
                || ((Long) form.get(Ibd.FAMILY_HISTORY_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.FAMILY_HISTORY_ID_PARAM, new ActionMessage(Ibd.FAMILY_HISTORY_REQUIRED));
        }

        if (form.get(Ibd.SMOKING_ID_PARAM) == null || ((Long) form.get(Ibd.SMOKING_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.SMOKING_ID_PARAM, new ActionMessage(Ibd.SMOKING_REQUIRED));
        }

        if (form.get(Ibd.SURGERY_ID_PARAM) == null || ((Long) form.get(Ibd.SURGERY_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.SURGERY_ID_PARAM, new ActionMessage(Ibd.SURGERY_REQUIRED));
        }

        if (form.get(Ibd.VACCINATION_RECORD_ID_PARAM) == null
                || ((Long) form.get(Ibd.VACCINATION_RECORD_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.VACCINATION_RECORD_ID_PARAM, new ActionMessage(Ibd.VACCINATION_RECORD_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
