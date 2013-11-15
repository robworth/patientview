package org.patientview.radar.service.impl;

import org.patientview.radar.dao.PatientLinkDao;
import org.patientview.radar.model.PatientLink;
import org.patientview.radar.service.PatientLinkManager;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 17:04
 */
public class PatientLinkManagerImpl implements PatientLinkManager {

    private PatientLinkDao patientLinkDao;

    public PatientLink createLink(PatientLink patientLink) {
        return patientLinkDao.createLink(patientLink);
    }

    public List<PatientLink> getPatientLink(String nhsNo, String unitCode) {
        return patientLinkDao.getPatientLink(nhsNo, unitCode);
    }

    public void setPatientLinkDao(PatientLinkDao patientLinkDao) {
        this.patientLinkDao = patientLinkDao;
    }
}
