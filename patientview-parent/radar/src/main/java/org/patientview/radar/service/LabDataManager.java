package org.patientview.radar.service;

import org.patientview.radar.model.sequenced.LabData;

import java.util.List;

public interface LabDataManager {

    void saveLabData(LabData labData);

    LabData getLabData(long id);

    List<LabData> getLabDataByRadarNumber(long id);

    LabData getFirstLabDataByRadarNumber(Long id);
}
