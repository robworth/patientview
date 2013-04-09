package com.worthsoln.patientview.sharingthoughts;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.unit.UnitUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SharingThoughtsNegativeAction extends BaseAction {

    // TODO: check this isn't redundant with SharingThoughtsPositiveAction
    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UnitUtils.putRelevantUnitsInRequest(request);

        return mapping.findForward("success");
    }
}
