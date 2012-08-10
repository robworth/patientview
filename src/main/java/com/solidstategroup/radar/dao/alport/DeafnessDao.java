package com.solidstategroup.radar.dao.alport;

import com.solidstategroup.radar.model.alport.Deafness;

public interface DeafnessDao {

    void save(Deafness deafness);

    Deafness get(Long radarNo);
}
