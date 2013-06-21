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

package com.worthsoln.service.ibd.impl;

import com.worthsoln.ibd.model.Allergy;
import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.ibd.model.MyIbdSeverityLevel;
import com.worthsoln.ibd.model.Procedure;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.Severity;
import com.worthsoln.ibd.model.symptoms.ColitisSymptoms;
import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.Nutrition;
import com.worthsoln.ibd.model.medication.Medication;
import com.worthsoln.ibd.model.medication.MedicationDose;
import com.worthsoln.ibd.model.medication.MedicationType;
import com.worthsoln.ibd.model.medication.MyMedication;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.repository.ibd.AllergyDao;
import com.worthsoln.repository.ibd.CarePlanDao;
import com.worthsoln.repository.ibd.ColitisSymptomsDao;
import com.worthsoln.repository.ibd.CrohnsSymptomsDao;
import com.worthsoln.repository.ibd.MedicationDao;
import com.worthsoln.repository.ibd.MedicationTypeDao;
import com.worthsoln.repository.ibd.MyIbdDao;
import com.worthsoln.repository.ibd.MyIbdSeverityLevelDao;
import com.worthsoln.repository.ibd.MyMedicationDao;
import com.worthsoln.repository.ibd.NutritionDao;
import com.worthsoln.repository.ibd.MedicationDoseDao;
import com.worthsoln.repository.ibd.ProcedureDao;
import com.worthsoln.service.TestResultManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.service.ibd.IbdManager;
import com.worthsoln.utils.LegacySpringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service(value = "ibdManager")
public class IbdManagerImpl implements IbdManager {

    @Inject
    private MyIbdDao myIbdDao;

    @Inject
    private MyIbdSeverityLevelDao myIbdSeverityLevelDao;

    @Inject
    private CarePlanDao carePlanDao;

    @Inject
    private UserManager userManager;

    @Inject
    private TestResultManager testResultManager;

    @Inject
    private MedicationDao medicationDao;

    @Inject
    private MedicationDoseDao medicationDoseDao;

    @Inject
    private MedicationTypeDao medicationTypeDao;

    @Inject
    private MyMedicationDao myMedicationDao;

    @Inject
    private NutritionDao nutritionDao;

    @Inject
    private CrohnsSymptomsDao crohnsSymptomsDao;

    @Inject
    private ColitisSymptomsDao colitisSymptomsDao;

    @Inject
    private ProcedureDao procedureDao;

    @Inject
    private AllergyDao allergyDao;

    @Override
    public MyIbd getMyIbd(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getMyIbd(nhsNo);
        }

