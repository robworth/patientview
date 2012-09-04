package com.worthsoln.ibd.action.symptoms.crohns;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;
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
import java.util.Date;

public class CrohnsUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        // if these were set in the other form of the pge they will be passed through with this one
        Date fromDate = convertFormDateString(Ibd.FROM_DATE_PARAM, dynaForm);
        Date toDate = convertFormDateString(Ibd.TO_DATE_PARAM, dynaForm);

        request.setAttribute(Ibd.FROM_DATE_PARAM, convertFormDateString(fromDate));
        request.setAttribute(Ibd.TO_DATE_PARAM, convertFormDateString(toDate));

        if (!validate(dynaForm, request)) {
            mapping.findForward(INPUT);
        }

        CrohnsSymptoms crohnsSymptoms = new CrohnsSymptoms();
        crohnsSymptoms.setNhsno(getNhsNoForUser(request));
        crohnsSymptoms.setAbdominalPainId((Integer) dynaForm.get(Ibd.ABDOMINAL_PAIN_PARAM));
        crohnsSymptoms.setOpenBowels((Integer) dynaForm.get(Ibd.OPEN_BOWELS_PARAM));
        crohnsSymptoms.setFeelingId((Integer) dynaForm.get(Ibd.FEELING_PARAM));
        crohnsSymptoms.setComplicationId((Integer) dynaForm.get(Ibd.COMPLICATION_PARAM));
        crohnsSymptoms.setMassInTummyId((Integer) dynaForm.get(Ibd.MASS_IN_TUMMY_PARAM));
        crohnsSymptoms.setSymptomDate(convertFormDateString(Ibd.SYMPTOM_DATE_PARAM,  dynaForm));

        getIbdManager().saveCrohns(crohnsSymptoms);

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
