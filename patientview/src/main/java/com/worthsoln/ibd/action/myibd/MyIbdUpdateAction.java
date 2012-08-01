package com.worthsoln.ibd.action.myibd;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
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

        if (form.get(Ibd.DIAGNOSIS_ID_PARAM) == null || ((Long) form.get(Ibd.DIAGNOSIS_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.DIAGNOSIS_ID_PARAM, new ActionMessage(Ibd.DIAGNOSIS_REQUIRED));
        }

        if (form.get(Ibd.DISEASE_EXTENT_ID_PARAM) == null || ((Long) form.get(Ibd.DISEASE_EXTENT_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.DISEASE_EXTENT_ID_PARAM, new ActionMessage(Ibd.DISEASE_EXTENT_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }

    private String getNhsNoForUser(HttpServletRequest request) {
        User user = UserUtils.retrieveUser(request);

        UserMapping userMapping = LegacySpringUtils.getUserManager().getUserMappingPatientEntered(user);

        if (userMapping != null) {
            return userMapping.getNhsno();
        }

        return null;
    }
}
