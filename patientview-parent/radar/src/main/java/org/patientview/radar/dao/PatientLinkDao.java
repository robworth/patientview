package org.patientview.radar.dao;

import org.patientview.radar.model.PatientLink;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 16:00
 */
public interface PatientLinkDao {

    PatientLink createLink(final PatientLink patientLink);

    PatientLink getPatientLink(String nhsNo, String unitCode);

}
