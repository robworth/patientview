package com.worthsoln.service.ibd;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
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

    void saveAllergy(Allergy allergy);

    Allergy getAllergy(String nhsno);

}
