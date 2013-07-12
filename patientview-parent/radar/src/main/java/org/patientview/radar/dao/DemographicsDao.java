package org.patientview.radar.dao;

import org.patientview.model.Centre;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.filter.DemographicsFilter;

import java.util.List;

public interface DemographicsDao {

    void saveDemographics(Demographics demographics);

    Demographics getDemographicsByRadarNumber(long radarNumber);

    List<Demographics> getDemographicsByRenalUnit(Centre centre);

    List<Demographics> getDemographics(DemographicsFilter filter, int page, int numberPerPage);

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Status> getStatuses();

}
