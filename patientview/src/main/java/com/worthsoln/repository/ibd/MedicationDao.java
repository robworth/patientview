package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.medication.Medication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface MedicationDao {

    Medication get(Long id);

    void save(Medication medication);
}
