package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.ImmunosuppressionTreatment;

import java.util.List;

public interface ImmunosuppressionDao {

    ImmunosuppressionTreatment getImmunosuppressionTreatment(long id);

    List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber);
}
