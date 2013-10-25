package org.patientview.repository.radar.service.alport;

import org.patientview.model.radar.alport.Deafness;

public interface DeafnessManager {

    void save(Deafness deafness);

    Deafness get(Long radarNo);
}
