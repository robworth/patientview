package org.patientview.radar.service;

import org.patientview.model.Ethnicity;
import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;
import org.patientview.radar.exception.PatientLinkException;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 11:13
 */
public interface PatientManager {

    List<Patient> getPatientByNhsNumber(String nhsNo);

    Patient getPatientByRadarNumber(Long radarNumber);

    Patient getById(Long id);

    void save(final Patient patient);

    List<Patient> getPatientsByUnitCode(List<String> unitCodes);

    Patient createLinkPatient(Patient patient) throws PatientLinkException;

    List<Sex> getSexes();

    List<Status> getStatuses();

    List<DiseaseGroup> getDiseaseGroups();

    List<GenericDiagnosis> getGenericDiagnoses();

    List<Ethnicity> getEthnicities();

}
