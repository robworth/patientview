package org.patientview.radar.service.impl;

import org.patientview.model.Patient;
import org.patientview.radar.dao.PatientDao;
import org.patientview.radar.service.PatientManager;

import java.util.List;

/**
 * Created for the new functionality with using just the on patient table. Going forward this can be then merged with
 * the class of the same name in Patient View
 *
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 11:11
 */
public class PatientManagerImpl implements PatientManager {

    private PatientDao patientDao;

    public List<Patient> getPatientByNhsNumber(String nhsNo) {
        return patientDao.getPatientsByNhsNumber(nhsNo);
    }

    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }
}
