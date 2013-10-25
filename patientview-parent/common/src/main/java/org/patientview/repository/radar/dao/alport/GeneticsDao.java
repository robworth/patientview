package org.patientview.repository.radar.dao.alport;

import org.patientview.model.radar.Genetics;

public interface GeneticsDao {

    void save(Genetics genetics);

    Genetics get(Long radarNo);
}
