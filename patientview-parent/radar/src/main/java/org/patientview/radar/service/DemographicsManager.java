package org.patientview.radar.service;

import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.Status;
import org.patientview.radar.model.filter.DemographicsFilter;

import java.util.List;

public interface DemographicsManager {

    void saveDemographics(Demographics demographics);

    Demographics getDemographicsByRadarNumber(long radarNumber);

    List<Demographics> getDemographicsByRenalUnit(Centre centre);

    List<Demographics> getDemographics();

    List<Demographics> getDemographics(DemographicsFilter filter);

    List<Demographics> getDemographics(DemographicsFilter filter, int page, int numberPerPage);

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Status> getStatuses();

    boolean isNhsNumberValid(String nhsNumber);

    boolean isNhsNumberValidWhenUppercaseLettersAreAllowed(String nhsNumber);
}
