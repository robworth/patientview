package org.patientview.radar.dao;

import org.patientview.radar.model.sequenced.LabData;

import java.util.List;

public interface LabDataDao {

    void saveLabData(LabData labData);

    LabData getLabData(long id);

    List<LabData> getLabDataByRadarNumber(long id);

    LabData getFirstLabDataByRadarNumber(Long id);
}
