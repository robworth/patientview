package com.solidstategroup.radar.dao.alport;

import com.solidstategroup.radar.model.Genetics;

public interface GeneticsDao {

    void save(Genetics genetics);

    Genetics get(Long radarNo);
}
