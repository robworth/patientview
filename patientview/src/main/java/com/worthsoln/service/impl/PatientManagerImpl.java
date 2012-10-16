package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.repository.PatientDao;
import com.worthsoln.service.PatientManager;
import com.worthsoln.service.SecurityUserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "patientManager")
public class PatientManagerImpl implements PatientManager {

    @Inject
    private PatientDao patientDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public Patient get(Long id) {
        return patientDao.get(id);
    }

    @Override
    public Patient get(String nhsno, String unitcode) {
        return patientDao.get(nhsno, unitcode);
    }

    @Override
    public void save(Patient patient) {
        patientDao.save(patient);
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        patientDao.delete(nhsno, unitcode);
    }

    @Override
    public List<Patient> get(String unitCode) {
        return patientDao.get(unitCode);
    }

    @Override
    public List getUnitPatientsWithTreatment(String unitcode, String nhsno, String name, boolean showgps) {
        return patientDao.getUnitPatientsWithTreatmentDao(unitcode, nhsno, name, showgps,
                securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List getUnitPatientsAllWithTreatmentDao(String unitcode) {
        return patientDao.getUnitPatientsAllWithTreatmentDao(unitcode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<Patient> getUktPatients() {
        return patientDao.getUktPatients();
    }
}
