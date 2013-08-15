package org.patientview.radar.service;

import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.model.sequenced.Pathology;

import java.util.List;

public interface PathologyManager {
    public static final String TOTAL_ERROR = "Total has to be equal to or greater than the sum of the below values";

    void savePathology(Pathology pathology) throws InvalidModelException;

    Pathology getPathology(long id);

    List<Pathology> getPathologyByRadarNumber(long radarNumber);

}
