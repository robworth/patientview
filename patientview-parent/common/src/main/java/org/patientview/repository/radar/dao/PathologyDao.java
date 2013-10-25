package org.patientview.repository.radar.dao;

import org.patientview.model.radar.sequenced.Pathology;

import java.util.List;

public interface PathologyDao {

    void savePathology(Pathology pathology);

    Pathology getPathology(long id);

    List<Pathology> getPathologyByRadarNumber(long radarNumber);

}
