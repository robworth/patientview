package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.model.TreatmentModality;

import java.util.List;

public interface TreatmentDao {

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);
    
    List<TreatmentModality> getTreatmentModalities();
}
