package org.patientview.repository.radar.service.generic;

import org.patientview.model.radar.generic.MedicalResult;

public interface MedicalResultManager {

    void save(MedicalResult medicalResult);

    MedicalResult getMedicalResult(long radarNumber, String unitCode);
}
