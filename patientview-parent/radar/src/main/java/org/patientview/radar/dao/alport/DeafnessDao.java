package org.patientview.radar.dao.alport;

import org.patientview.radar.model.alport.Deafness;

public interface DeafnessDao {

    void save(Deafness deafness);

    Deafness get(Long radarNo);
}
