package org.patientview.radar.dao;

import org.patientview.model.Ethnicity;
import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 10:12
 */
public interface PatientDao {

    List<Patient> getPatientsByNhsNumber(final String nhsNo);

    List<Patient> getPatientsWithRadarSourceType();

    Patient getById(final Long id);

    Patient getPatientsByRadarNumber(final Long radarNumber);

    void save(final Patient patient);

    Patient getByPatientLinkId(final Long patientLinkId);

    List<Patient> getPatientsByUnitCode(List<String> unitCodes);

    List<Sex> getSexes();

    List<Status> getStatuses();

    List<DiseaseGroup> getDiseaseGroups();

    List<GenericDiagnosis> getGenericDiagnoses();

    List<Ethnicity> getEthnicities();
}
