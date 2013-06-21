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

package org.patientview.service.ibd;

import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.CarePlan;
import org.patientview.ibd.model.MyIbdSeverityLevel;
import org.patientview.ibd.model.Procedure;
import org.patientview.ibd.model.enums.Diagnosis;
import org.patientview.ibd.model.enums.Severity;
import org.patientview.ibd.model.symptoms.ColitisSymptoms;
import org.patientview.ibd.model.symptoms.CrohnsSymptoms;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Nutrition;
import org.patientview.ibd.model.medication.Medication;
import org.patientview.ibd.model.medication.MedicationDose;
import org.patientview.ibd.model.medication.MedicationType;
import org.patientview.ibd.model.medication.MyMedication;
import org.patientview.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface IbdManager {

    MyIbd getMyIbd(User user);

    MyIbd getMyIbd(String nhsno);

    void saveMyIbd(MyIbd myIbd);

    void deleteMyIbd(String nhsno, String unitcode);

    MyIbdSeverityLevel getMyIbdSeverityLevel(String nhsno, Severity severity);

    void saveMyIbdSeverityLevel(MyIbdSeverityLevel myIbdSeverityLevel);

    void saveMyIbdSeverityLevels(List<MyIbdSeverityLevel> myIbdSeverityLevels);

    CarePlan getCarePlan(User user);

    CarePlan getCarePlan(String nhsno);

    void saveCarePlan(CarePlan carePlan);

    Medication getMedication(Long id);

    MedicationDose getMedicationDose(Long id);

    MyMedication getMyMedication(Long id);

    void saveMyMedication(MyMedication myMedication);

    void saveMyMedication(MyMedication myMedication, Long medicationTypeId, Long medicationId, Long medicationDoseId);

    List<MyMedication> getAllMedications(User user);

    List<MyMedication> getAllMedications(String nhsno);

    List<MyMedication> getCurrentMedications(User user);

    List<MyMedication> getCurrentMedications(String nhsno);

    List<MyMedication> getStoppedMedications(User user);

    List<MyMedication> getStoppedMedications(String nhsno);

    MedicationType getMedicationType(Long id);

    List<MedicationType> getMedicationTypes();

    List<Nutrition> getAllNutritions(User user);

    void saveNutrition(Nutrition nutrition);

    void saveCrohns(CrohnsSymptoms crohnsSymptoms);

    List<CrohnsSymptoms> getAllCrohns(User user);

    List<CrohnsSymptoms> getAllCrohns(User user, Date fromDate, Date toDate);

    void saveColitis(ColitisSymptoms colitisSymptoms);

    List<ColitisSymptoms> getAllColitis(User user);

    List<ColitisSymptoms> getAllColitis(User user, Date fromDate, Date toDate);

    String getWeight(User user);

    Diagnosis getLoggedInUserDiagnosis();

    Diagnosis getDiagnosis(User user);

    void saveProcedure(Procedure procedure);

    Procedure getProcedure(String nhsno);

    void deleteProcedure(String nhsno, String unitcode);

    void saveAllergy(Allergy allergy);

    Allergy getAllergy(String nhsno);

    void deleteAllergy(String nhsno, String unitcode);

}
