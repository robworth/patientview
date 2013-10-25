package org.patientview.repository.radar.service;

import org.patientview.model.radar.sequenced.Therapy;

import java.util.List;

public interface TherapyManager {

    void saveTherapy(Therapy therapy);

    Therapy getTherapy(long id);

    List<Therapy> getTherapyByRadarNumber(long radarNumber);

    Therapy getFirstTherapyByRadarNumber(long radarNumber);
}
