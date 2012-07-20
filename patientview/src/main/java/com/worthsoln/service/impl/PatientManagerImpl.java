package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.repository.PatientDao;
import com.worthsoln.service.PatientManager;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
public class PatientManagerImpl implements PatientManager {

    @Inject
    private PatientDao patientDao;

    @Override
    public List<Patient> get(String unitCode) {
        return patientDao.get(unitCode);
    }
}
