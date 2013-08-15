package org.patientview.radar.dao;

import org.patientview.radar.model.sequenced.Therapy;

import java.util.List;

public interface TherapyDao {

    void saveTherapy(Therapy therapy);

    Therapy getTherapy(long id);

    List<Therapy> getTherapyByRadarNumber(long radarNumber);

    Therapy getFirstTherapyByRadarNumber(long radarNumber);
}
