package org.patientview.radar.service.impl;

import org.patientview.radar.dao.generic.MedicalResultDao;
import org.patientview.radar.model.generic.MedicalResult;
import org.patientview.radar.service.generic.MedicalResultManager;

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
