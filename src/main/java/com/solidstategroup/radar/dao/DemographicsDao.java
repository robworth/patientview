package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Sex;

import java.util.List;

public interface DemographicsDao {

    Demographics getDemographicsByRadarNumber(long radarNumber);

    Sex getSex(long id);

    List<Sex> getSexes();

}
