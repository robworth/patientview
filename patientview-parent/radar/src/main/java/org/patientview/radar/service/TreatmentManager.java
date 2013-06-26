package org.patientview.radar.service;

import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;
import org.patientview.radar.model.exception.InvalidModelException;

import java.util.Arrays;
import java.util.List;

public interface TreatmentManager {

    public static final String PREVIOUS_TREATMENT_NOT_CLOSED_ERROR = "Cannot start a new treatment after a previous " +
            "treatment start date which has not been closed";
    public static final String OVERLAPPING_ERROR = "Cannot add treatment overlapping with a previous entry";
    public static final String BEFORE_DOB_ERROR = "treatment dates cannot precede date of birth";
    public static final String AFTER_TODAY_ERROR = "treatment dates cannot be after today";
    public static final List<String> ERROR_MESSAGES = Arrays.asList(PREVIOUS_TREATMENT_NOT_CLOSED_ERROR,
            OVERLAPPING_ERROR, BEFORE_DOB_ERROR, AFTER_TODAY_ERROR);

    void saveTreatment(Treatment treatment) throws InvalidModelException;

    void deleteTreatment(Treatment treatment);

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);

    List<TreatmentModality> getTreatmentModalities();

}
