package org.patientview.repository.radar.dao;

import org.patientview.model.radar.Treatment;
import org.patientview.model.radar.TreatmentModality;

import java.util.List;

public interface TreatmentDao {

    void saveTreatment(Treatment treatment);

    void deleteTreatment(Treatment treatment);

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);

    List<TreatmentModality> getTreatmentModalities();
}
