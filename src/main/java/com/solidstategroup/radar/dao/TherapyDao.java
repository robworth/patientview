package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.sequenced.Therapy;

import java.util.List;

public interface TherapyDao {

    Therapy getTherapy(long id);

    List<Therapy> getTherapyByRadarNumber(long radarNumber);

}
