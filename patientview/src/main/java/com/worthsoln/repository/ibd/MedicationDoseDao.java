package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.medication.MedicationDose;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface MedicationDoseDao {

    MedicationDose get(Long id);

    void save(MedicationDose medicationDose);
}
