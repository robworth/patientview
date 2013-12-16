package org.patientview.radar.dao;

import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.user.DemographicsUserDetail;

import java.util.List;

public interface DemographicsDao {

    void saveDemographics(Patient patient);

    Patient getDemographicsByRadarNumber(long radarNumber);

    List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage);

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Status> getStatuses();

    DemographicsUserDetail getDemographicsUserDetail(String nhsno, String unitcode);

    Patient get(Long id);
}
