package org.patientview.radar.service;

import org.patientview.radar.model.sequenced.Relapse;

import java.util.List;

public interface RelapseManager {

    void saveRelapse(Relapse relapse);

    void deleteRelapse(Relapse relapse);

    Relapse getRelapse(long id);

    List<Relapse> getRelapsesByRadarNumber(long radarNumber);

}
