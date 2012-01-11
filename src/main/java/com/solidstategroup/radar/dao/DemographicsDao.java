package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.model.Status;

import java.util.List;

public interface DemographicsDao {

    Demographics getDemographicsByRadarNumber(long radarNumber);

    List<Demographics> getDemographicsByRenalUnit(Centre centre);

    List<Demographics> getDemographics();

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Status> getStatuses();
}
