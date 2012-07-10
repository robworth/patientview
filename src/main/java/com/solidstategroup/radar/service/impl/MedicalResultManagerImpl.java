package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.generic.MedicalResultDao;
import com.solidstategroup.radar.model.generic.MedicalResult;
import com.solidstategroup.radar.service.generic.MedicalResultManager;

public class MedicalResultManagerImpl implements MedicalResultManager {
    private MedicalResultDao medicalResultDao;

    public void save(MedicalResult medicalResult) {
        medicalResultDao.save(medicalResult);
    }

    public MedicalResult getMedicalResult(long radarNumber, String unitCode) {
        return medicalResultDao.getMedicalResult(radarNumber, unitCode);
    }

    public void setMedicalResultDao(MedicalResultDao medicalResultDao) {
        this.medicalResultDao = medicalResultDao;
    }
}
