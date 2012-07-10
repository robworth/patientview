package com.solidstategroup.radar.service.generic;

import com.solidstategroup.radar.model.generic.MedicalResult;

public interface MedicalResultManager {

    void save(MedicalResult medicalResult);

    MedicalResult getMedicalResult(long radarNumber, String unitCode);
}
