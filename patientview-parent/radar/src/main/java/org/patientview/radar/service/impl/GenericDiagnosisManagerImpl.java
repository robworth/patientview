package org.patientview.radar.service.impl;

import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.GenericDiagnosis;
import org.patientview.radar.service.generic.GenericDiagnosisManager;

import java.util.List;


public class GenericDiagnosisManagerImpl implements GenericDiagnosisManager {
    private GenericDiagnosisDao genericDiagnosisDao;

    public List<GenericDiagnosis> getAll() {
        return genericDiagnosisDao.getAll();
    }

    public List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup) {
        return genericDiagnosisDao.getByDiseaseGroup(diseaseGroup);
    }

    public GenericDiagnosis get(String prdCode, String workingGroup) {
        return genericDiagnosisDao.get(prdCode, workingGroup);
    }

    public void setGenericDiagnosisDao(GenericDiagnosisDao genericDiagnosisDao) {
        this.genericDiagnosisDao = genericDiagnosisDao;
    }
}
