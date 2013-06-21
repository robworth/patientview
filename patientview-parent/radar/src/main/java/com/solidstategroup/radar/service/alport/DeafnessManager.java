package com.solidstategroup.radar.service.alport;

import com.solidstategroup.radar.model.alport.Deafness;

public interface DeafnessManager {

    void save(Deafness deafness);

    Deafness get(Long radarNo);
}
