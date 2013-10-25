package org.patientview.repository.radar.dao.generic;

import org.patientview.model.radar.generic.MedicalResult;

public interface MedicalResultDao {

    void save(MedicalResult medicalResult);

    MedicalResult getMedicalResult(long radarNumber, String unitCode);

}
