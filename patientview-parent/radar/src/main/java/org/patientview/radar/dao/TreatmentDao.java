package org.patientview.radar.dao;

import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;

import java.util.List;

public interface TreatmentDao {

    void saveTreatment(Treatment treatment);

    void deleteTreatment(Treatment treatment);

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);
    
    List<TreatmentModality> getTreatmentModalities();
}
