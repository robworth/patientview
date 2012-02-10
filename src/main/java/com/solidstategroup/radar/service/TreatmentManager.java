package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.model.TreatmentModality;

import java.util.List;

public interface TreatmentManager {

    void saveTreatment(Treatment treatment);

    void deleteTreatment(Treatment treatment);

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);
    
    List<TreatmentModality> getTreatmentModalities();
}
