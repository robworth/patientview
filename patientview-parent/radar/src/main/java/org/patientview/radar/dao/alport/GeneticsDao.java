package org.patientview.radar.dao.alport;

import org.patientview.radar.model.Genetics;

public interface GeneticsDao {

    void save(Genetics genetics);

    Genetics get(Long radarNo);
}
