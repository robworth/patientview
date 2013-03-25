package com.worthsoln.patientview.sharingthoughts;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.SharedThought;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SharingThoughtsDeleteThoughtAction extends BaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = UserUtils.retrieveUser(request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        long thoughtId = (Long) dynaForm.get(SharingThoughts.ID);

        getSharedThoughtManager().delete(thoughtId);

        SharingThoughts.putThoughtListInRequest(request, user, true);
        SharingThoughts.putThoughtListInRequest(request, user, false);

        return mapping.findForward(SUCCESS);
    }
}
