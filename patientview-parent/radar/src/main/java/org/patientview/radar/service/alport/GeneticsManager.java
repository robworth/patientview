package org.patientview.radar.service.alport;

import org.patientview.radar.model.Genetics;

public interface GeneticsManager {

    void save(Genetics genetics);

    Genetics get(Long radarNo);

}
