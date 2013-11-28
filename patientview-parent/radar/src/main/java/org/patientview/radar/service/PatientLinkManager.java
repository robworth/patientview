package org.patientview.radar.service;

import org.patientview.model.Patient;
import org.patientview.model.PatientLink;
import org.patientview.radar.exception.PatientLinkException;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 17:03
 */
public interface PatientLinkManager {

    PatientLink createLink(PatientLink patientLink);

    PatientLink getPatientLink(String nhsNo, String unitCode);

    Patient linkPatientRecord(Patient patient) throws PatientLinkException;

    Patient getMergePatient(Patient sourcePatient) throws Exception;
}
