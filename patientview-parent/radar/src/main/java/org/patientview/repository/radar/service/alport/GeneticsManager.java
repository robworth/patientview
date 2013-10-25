package org.patientview.repository.radar.service.alport;

import org.patientview.model.radar.Genetics;

public interface GeneticsManager {

    void save(Genetics genetics);

    Genetics get(Long radarNo);

}
