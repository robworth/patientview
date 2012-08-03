package com.worthsoln.ibd.action;

import com.worthsoln.ibd.model.enums.AreaToDiscuss;
import com.worthsoln.ibd.model.enums.BodyPartAffected;
import com.worthsoln.ibd.model.enums.Complication;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.DiseaseExtent;
import com.worthsoln.ibd.model.enums.FamilyHistory;
import com.worthsoln.ibd.model.enums.Smoking;
import com.worthsoln.ibd.model.enums.Surgery;
import com.worthsoln.ibd.model.enums.VaccinationRecord;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.service.ibd.IbdManager;
import com.worthsoln.utils.LegacySpringUtils;
import org.springframework.web.struts.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BaseAction extends ActionSupport {

    protected static final String SUCCESS = "success";
    protected static final String INPUT = "input";
    protected static final String ERROR = "error";

    // my ibd lists
    protected static final String DISEASE_EXTENT_LIST_PROPERTY = "diseaseExtentList";
    protected static final String DIAGNOSIS_LIST_PROPERTY = "diagnosisList";
    protected static final String COMPLICATION_LIST_PROPERTY = "complicationList";
    protected static final String FAMILY_HISTORY_LIST_PROPERTY = "familyHistoryList";
    protected static final String SMOKING_LIST_PROPERTY = "smokingList";
    protected static final String SURGERY_LIST_PROPERTY = "surgeryList";
    protected static final String BODY_PART_AFFECTED_LIST_PROPERTY = "bodyPartAffectedList";
    protected static final String VACCINATION_RECORD_LIST_PROPERTY = "vaccinationRecordList";

    // care plan lists
    protected static final String AREA_TO_DISCUSS_LIST_PROPERTY = "areaToDiscussList";
    protected static final String SCALE_LIST_PROPERTY = "scaleList";

    protected static List<ScaleItem> scaleList;

    protected List<DiseaseExtent> getDiseaseExtentList() {
        return DiseaseExtent.getAsList();
    }

    protected List<Diagnosis> getDiagnosisList() {
        return Diagnosis.getAsList();
    }

    protected List<Complication> getComplicationList() {
        return Complication.getAsList();
    }

    protected List<FamilyHistory> getFamilyHistoryList() {
        return FamilyHistory.getAsList();
    }

    protected List<Smoking> getSmokingList() {
        return Smoking.getAsList();
    }

    protected List<Surgery> getSurgeryList() {
        return Surgery.getAsList();
    }

    protected List<BodyPartAffected> getBodyPartAffectedList() {
        return BodyPartAffected.getAsList();
    }

    protected List<VaccinationRecord> getVaccinationRecordList() {
        return VaccinationRecord.getAsList();
    }

    protected List<AreaToDiscuss> getAreaToDiscussList() {
        return AreaToDiscuss.getAsList();
    }

    protected List<ScaleItem> getScaleList() {
        if (scaleList == null) {
            scaleList = new ArrayList<ScaleItem>();

            for (int x = 1; x <= 10; x++) {
                scaleList.add(new ScaleItem(x));
            }
        }

        return scaleList;
    }

    protected String getNhsNoForUser(HttpServletRequest request) {
        User user = UserUtils.retrieveUser(request);

        UserMapping userMapping = LegacySpringUtils.getUserManager().getUserMappingPatientEntered(user);

        if (userMapping != null) {
            return userMapping.getNhsno();
        }

        return null;
    }

    protected IbdManager getIbdManager() {
        // To do this without the spring action support
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(
//                servlet.getServletContext());

        return getWebApplicationContext().getBean(IbdManager.class);
    }

    /**
     * This is just a simple class as the struts list cant just take an array of ints
     */
    public class ScaleItem {
        private int value;

        public ScaleItem(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
