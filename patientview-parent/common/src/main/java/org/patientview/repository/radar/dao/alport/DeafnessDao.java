package org.patientview.repository.radar.dao.alport;

import org.patientview.model.radar.alport.Deafness;

public interface DeafnessDao {

    void save(Deafness deafness);

    Deafness get(Long radarNo);
}
