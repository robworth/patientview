package org.patientview.radar.dao.generic;

import org.patientview.radar.model.generic.MedicalResult;

public interface MedicalResultDao {

    void save(MedicalResult medicalResult);

    MedicalResult getMedicalResult(long radarNumber, String unitCode);

}
