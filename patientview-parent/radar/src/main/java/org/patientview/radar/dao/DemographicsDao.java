package org.patientview.radar.dao;

import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.user.DemographicsUserDetail;

import java.util.List;
import java.util.Set;

public interface DemographicsDao {

    void saveDemographics(Patient patient);

    Patient getDemographicsByRadarNumber(long radarNumber);

    List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage);

    Sex getSex(long id);

    List<Sex> getSexes();

    Status getStatus(long id);

    List<Status> getStatuses();

    Patient getDemographicsByNhsNoAndUnitCode(String nhsNo, String unitCode);

    DemographicsUserDetail getDemographicsUserDetail(String nhsno, String unitcode);

    List<Patient> getDemographicsByUnitCode(Set<String> unitCodes);

    Patient get(Long id);
}
