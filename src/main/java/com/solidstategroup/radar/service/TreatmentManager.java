package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.model.TreatmentModality;
import com.solidstategroup.radar.model.exception.InvalidModelException;

import java.util.List;

public interface TreatmentManager {

    String PREVIOUS_TREATMENT_NOT_CLOSED_ERROR = "Cannot start a new treatment whilst a " +
            "previous " + "treament has not been closed";
    String OVERLAPPING_ERROR = "Cannot add treatment overlapping with a previous entry";

    void saveTreatment(Treatment treatment) throws InvalidModelException;

    void deleteTreatment(Treatment treatment);

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);
    
    List<TreatmentModality> getTreatmentModalities();

}
