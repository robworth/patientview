package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.exception.InvalidModelException;
import com.solidstategroup.radar.model.sequenced.Pathology;

import java.util.List;

public interface PathologyManager {
    public static final String TOTAL_ERROR = "Total has to be equal to or greater than the sum of the below values";

    void savePathology(Pathology pathology) throws InvalidModelException;

    Pathology getPathology(long id);

    List<Pathology> getPathologyByRadarNumber(long radarNumber);

}
