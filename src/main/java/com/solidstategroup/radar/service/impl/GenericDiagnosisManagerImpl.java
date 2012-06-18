package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.generic.GenericDiagnosisDao;
import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.model.generic.GenericDiagnosis;
import com.solidstategroup.radar.service.generic.GenericDiagnosisManager;

import java.util.List;


public class GenericDiagnosisManagerImpl implements GenericDiagnosisManager {
    private GenericDiagnosisDao genericDiagnosisDao;

    public List<GenericDiagnosis> getAll() {
        return genericDiagnosisDao.getAll();
    }

    public List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup) {
        return genericDiagnosisDao.getByDiseaseGroup(diseaseGroup);
    }

    public GenericDiagnosis getById(String id) {
        return genericDiagnosisDao.getById(id);
    }

    public void setGenericDiagnosisDao(GenericDiagnosisDao genericDiagnosisDao) {
        this.genericDiagnosisDao = genericDiagnosisDao;
    }
}
