package org.patientview.radar.service;

import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.user.DemographicsUserDetail;
import org.patientview.radar.model.user.User;

import java.util.List;

public interface DemographicsManager {

    void saveDemographics(Patient patient);

    Patient getDemographicsByRadarNumber(long radarNumber);

    List<Patient> getDemographics();

    List<Patient> getDemographics(DemographicsFilter filter);

    List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage);

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Patient> getDemographicsByUnitAdmin(User user);

    List<Status> getStatuses();

    boolean isNhsNumberValid(String nhsNumber);

    boolean isNhsNumberValidWhenUppercaseLettersAreAllowed(String nhsNumber);

    Patient getDemographicsByNhsNoAndUnitCode(String nhsNo, String unitCode);

    DemographicsUserDetail getDemographicsUserDetail(String nhsno, String unitcode);

    Patient get(Long id);


}
