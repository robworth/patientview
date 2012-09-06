package com.worthsoln.ibd.action.myibd;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.medication.MyMedication;
import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class MyIbdAction extends BaseAction {

    // just maps question link codes to params for the front end to pick up
    private static final HashMap<String, String> MY_IBD_QUESTIONS_MAP = new HashMap<String, String>() {
        {
            put("Primary Diagnosis", Ibd.PRIMARY_DIAGNOSIS_LINK_PARAM);
            put("Disease Extent", Ibd.DISEASE_EXTENT_LINK_PARAM);
            put("Year of Diagnosis", Ibd.YEAR_OF_DIAGNOSIS_LINK_PARAM);
            put("Complications", Ibd.COMPLICATIONS_LINK_PARAM);
            put("Other parts of the body affected", Ibd.BODY_PART_AFFECTED_LINK_PARAM);
            put("Year for Surveillance Colonoscopy", Ibd.YEAR_FOR_SURVEILLANCE_COLONOSCOPY_LINK_PARAM);
            put("Named Consultant", Ibd.NAMED_CONSULTANT_LINK_PARAM);
            put("Nurses", Ibd.NURSES_LINK_PARAM);
            put("Weight", Ibd.WEIGHT_LINK_PARAM);
            put("IBD Related Family History", Ibd.FAMILY_HISTORY_LINK_PARAM);
            put("Smoking History", Ibd.SMOKING_LINK_PARAM);
            put("Surgery History", Ibd.SURGERY_LINK_PARAM);
            put("Vaccination History", Ibd.VACCINATION_RECORD_LINK_PARAM);
        }
    };

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        MyIbd myIbd = getIbdManager().getMyIbd(UserUtils.retrieveUser(request));

        if (myIbd != null) {
            request.setAttribute(Ibd.MY_IBD_PARAM, myIbd);
        }

        List<MyMedication> currentMedications = getIbdManager().getCurrentMedications(UserUtils.retrieveUser(request));

        if (currentMedications != null && !currentMedications.isEmpty()) {
            request.setAttribute(Ibd.CURRENT_MEDICATIONS_PARAM, currentMedications);
        }

        // add any managed links in for this page
        addMyIbdLinks(myIbd, request);

        return mapping.findForward(SUCCESS);
    }

    private void addMyIbdLinks(MyIbd myIbd, HttpServletRequest request) {
        for (EdtaCode edtaCode : LegacySpringUtils.getEdtaCodeManager().get(Ibd.MY_IBD_LINKS_TYPE)) {
            if (MY_IBD_QUESTIONS_MAP.containsKey(edtaCode.getEdtaCode())) {
                String link = null;

                if (myIbd.getDiagnosis().equals(Diagnosis.CROHNS)) {
                    link = edtaCode.getMedicalLink01();
                } else if (myIbd.getDiagnosis().equals(Diagnosis.COLITIS_UNSPECIFIED)) {
                    link = edtaCode.getMedicalLink02();
                }

                if (link != null && link.length() > 0) {
                    request.setAttribute(MY_IBD_QUESTIONS_MAP.get(edtaCode.getEdtaCode()), link);
                }
            }
        }
    }
}
