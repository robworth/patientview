package com.worthsoln.ibd.action.symptoms.colitis;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.Colitis;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ColitisUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        Colitis colitis = new Colitis();
        colitis.setNhsno(getNhsNoForUser(request));
        colitis.setStoolsDay((Integer) dynaForm.get(Ibd.STOOLS_DATE_PARAM));
        colitis.setStoolsNight((Integer) dynaForm.get(Ibd.STOOLS_NIGHT_PARAM));
        colitis.setToiletTiming((Integer) dynaForm.get(Ibd.TOILET_TIMING_PARAM));
        colitis.setPresentBlood((Integer) dynaForm.get(Ibd.PRESENT_BLOOD_PARAM));
        colitis.setFeeling((Integer) dynaForm.get(Ibd.FEELING_PARAM));
        colitis.setFurtherComplications((Integer) dynaForm.get(Ibd.FURTHER_COMPLICATIONS_PARAM));

        String colitisDateString = (String) dynaForm.get(Ibd.SYMPTOM_DATE_PARAM);
        if (colitisDateString != null && colitisDateString.length() > 0) {
            try {
                colitis.setSymptomDate(Ibd.DATE_FORMAT.parse(colitisDateString));
            } catch (Exception e) {
                // dunno just store with it set to null
            }
        }

        getIbdManager().saveColitis(colitis);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        if (form.get(Ibd.SYMPTOM_DATE_PARAM) == null ||
                ((String) form.get(Ibd.SYMPTOM_DATE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.SYMPTOM_DATE_PARAM, new ActionMessage(Ibd.DATE_REQUIRED));
        }
        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
