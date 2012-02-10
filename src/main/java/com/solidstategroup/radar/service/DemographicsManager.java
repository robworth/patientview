package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.model.Status;

import java.util.List;

public interface DemographicsManager {

    void saveDemographics(Demographics demographics);

    Demographics getDemographicsByRadarNumber(long radarNumber);

    List<Demographics> getDemographicsByRenalUnit(Centre centre);

    List<Demographics> getDemographics();

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Status> getStatuses();

}
