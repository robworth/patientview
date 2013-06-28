package org.patientview.radar.service.alport;

import org.patientview.radar.model.alport.Deafness;

public interface DeafnessManager {

    void save(Deafness deafness);

    Deafness get(Long radarNo);
}
