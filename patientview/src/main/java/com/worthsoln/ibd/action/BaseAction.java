package com.worthsoln.ibd.action;

import com.worthsoln.ibd.model.enums.BodyPartAffected;
import com.worthsoln.ibd.model.enums.Complication;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.DiseaseExtent;
import com.worthsoln.ibd.model.enums.FamilyHistory;
import com.worthsoln.ibd.model.enums.Smoking;
import com.worthsoln.ibd.model.enums.Surgery;
import com.worthsoln.ibd.model.enums.VaccinationRecord;
import com.worthsoln.service.ibd.IbdManager;
import org.springframework.web.struts.ActionSupport;


import java.util.List;

public class BaseAction extends ActionSupport {

    protected static String SUCCESS = "success";
    protected static String INPUT = "input";
    protected static String ERROR = "error";

    protected static String SUBMIT_PROPERTY = "submit";
    protected static String DISEASE_EXTENT_LIST_PROPERTY = "diseaseExtentList";
    protected static String DIAGNOSIS_LIST_PROPERTY = "diagnosisList";
    protected static String COMPLICATION_LIST_PROPERTY = "complicationList";
    protected static String FAMILY_HISTORY_LIST_PROPERTY = "familyHistoryList";
    protected static String SMOKING_LIST_PROPERTY = "smokingList";
    protected static String SURGERY_LIST_PROPERTY = "surgeryList";
    protected static String BODY_PART_AFFECTED_LIST_PROPERTY = "bodyPartAffectedList";
    protected static String VACCINATION_RECORD_LIST_PROPERTY = "vaccinationRecordList";

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

    protected IbdManager getIbdManager() {
        return getWebApplicationContext().getBean(IbdManager.class);
    }
}
