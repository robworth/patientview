package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.sequenced.Therapy;

import java.util.List;

public interface TherapyManager {

    void saveTherapy(Therapy therapy);

    Therapy getTherapy(long id);

    List<Therapy> getTherapyByRadarNumber(long radarNumber);

    Therapy getFirstTherapyByRadarNumber(long radarNumber);
}
