package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Demographics;

public interface DemographicsDao {

    Demographics getDemographicsByRadarNumber(long radarNumber);

}
