package org.patientview.radar.dao;

import org.patientview.model.Patient;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 10:12
 */
public interface PatientDao {

    List<Patient> getPatientsByNhsNumber(String nhsNo);
}
