package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.repository.DiagnosisDao;
import com.worthsoln.service.DiagnosisManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "diagnosisManager")
public class DiagnosisManagerImpl implements DiagnosisManager {

    @Inject
    private DiagnosisDao diagnosisDao;

    @Override
    public void save(Diagnosis diagnosis) {
        diagnosisDao.save(diagnosis);
    }

    @Override
    public List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode) {
        return diagnosisDao.getOtherDiagnoses(nhsno, unitcode);
    }
}
