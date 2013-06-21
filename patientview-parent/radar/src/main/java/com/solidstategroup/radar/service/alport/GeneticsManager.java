package com.solidstategroup.radar.service.alport;

import com.solidstategroup.radar.model.Genetics;

public interface GeneticsManager {

    void save(Genetics genetics);

    Genetics get(Long radarNo);

}
