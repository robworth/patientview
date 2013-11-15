package org.patientview.radar.dao;

import org.patientview.radar.model.PatientLink;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 16:00
 */
public interface PatientLinkDao {

    PatientLink createLink(final PatientLink patientLink);

    List<PatientLink> getPatientLink(String nhsNo, String unitCode);

}
