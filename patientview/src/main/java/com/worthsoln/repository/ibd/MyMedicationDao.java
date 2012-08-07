package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.medication.MyMedication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MyMedicationDao {

    MyMedication get(Long id);

    void save(MyMedication myMedication);

    List<MyMedication> getAllMedications(String nhsno);

    List<MyMedication> getCurrentMedications(String nhsno);

    List<MyMedication> getStoppedMedications(String nhsno);
}
