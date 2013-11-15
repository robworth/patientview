package org.patientview.radar.service;

import org.patientview.radar.model.PatientLink;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 17:03
 */
public interface PatientLinkManager {

    PatientLink createLink(PatientLink patientLink);

    List<PatientLink> getPatientLink(String nhsNo, String unitCode);

}
