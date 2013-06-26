package org.patientview.radar.dao;

import org.patientview.radar.model.sequenced.Pathology;

import java.util.List;

public interface PathologyDao {

    void savePathology(Pathology pathology);

    Pathology getPathology(long id);

    List<Pathology> getPathologyByRadarNumber(long radarNumber);

}
