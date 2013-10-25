package org.patientview.repository.radar.dao;

import org.patientview.model.radar.sequenced.LabData;

import java.util.List;

public interface LabDataDao {

    void saveLabData(LabData labData);

    LabData getLabData(long id);

    List<LabData> getLabDataByRadarNumber(long id);

    LabData getFirstLabDataByRadarNumber(Long id);
}
