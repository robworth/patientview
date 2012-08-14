package com.worthsoln.ibd.action.symptoms.crohns;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.symptoms.Crohns;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrohnsUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        Crohns crohns = new Crohns();
        crohns.setNhsno(getNhsNoForUser(request));
        crohns.setAbdominalPain((Integer) dynaForm.get(Ibd.ABDOMINAL_PAIN_PARAM));
        crohns.setOpenBowels((Integer) dynaForm.get(Ibd.OPEN_BOWELS_PARAM));
        crohns.setFeeling((Integer) dynaForm.get(Ibd.FEELING_PARAM));
        crohns.setComplications((Integer) dynaForm.get(Ibd.COMPLICATIONS_PARAM));
        crohns.setMassInTummy((Integer) dynaForm.get(Ibd.MASS_IN_TUMMY_PARAM));

        String crohnsDateString = (String) dynaForm.get(Ibd.CROHNS_DATE_PARAM);
        if (crohnsDateString != null && crohnsDateString.length() > 0) {
            try {
                crohns.setChornsDate(Ibd.DATE_FORMAT.parse(crohnsDateString));
            } catch (Exception e) {
                // dunno just store with it set to null
            }
        }

        getIbdManager().saveCrohns(crohns);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        if (form.get(Ibd.CROHNS_DATE_PARAM) == null ||
                ((String) form.get(Ibd.CROHNS_DATE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.CROHNS_DATE_PARAM, new ActionMessage(Ibd.DATE_REQUIRED));
        }

        if (form.get(Ibd.OPEN_BOWELS_PARAM) == null ||
                ((Integer) form.get(Ibd.OPEN_BOWELS_PARAM)) < 0) {
            actionErrors.add(Ibd.OPEN_BOWELS_PARAM, new ActionMessage(Ibd.OPEN_BOWELS_INVALID_NUMBER));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
