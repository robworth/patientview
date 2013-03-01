package com.worthsoln.ibd.action.myibd;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIbdEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        MyIbd myIbd = getIbdManager().getMyIbd(UserUtils.retrieveUser(request));

        if (myIbd == null) {
            myIbd = new MyIbd();
        }

        DynaActionForm dynaForm = (DynaActionForm) form;
        dynaForm.set(Ibd.DIAGNOSIS_ID_PARAM, myIbd.getDiagnosisId());
        dynaForm.set(Ibd.DISEASE_EXTENT_ID_PARAM, myIbd.getDiseaseExtentId());
        dynaForm.set(Ibd.YEAR_OF_DIAGNOSIS_PARAM, convertFormDateString(myIbd.getYearOfDiagnosis()));
        dynaForm.set(Ibd.BODY_PART_AFFECTED_PARAM, myIbd.getBodyPartAffected());
        dynaForm.set(Ibd.YEAR_FOR_SURVEILLANCE_COLONOSCOPY_PARAM, convertFormDateString(myIbd.getYearForSurveillanceColonoscopy()));
        dynaForm.set(Ibd.NAMED_CONSULTANT_PARAM, myIbd.getNamedConsultant());
        dynaForm.set(Ibd.NURSES_PARAM, myIbd.getNurses());
        dynaForm.set(Ibd.FAMILY_HISTORY_PARAM, myIbd.getFamilyHistory());
        dynaForm.set(Ibd.SMOKING_PARAM, myIbd.getSmoking());
        dynaForm.set(Ibd.SURGERY_PARAM, myIbd.getSurgery());
        dynaForm.set(Ibd.VACCINATION_RECORD_PARAM, myIbd.getVaccinationRecord());
        dynaForm.set(Ibd.COMPLICATIONS_PARAM, myIbd.getComplications());

        // set up all the needed lists in the session
        request.getSession().setAttribute(DISEASE_EXTENT_LIST_PROPERTY, getDiseaseExtentList());
        request.getSession().setAttribute(DIAGNOSIS_LIST_PROPERTY, getDiagnosisList());
        request.getSession().setAttribute(COMPLICATION_LIST_PROPERTY, getComplicationList());
        request.getSession().setAttribute(FAMILY_HISTORY_LIST_PROPERTY, getFamilyHistoryList());
        request.getSession().setAttribute(SMOKING_LIST_PROPERTY, getSmokingList());
        request.getSession().setAttribute(SURGERY_LIST_PROPERTY, getSurgeryList());
        request.getSession().setAttribute(BODY_PART_AFFECTED_LIST_PROPERTY, getBodyPartAffectedList());
        request.getSession().setAttribute(VACCINATION_RECORD_LIST_PROPERTY, getVaccinationRecordList());

        return mapping.findForward(SUCCESS);
    }
}
