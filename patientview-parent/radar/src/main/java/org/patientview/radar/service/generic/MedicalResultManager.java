package org.patientview.radar.service.generic;

import org.patientview.radar.model.generic.MedicalResult;

public interface MedicalResultManager {

    void save(MedicalResult medicalResult);

    MedicalResult getMedicalResult(long radarNumber, String unitCode);
}