        return null;
    }

    @Override
    public MyIbd getMyIbd(String nhsno) {
        return myIbdDao.get(nhsno);
    }

    @Override
    public void saveMyIbd(MyIbd myIbd) {
        myIbdDao.save(myIbd);
    }

    @Override
    public void deleteMyIbd(String nhsno, String unitcode) {
        myIbdDao.delete(nhsno, unitcode);
    }

    @Override
    public MyIbdSeverityLevel getMyIbdSeverityLevel(String nhsno, Severity severity) {
        return myIbdSeverityLevelDao.get(nhsno, severity);
    }

    @Override
    public void saveMyIbdSeverityLevel(MyIbdSeverityLevel myIbdSeverityLevel) {
        myIbdSeverityLevelDao.save(myIbdSeverityLevel);
    }

    @Override
    public void saveMyIbdSeverityLevels(List<MyIbdSeverityLevel> myIbdSeverityLevels) {
        myIbdSeverityLevelDao.save(myIbdSeverityLevels);
    }

    @Override
    public CarePlan getCarePlan(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getCarePlan(nhsNo);
        }

        return null;
    }

    @Override
    public CarePlan getCarePlan(String nhsno) {
        return carePlanDao.get(nhsno);
    }

    @Override
    public void saveCarePlan(CarePlan carePlan) {
        carePlanDao.save(carePlan);
    }

    @Override
    public Medication getMedication(Long id) {
        return medicationDao.get(id);
    }

    @Override
    public MedicationDose getMedicationDose(Long id) {
        return medicationDoseDao.get(id);
    }

    @Override
    public MyMedication getMyMedication(Long id) {
        return myMedicationDao.get(id);
    }

    @Override
    public void saveMyMedication(MyMedication myMedication) {
        myMedicationDao.save(myMedication);
    }

    @Override
    public void saveMyMedication(MyMedication myMedication, Long medicationTypeId, Long medicationId,
                                 Long medicationDoseId) {
        if (medicationTypeId != null && medicationTypeId > 0) {
            MedicationType medicationType = getMedicationType(medicationTypeId);

            if (medicationType != null && medicationType.hasValidId()) {
                myMedication.setMedicationType(medicationType);
            }
        }

        if (medicationId != null && medicationId > 0) {
            Medication medication = getMedication(medicationId);

            if (medication != null && medication.hasValidId()) {
                myMedication.setMedication(medication);
            }
        }

        if (medicationDoseId != null && medicationDoseId > 0) {
            MedicationDose medicationDose = getMedicationDose(medicationDoseId);

            if (medicationDose != null && medicationDose.hasValidId()) {
                myMedication.setMedicationDose(medicationDose);
            }
        }

        myMedicationDao.save(myMedication);
    }

    @Override
    public List<MyMedication> getAllMedications(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getAllMedications(nhsNo);
        }

        return null;
    }

    @Override
    public List<MyMedication> getAllMedications(String nhsno) {
        return myMedicationDao.getAllMedications(nhsno);
    }

    @Override
    public List<MyMedication> getCurrentMedications(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getCurrentMedications(nhsNo);
        }

        return null;
    }

    @Override
    public List<MyMedication> getCurrentMedications(String nhsno) {
        return myMedicationDao.getCurrentMedications(nhsno);
    }

    @Override
    public List<MyMedication> getStoppedMedications(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getStoppedMedications(nhsNo);
        }

        return null;
    }

    @Override
    public List<MyMedication> getStoppedMedications(String nhsno) {
        return myMedicationDao.getStoppedMedications(nhsno);
    }

    @Override
    public MedicationType getMedicationType(Long id) {
        return medicationTypeDao.get(id);
    }

    @Override
    public List<MedicationType> getMedicationTypes() {
        return medicationTypeDao.getAll();
    }

    @Override
    public List<Nutrition> getAllNutritions(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getAllNutritions(nhsNo);
        }

        return null;
    }

    public List<Nutrition> getAllNutritions(String nhsno) {
        return nutritionDao.getAllNutritions(nhsno);
    }

    @Override
    public void saveNutrition(Nutrition nutrition) {
        nutritionDao.save(nutrition);
    }

    @Override
    public void saveCrohns(CrohnsSymptoms crohnsSymptoms) {
        crohnsSymptomsDao.save(crohnsSymptoms);
    }

    @Override
    public List<CrohnsSymptoms> getAllCrohns(User user) {
        return getAllCrohns(user, null, null);
    }

    @Override
    public List<CrohnsSymptoms> getAllCrohns(User user, Date fromDate, Date toDate) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getAllCrohns(nhsNo, fromDate, toDate);
        }

        return null;
    }

    public List<CrohnsSymptoms> getAllCrohns(String nhsno, Date fromDate, Date toDate) {
        return crohnsSymptomsDao.getAllCrohns(nhsno, fromDate, toDate);
    }

    @Override
    public void saveColitis(ColitisSymptoms colitisSymptoms) {
        colitisSymptomsDao.save(colitisSymptoms);
    }

    @Override
    public List<ColitisSymptoms> getAllColitis(User user) {
        return getAllColitis(user, null, null);
    }

    @Override
    public List<ColitisSymptoms> getAllColitis(User user, Date fromDate, Date toDate) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getAllColitis(nhsNo, fromDate, toDate);
        }

        return null;
    }

    @Override
    public String getWeight(User user) {
        return testResultManager.getLatestWeightFromResults(user);
    }

    public List<ColitisSymptoms> getAllColitis(String nhsno, Date fromDate, Date toDate) {
        return colitisSymptomsDao.getAllColitis(nhsno, fromDate, toDate);
    }

    private String getNhsNumber(User user) {
        UserMapping userMapping = userManager.getUserMappingPatientEntered(user);

        if (userMapping != null) {
            return userMapping.getNhsno();
        }

        return null;
    }

    @Override
    public Diagnosis getLoggedInUserDiagnosis() {
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();

        if (username != null) {
            User user = LegacySpringUtils.getUserManager().get(username);

            return getDiagnosis(user);
        }

        return null;
    }

    @Override
    public Diagnosis getDiagnosis(User user) {
        MyIbd myIbd = getMyIbd(user);

        if (myIbd != null) {
            return myIbd.getDiagnosis();
        } else {
            return null;
        }
    }

    @Override
    public void saveProcedure(Procedure procedure) {
        procedureDao.save(procedure);
    }

    @Override
    public Procedure getProcedure(String nhsno) {
        return procedureDao.getProcedure(nhsno);
    }

    @Override
    public void deleteProcedure(String nhsno, String unitcode) {
        procedureDao.delete(nhsno, unitcode);
    }

    @Override
    public void saveAllergy(Allergy allergy) {
        allergyDao.save(allergy);
    }

    @Override
    public Allergy getAllergy(String nhsno) {
        return allergyDao.getAllergy(nhsno);
    }

    @Override
    public void deleteAllergy(String nhsno, String unitcode) {
        allergyDao.delete(nhsno, unitcode);
    }
}
