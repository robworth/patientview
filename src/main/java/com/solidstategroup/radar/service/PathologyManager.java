package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.sequenced.Pathology;

import java.util.List;

public interface PathologyManager {

    void savePathology(Pathology pathology);

    Pathology getPathology(long id);

    List<Pathology> getPathologyByRadarNumber(long radarNumber);

}
