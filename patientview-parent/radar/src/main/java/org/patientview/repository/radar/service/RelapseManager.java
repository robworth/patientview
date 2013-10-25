package org.patientview.repository.radar.service;

import org.patientview.model.radar.sequenced.Relapse;

import java.util.List;

public interface RelapseManager {

    void saveRelapse(Relapse relapse);

    void deleteRelapse(Relapse relapse);

    Relapse getRelapse(long id);

    List<Relapse> getRelapsesByRadarNumber(long radarNumber);

}
