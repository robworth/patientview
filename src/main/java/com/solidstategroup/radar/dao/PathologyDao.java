package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.sequenced.Pathology;

import java.util.List;

public interface PathologyDao {

    Pathology getPathology(long id);

    List<Pathology> getPathologyByRadarNumber(long radarNumber);

}
