package com.worthsoln.patientview.sharingthoughts;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.SharedThought;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SharingThoughtsRetrieveThoughtAction extends BaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DynaActionForm dynaForm = (DynaActionForm) form;
        long thoughtId = (Long) dynaForm.get(SharingThoughts.ID);
        SharedThought thought = getSharedThoughtManager().getSharedThought(thoughtId);

        request.setAttribute(SharingThoughts.THOUGHT_PARAM, thought);

        if (thought.getPositiveNegative() == 1) {
            return mapping.findForward("positive");
        } else {
            return mapping.findForward("negative");
        }
    }
}
