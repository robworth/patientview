package org.patientview.radar.service;

import org.patientview.model.Patient;
import org.patientview.radar.exception.PatientLinkException;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 17:03
 */
public interface PatientLinkManager {

    Patient createLinkPatientRecord(Patient patient) throws PatientLinkException;
}
