package com.worthsoln.patientview;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.edtacode.EdtaCodeUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.news.NewsUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PatientDetailsAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        NewsUtils.putAppropriateNewsForViewingInRequest(request);

        DatabaseDAO dao = getDao(request);

        List<PatientDetails> patientDetails = PatientDetailsUtils.buildPatientDetails(request, dao);

        request.setAttribute("patientDetails", patientDetails);

        EdtaCodeUtils.addEdtaCodeToRequest("static", "staticLinks", dao, request);
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "edtaCode";
    }
}
