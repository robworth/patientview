package com.worthsoln.service.impl;

import com.worthsoln.patientview.PatientDetails;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.uktransplant.UktUtils;
import com.worthsoln.repository.PatientDao;
import com.worthsoln.service.DiagnosisManager;
import com.worthsoln.service.EdtaCodeManager;
import com.worthsoln.service.LetterManager;
import com.worthsoln.service.MedicineManager;
import com.worthsoln.service.PatientManager;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.TestResultManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.utils.LegacySpringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service(value = "patientManager")
public class PatientManagerImpl implements PatientManager {

    @Inject
    private PatientDao patientDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private UserManager userManager;

    @Inject
    private UnitManager unitManager;

    @Inject
    private EdtaCodeManager edtaCodeManager;

    @Inject
    private DiagnosisManager diagnosisManager;

    @Inject
    private TestResultManager testResultManager;

    @Inject
    private LetterManager letterManager;

    @Inject
    private MedicineManager medicineManager;

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
    public void removePatientFromSystem(String nhsno, String unitcode) {
        // remove all the patiens content
        testResultManager.deleteTestResults(nhsno, unitcode);
        letterManager.delete(nhsno, unitcode);
        medicineManager.delete(nhsno, unitcode);
        diagnosisManager.deleteOtherDiagnoses(nhsno, unitcode);

        // finally remove the patient
        delete(nhsno, unitcode);
    }

    @Override
    public List<Patient> get(String unitCode) {
        return patientDao.get(unitCode);
    }

    @Override
    public List getUnitPatientsWithTreatment(String unitcode, String nhsno, String name, boolean showgps) {
        return patientDao.getUnitPatientsWithTreatmentDao(unitcode, nhsno, name, showgps,
                securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List getUnitPatientsAllWithTreatmentDao(String unitcode) {
        return patientDao.getUnitPatientsAllWithTreatmentDao(unitcode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Patient> getUktPatients() {
        return patientDao.getUktPatients();
    }

    @Override
    public List<PatientDetails> getPatientDetails(String username) {
        List<UserMapping> userMappings = userManager.getUserMappings(username);

        List<PatientDetails> patientDetails = new ArrayList<PatientDetails>();

        for (UserMapping userMapping : userMappings) {
            Patient patient = get(userMapping.getNhsno(), userMapping.getUnitcode());

            Unit unit = unitManager.get(userMapping.getUnitcode());

            if (patient != null && unit != null) {
                PatientDetails patientDetail = new PatientDetails();

                patientDetail.setPatient(patient);
                patientDetail.setUnit(unit);
                patientDetail.setEdtaDiagnosis(edtaCodeManager.getEdtaCode(patient.getDiagnosis()));
                patientDetail.setEdtaTreatment(edtaCodeManager.getEdtaCode(patient.getTreatment()));
                patientDetail.setOtherDiagnoses(diagnosisManager.getOtherDiagnoses(patient.getNhsno(),
                        patient.getCentreCode()));

                // TODO: dont really know bout this UktUtils ?
                patientDetail.setUktStatus(UktUtils.retreiveUktStatus(userMapping.getNhsno()));

                patientDetails.add(patientDetail);

                AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(),
                        AddLog.PATIENT_VIEW, "", patient.getNhsno(),
                        patient.getCentreCode(), "");
            }
        }

        return patientDetails;
    }
}
