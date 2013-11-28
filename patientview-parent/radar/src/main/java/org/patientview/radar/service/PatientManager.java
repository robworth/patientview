package org.patientview.radar.service;

import org.patientview.model.Patient;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 11:13
 */
public interface PatientManager {

    List<Patient> getPatientByNhsNumber(String nhsNo);

}
