package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.medication.MedicationType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MedicationTypeDao {

    MedicationType get(Long id);

    List<MedicationType> getAll();

    void save(MedicationType medication);
}
