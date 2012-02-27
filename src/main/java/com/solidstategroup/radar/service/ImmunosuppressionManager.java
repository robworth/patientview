package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Immunosuppression;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import com.solidstategroup.radar.model.exception.InvalidModelException;

import java.util.List;

public interface ImmunosuppressionManager {

    public static final String PREVIOUS_TREATMENT_NOT_STOPPED_ERROR = "Cannot start a new treatment whilst a " +
            "previous " + "treament has not been stopped";
    public static final String OVERLAPPING_ERROR = "Cannot add treatment overlapping with a previous entry";

    void saveImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment) throws InvalidModelException;

    void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment);

    ImmunosuppressionTreatment getImmunosuppressionTreatment(long id);

    List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber);

    List<Immunosuppression> getImmunosuppressions();

    Immunosuppression getImmunosuppression(long id);
}
