/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.service.impl;

import org.patientview.model.Patient;
import org.patientview.model.Unit;
import org.patientview.patientview.PatientDetails;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.uktransplant.UktUtils;
import org.patientview.repository.PatientDao;
import org.patientview.service.DiagnosisManager;
import org.patientview.service.EdtaCodeManager;
import org.patientview.service.LetterManager;
import org.patientview.service.MedicineManager;
import org.patientview.service.PatientManager;
import org.patientview.service.SecurityUserManager;
import org.patientview.service.TestResultManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Patient get(String nhsno, String unitcode) {
        return patientDao.get(nhsno, unitcode);
    }

    @Override
    public Patient get(Long id) {
        return patientDao.get(id);
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
    public List<Patient> getByNhsNo(String nhsNo) {
        return patientDao.getByNhsNo(nhsNo);
    }


    public Patient getRadarPatient(String nhsNo) {
        return patientDao.getRadarPatient(nhsNo);
    }

    @Override
    public List getUnitPatientsWithTreatment(String unitcode, String nhsno, String firstname, String lastname,
                                             boolean showgps) {
        return patientDao.getUnitPatientsWithTreatmentDao(unitcode, nhsno, firstname, lastname, showgps,
                securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List getAllUnitPatientsWithTreatment(String nhsno, String firstname, String lastname, boolean showgps) {
        return patientDao.getAllUnitPatientsWithTreatmentDao(nhsno, firstname, lastname, showgps,
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

    /**
     * Get all patient records that are associated with this user
     * @param username of user
     * @return a list of 'mini' objects based on patient records
     */
    @Override
    public List<PatientDetails> getPatientDetails(String username) {

        // our results list
        List<PatientDetails> patientDetails = new ArrayList<PatientDetails>();

        // Get a set of nhs numbers associated with this user via the user mappings table
        Set<String> nhsNumbersAssociatedWithUser = new HashSet<String>();
        List<UserMapping> userMappings = userManager.getUserMappings(username);
        if (!CollectionUtils.isEmpty(userMappings)) {
            for (UserMapping userMapping : userMappings) {
                nhsNumbersAssociatedWithUser.add(userMapping.getNhsno());
            }
        }

        // get a set of patient records for these nhs numbers, including patients added by Radar
        for (String nhsNumber : nhsNumbersAssociatedWithUser) {
            for (Patient patient : getByNhsNo(nhsNumber)) {
                patientDetails.add(createPatientDetails(patient, unitManager.get(patient.getUnitcode())));
                AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(),
                        AddLog.PATIENT_VIEW, "", patient.getNhsno(),
                        patient.getUnitcode(), "");
            }
        }

        return patientDetails;
    }

    @Override
    public List<PatientDetails> getPatientDetails(Long id) {
        Patient patient = get(id);
        List<PatientDetails> patientDetails = new ArrayList<PatientDetails>();
        patientDetails.add(createPatientDetails(patient, unitManager.get(patient.getUnitcode())));
        return patientDetails;

    }

    private PatientDetails createPatientDetails(Patient patient, Unit unit) {
        PatientDetails patientDetail = new PatientDetails();

        patientDetail.setPatient(patient);

        patientDetail.setUnit(unit);
        patientDetail.setEdtaDiagnosis(edtaCodeManager.getEdtaCode(patient.getDiagnosis()));
        patientDetail.setEdtaTreatment(edtaCodeManager.getEdtaCode(patient.getTreatment()));
        patientDetail.setOtherDiagnoses(diagnosisManager.getOtherDiagnoses(patient.getNhsno(),
                patient.getUnitcode()));

        // get the transplant status for the patient
        patientDetail.setUktStatus(UktUtils.retreiveUktStatus(patient.getNhsno()));

        return patientDetail;

    }

    @Override
    public Patient getPatient(String username) {
        List<UserMapping> userMappings = userManager.getUserMappings(username);


        for (UserMapping userMapping : userMappings) {
            String unitcode = userMapping.getUnitcode();
            //check user's permission
            if (!securityUserManager.userHasReadAccessToUnit(unitcode)) {
                continue;
            }
            Unit unit = unitManager.get(unitcode);
            if (!"radargroup".equalsIgnoreCase(unit.getSourceType())) {
                continue;
            }

            Patient patient = get(userMapping.getNhsno(), unitcode);
            if (patient == null) {
                continue;
            }

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(),
                    AddLog.PATIENT_VIEW, "", patient.getNhsno(),
                    patient.getUnitcode(), "");
            return patient;
        }

        return null;
    }


    /**
     * This is to get the date by unit of the test results loaded into the system
     *
     *
     * @param nhsNo
     * @return
     */
    public Map.Entry<String, Date> getLatestTestResultUnit(String nhsNo) {

        Map.Entry<String, Date> maxTestRange = null;

        for (Map.Entry<String, Date> testDateRange : getMostRecentTestResultDateByNhsNo(nhsNo).entrySet()) {

            if (maxTestRange == null) {
                maxTestRange = testDateRange;
            } else {
                // Check which entry is the latest
                if (maxTestRange.getValue().before(testDateRange.getValue())) {
                    maxTestRange = testDateRange;
                }
            }

        }

        return maxTestRange;
    }

    private Map<String, Date> getMostRecentTestResultDateByNhsNo(String nhsNo) {

        Map<String, Date> maxDataRangeDate = new HashMap<String, Date>();
        for (Patient patient : patientDao.getByNhsNo(nhsNo)) {

            if (patient.getMostRecentTestResultDateRangeStopDate() != null) {
                maxDataRangeDate.put(patient.getUnitcode(), patient.getMostRecentTestResultDateRangeStopDate());
            }

        }
        return maxDataRangeDate;
    }



}
